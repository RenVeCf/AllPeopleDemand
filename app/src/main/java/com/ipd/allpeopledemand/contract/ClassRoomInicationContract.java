package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.ClassRoomInicationBean;
import com.ipd.allpeopledemand.bean.IsMsgBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ClassRoomInicationContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultClassRoomInication(ClassRoomInicationBean data);

        void resultIsMsg(IsMsgBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getClassRoomInication(boolean isDialog, boolean cancelable);

        public abstract void getIsMsg(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}