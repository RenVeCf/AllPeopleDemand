package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.WechatBindBean;
import com.ipd.allpeopledemand.bean.WithdrawBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface WithdrawContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultWithdraw(WithdrawBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getWithdraw(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}