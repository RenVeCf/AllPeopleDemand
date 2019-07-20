package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.ShareListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ShareListContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultShareList(ShareListBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getShareList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}