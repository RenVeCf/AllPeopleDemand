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
public class AttentionModel<T> extends BaseModel {

    public void getMainDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMainDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAttentionDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                    ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getAttentionDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMyBuyDemandDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMyBuyDemandDetails(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getAttentionCollection(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                       ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getAttentionCollection(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getReportList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getReportList(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getReport(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                          ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getReport(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMainAliPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMainAliPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMainWechatPay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                 ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMainWechatPay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getMainBalancePay(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                  ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getMainBalancePay(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getShare(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                         ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getShare(map), observerListener, transformer, isDialog, cancelable);
    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
