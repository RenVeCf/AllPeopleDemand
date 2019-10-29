package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.WechatBindBean;
import com.ipd.allpeopledemand.bean.WithdrawBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.WithdrawContract;
import com.ipd.allpeopledemand.presenter.WithdrawPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.HashMap;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class WithdrawActivity extends BaseActivity<WithdrawContract.View, WithdrawContract.Presenter> implements WithdrawContract.View, PlatformActionListener {

    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_withdraw_fee)
    SuperTextView tvWithdrawFee;
    @BindView(R.id.tv_bind_wechat)
    SuperTextView tvBindWechat;
    @BindView(R.id.tv_is_wechat)
    SuperTextView tvIsWechat;
    @BindView(R.id.tv_withdraw)
    TopView tvWithdraw;

    private String openid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public WithdrawContract.Presenter createPresenter() {
        return new WithdrawPresenter(this);
    }

    @Override
    public WithdrawContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvWithdraw);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Platform platform = (Platform) msg.obj;
                    String response = platform.getDb().exportData();
                    WechatBindBean jsonTopicsBean = new Gson().fromJson(response, WechatBindBean.class);

                    openid = jsonTopicsBean.getOpenid();
                    tvBindWechat.setVisibility(View.GONE);
                    tvIsWechat.setVisibility(View.VISIBLE);
                    tvIsWechat.setLeftString(platform.getDb().getUserName());
                    Glide.with(ApplicationUtil.getContext()).load(platform.getDb().getUserIcon()).apply(new RequestOptions()).into(tvIsWechat.getLeftIconIV());
                    break;
                case 2:
                    ToastUtil.showLongToast("授权失败");
                    break;
                case 3:
                    ToastUtil.showLongToast("授权取消");
                    break;
            }
        }
    };

    @OnClick({R.id.rv_withdraw, R.id.tv_bind_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rv_withdraw:
                if (isFastClick()) {
                    if (!isEmpty(openid)) {
                        if (!isEmpty(tvWithdrawFee.getCenterEditValue()) && Integer.parseInt(tvWithdrawFee.getCenterEditValue()) >= 10 && Integer.parseInt(tvWithdrawFee.getCenterEditValue()) <= 1000) {
                            TreeMap<String, String> openMemberMap = new TreeMap<>();
                            openMemberMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                            openMemberMap.put("openId", openid);
                            openMemberMap.put("money", tvWithdrawFee.getCenterEditValue());
                            openMemberMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(openMemberMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                            getPresenter().getWithdraw(openMemberMap, false, false);
                        } else
                            ToastUtil.showLongToast("提现金额为10-1000元");
                    } else
                        ToastUtil.showLongToast("请选择提现的微信用户");
                }
                break;
            case R.id.tv_bind_wechat:
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.SSOSetting(false);
                if (!wechat.isClientValid())
                    ToastUtil.showLongToast("微信未安装,请先安装微信");
                authorize(wechat);
                break;
        }
    }

    /**
     * 授权
     *
     * @param platform
     */
    private void authorize(Platform platform) {
        if (platform == null) {
            return;
        }
        if (platform.isAuthValid()) //如果授权就删除授权资料
            platform.removeAccount(true);
        platform.showUser(null); //授权并获取用户信息
    }

    @Override
    public void resultWithdraw(WithdrawBean data) {
        ToastUtil.showShortToast(data.getMsg());
        if (data.getCode() == 900) {
            //清除所有临时储存
            SPUtil.clear(ApplicationUtil.getContext());
            ApplicationUtil.getManager().finishActivity(MainActivity.class);
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Message message = Message.obtain();
        message.what = 2;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Message message = Message.obtain();
        message.what = 3;
        message.obj = platform;
        mHandler.sendMessage(message);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
