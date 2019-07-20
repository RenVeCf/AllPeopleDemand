package com.ipd.allpeopledemand.contract;

import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionDetailsBean;
import com.ipd.allpeopledemand.bean.MainDetailsBean;
import com.ipd.allpeopledemand.bean.ReportBean;
import com.ipd.allpeopledemand.bean.ReportListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface AttentionContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultMainDetails(MainDetailsBean data);

        void resultAttentionDetails(AttentionDetailsBean data);

        void resultAttentionCollection(AttentionCollectionBean data);

        void resultReportList(ReportListBean data);

        void resultReport(ReportBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getMainDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAttentionDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAttentionCollection(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReportList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getReport(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}