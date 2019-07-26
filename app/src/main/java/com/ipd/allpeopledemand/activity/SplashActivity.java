package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.LoadingBean;
import com.ipd.allpeopledemand.contract.LoadingContract;
import com.ipd.allpeopledemand.presenter.LoadingPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.FIRST_APP;

/**
 * Description ：启动页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class SplashActivity extends BaseActivity<LoadingContract.View, LoadingContract.Presenter> implements LoadingContract.View {

    /**
     *
     *----------Dragon be here!----------/
     * 　　　┏┓　　　┏┓
     * 　　┏┛┻━━━┛┻┓
     * 　　┃　　　　　　　┃
     * 　　┃　　　━　　　┃
     * 　　┃　┳┛　┗┳　┃
     * 　　┃　　　　　　　┃
     * 　　┃　　　┻　　　┃
     * 　　┃　　　　　　　┃
     * 　　┗━┓　　　┏━┛
     * 　　　　┃　　　┃神兽保佑
     * 　　　　┃　　　┃代码无BUG！
     * 　　　　┃　　　┗━━━┓
     * 　　　　┃　　　　　　　┣┓
     * 　　　　┃　　　　　　　┏┛
     * 　　　　┗┓┓┏━┳┓┏┛
     * 　　　　　┃┫┫　┃┫┫
     * 　　　　　┗┻┛　┗┻┛
     * ━━━━━━神兽出没━━━━━━
     */

    /**
     * 默认启动页过渡时间
     */
    private static final int DEFAULT_SPLASH_DURATION_MILLIS = 1500;
    private long firstTime = 0;
    protected LinearLayout mWelcomeLayout;
    private List<LoadingBean.DataBean.GuidePageListBean> guidePageListBean = new ArrayList<>();//引导页数据源

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
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
        mWelcomeLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mWelcomeLayout.setLayoutParams(params);
        mWelcomeLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mWelcomeLayout);
        initSplashView(getSplashImgResId());
        startSplash(true);
    }

    @Override
    public void initData() {
        if ((SPUtil.get(SplashActivity.this, FIRST_APP, "") + "").equals("")) {
            TreeMap<String, String> loadingMap = new TreeMap<>();
            loadingMap.put("password", "F9A75BB045D75998E1509B75ED3A5225");
            getPresenter().getLoading(loadingMap, false, false);
        }
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

    /**
     * 初始化启动界面
     *
     * @param splashImgResId 背景资源图片资源ID
     */
    protected void initSplashView(int splashImgResId) {
        if (splashImgResId != 0) {
            Utils.setBackground(this, mWelcomeLayout, splashImgResId);
        }
    }

    /**
     * 初始化启动界面背景图片
     *
     * @return 背景图片资源ID
     */
    protected int getSplashImgResId() {
        return R.mipmap.bg_splash;
    }

    /**
     * 开启过渡
     *
     * @param enableAlphaAnim 是否启用渐近动画
     */
    protected void startSplash(boolean enableAlphaAnim) {
        if (enableAlphaAnim)
            startSplashAnim(new AlphaAnimation(0.2F, 1.0F));
        else
            startSplashAnim(new AlphaAnimation(1.0F, 1.0F));
    }

    /**
     * 开启引导过渡动画
     *
     * @param anim
     */
    private void startSplashAnim(Animation anim) {
        Utils.checkNull(anim, "Splash Animation can not be null");
        anim.setDuration(DEFAULT_SPLASH_DURATION_MILLIS);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 启动完毕
                if ((SPUtil.get(SplashActivity.this, FIRST_APP, "") + "").equals("")) {
                    startActivity(new Intent(SplashActivity.this, LoadingActivity.class).putParcelableArrayListExtra("usertGuides", (ArrayList<? extends Parcelable>) guidePageListBean));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
        mWelcomeLayout.startAnimation(anim);
    }

    @Override
    public void resultLoading(LoadingBean data) {
        if (data.getCode() == 200) {
            if (data.getData().getGuidePageList().size() > 0) {
                guidePageListBean.addAll(data.getData().getGuidePageList());
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
