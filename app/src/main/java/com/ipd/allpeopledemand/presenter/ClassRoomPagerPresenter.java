package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.contract.ClassRoomPagerContract;
import com.ipd.allpeopledemand.model.ClassRoomPagerModel;
import com.ipd.allpeopledemand.progress.ObserverResponseListener;
import com.ipd.allpeopledemand.utils.ExceptionHandle;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class ClassRoomPagerPresenter extends ClassRoomPagerContract.Presenter {

    private ClassRoomPagerModel model;
    private Context context;

    public ClassRoomPagerPresenter(Context context) {
        this.model = new ClassRoomPagerModel();
        this.context = context;
    }

    @Override
    public void getClassRoomPager(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getClassRoomPager(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultClassRoomPager((ClassRoomPagerBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getClassRoomDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getClassRoomDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultClassRoomDetails((ClassRoomDetailsBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getClassRoomAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getClassRoomAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultClassRoomAliPay((ClassRoomAliPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}