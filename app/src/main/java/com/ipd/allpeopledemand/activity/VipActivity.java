package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.aliPay.AliPay;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.OpenMemberBean;
import com.ipd.allpeopledemand.common.view.BottomPayDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.OpenMemberContract;
import com.ipd.allpeopledemand.presenter.OpenMemberPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.IConstants.WECHAT_BT_TYPE;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;

/**
 * Description ：开通会员
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/9/23.
 */
public class VipActivity extends BaseActivity<OpenMemberContract.View, OpenMemberContract.Presenter> implements OpenMemberContract.View {

    @BindView(R.id.cb_protocol)
    CheckBox cbProtocol;
    @BindView(R.id.tv_day_fee)
    TextView tvDayFee;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.fee)
    TextView fee;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.tv_vip)
    TopView tvVip;
    @BindView(R.id.iv_head)
    RadiusImageView ivHead;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.bt_vip)
    Button btVip;

    private String stopTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
    }

    @Override
    public OpenMemberContract.Presenter createPresenter() {
        return new OpenMemberPresenter(this);
    }

    @Override
    public OpenMemberContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvVip);

        stopTime = getIntent().getStringExtra("stop_time");
        if (isEmpty(stopTime)) {
            btVip.setText("立即开通");
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 30);//计算30天后的时间
            String str2 = s.format(c.getTime());
            stopTime = str2;
        } else
            btVip.setText("续费VIP");
    }

    @Override
    public void initData() {
        Glide.with(this).load(BASE_LOCAL_URL + SPUtil.get(this, AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(ivHead);
        name.setText(SPUtil.get(this, NAME, "") + "");
        fee.setText("8.0元/月");
        time.setText("到期时间：" + stopTime);
        tvDayFee.setText(Html.fromHtml("每天仅需: <font color=\"#E71B64\">" + "￥0.27" + "</font>"));
        tvFee.setText("价格：￥8.0");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.bt_vip, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.bt_vip:
                if (cbProtocol.isChecked()) {
                    new BottomPayDialog(this, 0) {
                        @Override
                        public void goPay(int payType) {
                            switch (payType) {
                                case 1://支付宝
                                    TreeMap<String, String> openMemberMap = new TreeMap<>();
                                    openMemberMap.put("userId", SPUtil.get(VipActivity.this, USER_ID, "") + "");
                                    openMemberMap.put("payway", "2");
                                    openMemberMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(openMemberMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                    getPresenter().getOpenMember(openMemberMap, true, false);
                                    break;
                                case 2://微信
                                    TreeMap<String, String> openMemberMap1 = new TreeMap<>();
                                    openMemberMap1.put("userId", SPUtil.get(VipActivity.this, USER_ID, "") + "");
                                    openMemberMap1.put("payway", "1");
                                    openMemberMap1.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(openMemberMap1.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                    getPresenter().getOpenMember(openMemberMap1, true, false);
                                    break;
                                default:
                                    ToastUtil.showShortToast("请选择支付方式！");
                                    break;
                            }
                        }
                    }.show();
                } else
                    ToastUtil.showShortToast("请同意用户协议！");
                break;
            case R.id.tv_protocol:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 3));
                break;
        }
    }

    @Override
    public void resultOpenMember(OpenMemberBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                if (!isEmpty(data.getData().getSign2()))
                    new AliPay(this, data.getData().getSign2(), 4);
                else {
                    SPUtil.put(this, WECHAT_BT_TYPE, 4);

                    IWXAPI api = WXAPIFactory.createWXAPI(this, null);
                    api.registerApp("wx57313d36c4b4d0d7");
                    PayReq req = new PayReq();
                    req.appId = data.getData().getSign1().getAppid();//你的微信appid
                    req.partnerId = data.getData().getSign1().getPartnerid();//商户号
                    req.prepayId = data.getData().getSign1().getPrepayid();//预支付交易会话ID
                    req.nonceStr = data.getData().getSign1().getNoncestr();//随机字符串
                    req.timeStamp = data.getData().getSign1().getTimestamp() + "";//时间戳
                    req.packageValue = data.getData().getSign1().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
                    req.sign = data.getData().getSign1().getSign();//签名
                    //  req.extData         = "app data"; // optional
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                }
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
