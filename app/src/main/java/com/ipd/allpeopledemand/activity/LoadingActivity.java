package com.ipd.allpeopledemand.activity;

import android.content.Intent;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.LoadingBean;
import com.ipd.allpeopledemand.contract.LoadingContract;
import com.ipd.allpeopledemand.presenter.LoadingPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.banner.anim.select.ZoomInEnter;
import com.xuexiang.xui.widget.banner.transform.DepthTransformer;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：引导页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class LoadingActivity extends BaseActivity<LoadingContract.View, LoadingContract.Presenter> implements LoadingContract.View {

    @BindView(R.id.sgb)
    SimpleGuideBanner sgb;

    private long firstTime = 0;
    private List<LoadingBean.DataBean.GuidePageListBean> guidePageListBean = new ArrayList<>();//引导页数据源
    private List<Object> usertGuides = new ArrayList<>();//引导页数据源

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public LoadingContract.Presenter createPresenter() {
        return new LoadingPresenter(this);
    }

    @Override
    public LoadingContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        guidePageListBean = getIntent().getParcelableArrayListExtra("usertGuides");
        for (LoadingBean.DataBean.GuidePageListBean guidePageListBean : guidePageListBean) {
            usertGuides.add(BASE_LOCAL_URL + guidePageListBean.getPicPath());
        }
        sgb();
    }

    @Override
    public void initData() {
//        TreeMap<String, String> loadingMap = new TreeMap<>();
//        loadingMap.put("password", "F9A75BB045D75998E1509B75ED3A5225");
//        getPresenter().getLoading(loadingMap, false, false);
    }

    @Override
    public void initListener() {

    }

    //双击退出程序
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showShortToast(getResources().getString(R.string.click_out_again));
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();

        }
    }

    private void sgb() {
//        if (usertGuides.size() < 2)
        sgb
                .setIndicatorShow(true)
                .setBarShowWhenLast(false)
                .setIndicatorWidth(20)
                .setIndicatorHeight(8)
                .setIndicatorGap(20)
                .setIndicatorSelectorRes(R.drawable.bg_unselector_radius, R.drawable.bg_selector_radius)
                .setIndicatorSelectColor(getResources().getColor(R.color.peru))
                .setIndicatorUnselectColor(getResources().getColor(R.color.tx_bottom_navigation))
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(DepthTransformer.class)
                .barPadding(0, getResources().getDimension(R.dimen.y20), 0, getResources().getDimension(R.dimen.y50))
                .setSource(usertGuides)
                .startScroll();

        sgb.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                if (isFastClick()) {
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void resultLoading(LoadingBean data) {
        if (data.getCode() == 200) {
            if (data.getData().getGuidePageList().size() > 0) {
                for (LoadingBean.DataBean.GuidePageListBean guidePageListBean : data.getData().getGuidePageList()) {
                    usertGuides.add(BASE_LOCAL_URL + guidePageListBean.getPicPath());
                }
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }

            sgb();
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
