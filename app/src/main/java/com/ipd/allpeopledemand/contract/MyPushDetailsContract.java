package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.MyPushCollectionBean;
import com.ipd.allpeopledemand.bean.MyPushDemandTypeBean;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface MyPushDetailsContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMyPushDetails(MyPushDetailsBean data);

        void resultMyPushCollection(MyPushCollectionBean data);

        void resultMyPushDemandType(MyPushDemandTypeBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMyPushDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyPushCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyPushDemandType(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}