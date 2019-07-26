package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.LoadingBean;
import com.ipd.allpeopledemand.contract.LoadingContract;
import com.ipd.allpeopledemand.model.LoadingModel;
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
public class LoadingPresenter extends LoadingContract.Presenter {

    private LoadingModel model;
    private Context context;

    public LoadingPresenter(Context context) {
        this.model = new LoadingModel();
        this.context = context;
    }

    @Override
    public void getLoading(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getLoading(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultLoading((LoadingBean) o);
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