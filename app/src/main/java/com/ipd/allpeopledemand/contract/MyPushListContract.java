package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;
import com.ipd.allpeopledemand.bean.MyPushListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface MyPushListContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMyPushList(MyPushListBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMyPushList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}