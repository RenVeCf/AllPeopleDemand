package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.MyPushListBean;
import com.ipd.allpeopledemand.contract.MyPushListContract;
import com.ipd.allpeopledemand.model.MyPushListModel;
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
public class MyPushListPresenter extends MyPushListContract.Presenter {

    private MyPushListModel model;
    private Context context;

    public MyPushListPresenter(Context context) {
        this.model = new MyPushListModel();
        this.context = context;
    }

    @Override
    public void getMyPushList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyPushList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyPushList((MyPushListBean) o);
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