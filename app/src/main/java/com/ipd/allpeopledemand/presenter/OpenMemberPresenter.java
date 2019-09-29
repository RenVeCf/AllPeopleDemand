package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.OpenMemberBean;
import com.ipd.allpeopledemand.contract.OpenMemberContract;
import com.ipd.allpeopledemand.model.OpenMemberModel;
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
public class OpenMemberPresenter extends OpenMemberContract.Presenter {

    private OpenMemberModel model;
    private Context context;

    public OpenMemberPresenter(Context context) {
        this.model = new OpenMemberModel();
        this.context = context;
    }

    @Override
    public void getOpenMember(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOpenMember(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOpenMember((OpenMemberBean) o);
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