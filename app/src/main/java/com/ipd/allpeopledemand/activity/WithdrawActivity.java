package com.ipd.allpeopledemand.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.WechatBindBean;
import com.ipd.allpeopledemand.bean.WechatUserInfoBean;
import com.ipd.allpeopledemand.bean.WithdrawBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.WithdrawContract;
import com.ipd.allpeopledemand.presenter.WithdrawPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.L;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.IOException;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class WithdrawActivity extends BaseActivity<WithdrawContract.View, WithdrawContract.Presenter> implements WithdrawContract.View {

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

    private IWXAPI api;
    private ReceiveBroadCast receiveBroadCast;
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
                            getPresenter().getWithdraw(openMemberMap, true, false);
                        } else
                            ToastUtil.showLongToast("提现金额为10-1000元");
                    } else
                        ToastUtil.showLongToast("请选择提现的微信用户");
                }
                break;
            case R.id.tv_bind_wechat:
                api = WXAPIFactory.createWXAPI(this, "wxbb948d62bc17b798", true);
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wx_withdraw";
                api.sendReq(req);
                break;
        }
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

    class ReceiveBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(receiveBroadCast);
            String url = "code=" + intent.getStringExtra("code") + "&grant_type=authorization_code";
            L.i("url = " + url);
            Request r = new Request.Builder()
                    .url("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbb948d62bc17b798&secret=7318c04d463f41c7e61226cd67cad1c8&" + url)
                    .get()
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(r).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtil.showShortToast(e + "");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    WechatBindBean jsonTopicsBean = new Gson().fromJson(response.body().string(), WechatBindBean.class);
                    openid = jsonTopicsBean.getOpenid();

                    getPersonMessage(jsonTopicsBean.getAccess_token(), openid);
                }
            });
        }
    }

    //获取微信个人资料
    private void getPersonMessage(String access_token, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;

        Request r = new Request.Builder()
                .url(url)
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(r).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtil.showShortToast(e + "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                WechatUserInfoBean jsonTopicsBean = new Gson().fromJson(response.body().string(), WechatUserInfoBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvBindWechat.setVisibility(View.GONE);
                        tvIsWechat.setVisibility(View.VISIBLE);
                        tvIsWechat.setLeftString(jsonTopicsBean.getNickname());
                        Glide.with(ApplicationUtil.getContext()).load(jsonTopicsBean.getHeadimgurl()).apply(new RequestOptions()).into(tvIsWechat.getLeftIconIV());
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("authlogin");
        registerReceiver(receiveBroadCast, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiveBroadCast);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
