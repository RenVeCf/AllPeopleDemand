package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ClassRoomPagerContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultClassRoomPager(ClassRoomPagerBean data);

        void resultClassRoomDetails(ClassRoomDetailsBean data);

        void resultClassRoomAliPay(ClassRoomAliPayBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getClassRoomPager(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getClassRoomDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getClassRoomAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}