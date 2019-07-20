package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.AccountBean;
import com.ipd.allpeopledemand.contract.AccountContract;
import com.ipd.allpeopledemand.model.AccountModel;
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
public class AccountPresenter extends AccountContract.Presenter {

    private AccountModel model;
    private Context context;

    public AccountPresenter(Context context) {
        this.model = new AccountModel();
        this.context = context;
    }

    @Override
    public void getAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAccount(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAccount((AccountBean) o);
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