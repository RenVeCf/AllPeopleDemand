package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.ForgetPwdBean;
import com.ipd.allpeopledemand.bean.SmsBean;
import com.ipd.allpeopledemand.contract.ForgetPwdContract;
import com.ipd.allpeopledemand.model.ForgetPwdModel;
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
public class ForgetPwdPresenter extends ForgetPwdContract.Presenter {

    private ForgetPwdModel model;
    private Context context;

    public ForgetPwdPresenter(Context context) {
        this.model = new ForgetPwdModel();
        this.context = context;
    }

    @Override
    public void getForgetPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getForgetPwd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultForgetPwd((ForgetPwdBean) o);
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
    public void getSms(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSms(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSms((SmsBean) o);
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