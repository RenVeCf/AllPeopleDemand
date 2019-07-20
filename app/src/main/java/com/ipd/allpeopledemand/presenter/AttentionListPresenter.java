package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionListBean;
import com.ipd.allpeopledemand.contract.AttentionListContract;
import com.ipd.allpeopledemand.model.AttentionListModel;
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
public class AttentionListPresenter extends AttentionListContract.Presenter {

    private AttentionListModel model;
    private Context context;

    public AttentionListPresenter(Context context) {
        this.model = new AttentionListModel();
        this.context = context;
    }

    @Override
    public void getAttentionList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAttentionList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAttentionList((AttentionListBean) o);
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