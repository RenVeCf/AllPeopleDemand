package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.CheckVersionBean;
import com.ipd.allpeopledemand.contract.CheckVersionContract;
import com.ipd.allpeopledemand.model.CheckVersionModel;
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
public class CheckVersionPresenter extends CheckVersionContract.Presenter {

    private CheckVersionModel model;
    private Context context;

    public CheckVersionPresenter(Context context) {
        this.model = new CheckVersionModel();
        this.context = context;
    }

    @Override
    public void getCheckVersion(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getCheckVersion(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultCheckVersion((CheckVersionBean) o);
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