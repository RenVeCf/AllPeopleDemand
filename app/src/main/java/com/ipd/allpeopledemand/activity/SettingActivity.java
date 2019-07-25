package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.CheckVersionBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.CheckVersionContract;
import com.ipd.allpeopledemand.presenter.CheckVersionPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.CacheUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.PACKAGE_NAME;
import static com.ipd.allpeopledemand.utils.AppUtils.getAppVersionName;

/**
 * Description ：设置
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/27.
 */
public class SettingActivity extends BaseActivity<CheckVersionContract.View, CheckVersionContract.Presenter> implements CheckVersionContract.View {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public CheckVersionContract.Presenter createPresenter() {
        return new CheckVersionPresenter(this);
    }

    @Override
    public CheckVersionContract.View createView() {
        return this;
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
        TreeMap<String, String> checkVersionMap = new TreeMap<>();
        checkVersionMap.put("type", "1");
        checkVersionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(checkVersionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getCheckVersion(checkVersionMap, false, false);

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

    @Override
    public void resultCheckVersion(CheckVersionBean data) {
        if (data.getCode() == 200) {
            if (getAppVersionName(this, PACKAGE_NAME).equals(data.getData().getVersion().getVersionNo())) {
                stvVersion.setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation));
                stvVersion.setRightString("已是最新版本");
            } else {
                stvVersion.setRightTextColor(getResources().getColor(R.color.black));
                stvVersion.setRightString("发现新版本");
            }
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
