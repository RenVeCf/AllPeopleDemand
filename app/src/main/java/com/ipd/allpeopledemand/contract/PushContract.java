package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.PushBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;
import okhttp3.RequestBody;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface PushContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultPush(PushBean data);

        void resultClassIfication(ClassIficationBean data);

        void resultUploadImg(UploadImgBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getPush(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getClassIfication(boolean isDialog, boolean cancelable);

        public abstract void getUploadImg(TreeMap<String, RequestBody> map, boolean isDialog, boolean cancelable);
    }
}