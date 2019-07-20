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
public class ClassRoomPagerModel<T> extends BaseModel {

    public void getClassRoomPager(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                  ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getClassRoomPager(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getClassRoomDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                    ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getClassRoomDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getClassRoomAliPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                   ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getClassRoomAliPay(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
