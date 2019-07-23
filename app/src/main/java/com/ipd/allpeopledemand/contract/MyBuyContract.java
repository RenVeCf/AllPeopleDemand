package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomListBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface MyBuyContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMyBuyDemandList(MyBuyDemandListBean data);

        void resultMyBuyDemandDetails(MyBuyDemandDetailsBean data);

        void resultMyBuyClassRoomList(MyBuyClassRoomListBean data);

        void resultMyBuyClassRoomDetails(MyBuyClassRoomDetailsBean data);

        void resultAttentionCollection(AttentionCollectionBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMyBuyDemandList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyBuyDemandDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyBuyClassRoomList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getMyBuyClassRoomDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAttentionCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}