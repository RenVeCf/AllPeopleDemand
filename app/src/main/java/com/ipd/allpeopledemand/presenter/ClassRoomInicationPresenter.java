package com.ipd.allpeopledemand.presenter;

import android.content.Context;

import com.ipd.allpeopledemand.bean.ClassRoomInicationBean;
import com.ipd.allpeopledemand.contract.ClassRoomInicationContract;
import com.ipd.allpeopledemand.model.ClassRoomInicationModel;
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
public class ClassRoomInicationPresenter extends ClassRoomInicationContract.Presenter {

    private ClassRoomInicationModel model;
    private Context context;

    public ClassRoomInicationPresenter(Context context) {
        this.model = new ClassRoomInicationModel();
        this.context = context;
    }

    @Override
    public void getClassRoomInication(boolean isDialog, boolean cancelable) {
        model.getClassRoomInication(context,isDialog, cancelable, new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultClassRoomInication((ClassRoomInicationBean) o);
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