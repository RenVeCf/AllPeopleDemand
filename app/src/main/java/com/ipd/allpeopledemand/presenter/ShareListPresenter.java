package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.ShareListBean;
import com.ipd.allpeopledemand.contract.ShareListContract;
import com.ipd.allpeopledemand.model.ShareListModel;
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
public class ShareListPresenter extends ShareListContract.Presenter {

    private ShareListModel model;
    private Context context;

    public ShareListPresenter(Context context) {
        this.model = new ShareListModel();
        this.context = context;
    }

    @Override
    public void getShareList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getShareList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultShareList((ShareListBean) o);
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