package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.MyPushEditBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.contract.MyPushEditContract;
import com.ipd.allpeopledemand.model.MyPushEditModel;
import com.ipd.allpeopledemand.progress.ObserverResponseListener;
import com.ipd.allpeopledemand.utils.ExceptionHandle;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class MyPushEditPresenter extends MyPushEditContract.Presenter {

    private MyPushEditModel model;
    private Context context;

    public MyPushEditPresenter(Context context) {
        this.model = new MyPushEditModel();
        this.context = context;
    }

    @Override
    public void getMyPushEdit(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyPushEdit(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyPushEdit((MyPushEditBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getUploadImg(TreeMap<String, RequestBody> map, boolean isDialog, boolean cancelable) {
        model.getUploadImg(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultUploadImg((UploadImgBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}