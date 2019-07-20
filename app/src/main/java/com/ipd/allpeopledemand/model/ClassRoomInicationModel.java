package com.ipd.allpeopledemand.model;

import android.content.Context;

import com.ipd.allpeopledemand.api.Api;
import com.ipd.allpeopledemand.base.BaseModel;
import com.ipd.allpeopledemand.progress.ObserverResponseListener;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class ClassRoomInicationModel<T> extends BaseModel {

    public void getClassRoomInication(Context context, boolean isDialog, boolean cancelable,
                                      ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        nullParamSubscribe(context, Api.getApiService().getClassRoomInication(), observerListener, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
