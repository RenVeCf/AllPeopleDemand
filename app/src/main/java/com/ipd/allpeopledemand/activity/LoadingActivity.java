package com.ipd.allpeopledemand.activity;

import android.content.Intent;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.banner.transform.DepthTransformer;
import com.xuexiang.xui.widget.banner.widget.banner.SimpleGuideBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：引导页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class LoadingActivity extends BaseActivity {

    @BindView(R.id.sgb)
    SimpleGuideBanner sgb;

    private long firstTime = 0;
    private List<Object> getUsertGuides() {
        List<Object> list = new ArrayList<>();
        list.add(R.mipmap.bg_loading1);
        list.add(R.mipmap.bg_loading2);
        list.add(R.mipmap.bg_loading3);
        return list;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        sgb();
    }

    @Override
    public void initData() {

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
        sgb
                .setIndicatorShow(false)
                .setTransformerClass(DepthTransformer.class)
                .setSource(getUsertGuides())
                .startScroll();

        sgb.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
