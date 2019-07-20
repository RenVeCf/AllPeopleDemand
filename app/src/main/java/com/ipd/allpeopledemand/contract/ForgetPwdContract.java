package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.ForgetPwdBean;
import com.ipd.allpeopledemand.bean.SmsBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ForgetPwdContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultForgetPwd(ForgetPwdBean data);

        void resultSms(SmsBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getForgetPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSms(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}