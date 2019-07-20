package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface AttentionListContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultAttentionList(AttentionListBean data);

        void resultAttentionCollection(AttentionCollectionBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getAttentionList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAttentionCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}