package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.CacheUtil;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：设置
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/27.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_setting)
    TopView tvSetting;
    @BindView(R.id.stv_version)
    SuperTextView stvVersion;
    @BindView(R.id.stv_cache_clear)
    SuperTextView stvCacheClear;
    @BindView(R.id.stv_platform_introduction)
    SuperTextView stvPlatformIntroduction;
    @BindView(R.id.rv_out)
    RippleView rvOut;

    private boolean isVersion = false; //有新版本 : true, 没有新版本 : false

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
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
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSetting);
    }

    @Override
    public void initData() {
        if (isVersion) {
            stvVersion.setRightTextColor(R.color.white);
            stvVersion.setRightString("发现新版本");
        } else {
            stvVersion.setRightTextColor(R.color.tx_bottom_navigation);
            stvVersion.setRightString("已是最新版本");
        }
        try {
            stvCacheClear.setRightString(CacheUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_version, R.id.stv_cache_clear, R.id.stv_platform_introduction, R.id.rv_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_version://版本
                break;
            case R.id.stv_cache_clear://清缓存
                CacheUtil.clearAllCache(this);
                try {
                    stvCacheClear.setRightString(CacheUtil.getTotalCacheSize(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stv_platform_introduction://App介绍
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 2));
                break;
            case R.id.rv_out:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
