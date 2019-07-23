package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomListBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandListBean;
import com.ipd.allpeopledemand.contract.MyBuyContract;
import com.ipd.allpeopledemand.model.MyBuyModel;
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
public class MyBuyPresenter extends MyBuyContract.Presenter {

    private MyBuyModel model;
    private Context context;

    public MyBuyPresenter(Context context) {
        this.model = new MyBuyModel();
        this.context = context;
    }

    @Override
    public void getMyBuyDemandList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyBuyDemandList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyBuyDemandList((MyBuyDemandListBean) o);
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
    public void getMyBuyDemandDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyBuyDemandDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyBuyDemandDetails((MyBuyDemandDetailsBean) o);
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
    public void getMyBuyClassRoomList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyBuyClassRoomList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyBuyClassRoomList((MyBuyClassRoomListBean) o);
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
    public void getMyBuyClassRoomDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getMyBuyClassRoomDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultMyBuyClassRoomDetails((MyBuyClassRoomDetailsBean) o);
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
    public void getAttentionCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAttentionCollection(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAttentionCollection((AttentionCollectionBean) o);
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