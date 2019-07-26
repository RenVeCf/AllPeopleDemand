package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.MainADImgBean;
import com.ipd.allpeopledemand.bean.MainAliPayBean;
import com.ipd.allpeopledemand.bean.MainBalancePayBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.bean.MainWechatPayBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface MainPagerContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMainPager(ClassIficationBean data);

        void resultMainList(MainListBean data);

        void resultAttentionCollection(AttentionCollectionBean data);

        void resultMainADImg(MainADImgBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMainPager(boolean isDialog, boolean cancelable);

        public abstract void getMainList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAttentionCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMainADImg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}