package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.MyPushCollectionBean;
import com.ipd.allpeopledemand.bean.MyPushDemandTypeBean;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;
import com.ipd.allpeopledemand.contract.MyPushDetailsContract;
import com.ipd.allpeopledemand.model.MyPushDetailsModel;
import com.ipd.allpeopledemand.progress.ObserverResponseListener;
import com.ipd.allpeopledemand.utils.ExceptionHandle;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class MyPushDetailsPresenter extends MyPushDetailsContract.Presenter {

    private MyPushDetailsModel model;
    private Context context;

    public MyPushDetailsPresenter(Context context) {
        this.model = new MyPushDetailsModel();
        this.context = context;
    }

    @Override
    public void getMyPushDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyPushDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyPushDetails((MyPushDetailsBean) o);
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
    public void getMyPushCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyPushCollection(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyPushCollection((MyPushCollectionBean) o);
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
    public void getMyPushDemandType(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyPushDemandType(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyPushDemandType((MyPushDemandTypeBean) o);
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