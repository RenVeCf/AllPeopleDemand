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
public class MyBuyModel<T> extends BaseModel {

    public void getMyBuyDemandList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                   ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMyBuyDemandList(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMyBuyDemandDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMyBuyDemandDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMyBuyClassRoomList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMyBuyClassRoomList(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMyBuyClassRoomDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                         ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMyBuyClassRoomDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAttentionCollection(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getAttentionCollection(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
