package com.ipd.allpeopledemand.model;

import android.content.Context;

import com.ipd.allpeopledemand.api.Api;
import com.ipd.allpeopledemand.base.BaseModel;
import com.ipd.allpeopledemand.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class MyPushDetailsModel<T> extends BaseModel {

    public void getMyPushDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T, T> transformer, ObserverResponseListener observerDetailsener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerDetailsener);
        paramSubscribe(context, Api.getApiService().getMyPushDetails(map), observerDetailsener, transformer, isDialog, cancelable);
    }

    public void getMyPushCollection(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                    ObservableTransformer<T, T> transformer, ObserverResponseListener observerDetailsener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerDetailsener);
        paramSubscribe(context, Api.getApiService().getMyPushCollection(map), observerDetailsener, transformer, isDialog, cancelable);
    }

    public void getMyPushDemandType(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                    ObservableTransformer<T, T> transformer, ObserverResponseListener observerDetailsener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerDetailsener);
        paramSubscribe(context, Api.getApiService().getMyPushDemandType(map), observerDetailsener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
