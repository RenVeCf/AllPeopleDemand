package com.ipd.allpeopledemand.wxapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.L;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.allpeopledemand.common.config.IConstants.WECHAT_BT_TYPE;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    @BindView(R.id.tv_pay_status)
    TopView tvPayStatus;
    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.iv_pay)
    ImageView ivPay;

    private int payStatus;//1 : 成功，2 :失败

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;
    private int type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_status;
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
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        ImmersionBar.setTitleBar(this, tvPayStatus);

        ivTopBack.setImageResource(R.mipmap.ic_back_white);

        api = WXAPIFactory.createWXAPI(this, "wx57313d36c4b4d0d7");
        api.handleIntent(getIntent(), this);

        type = Integer.parseInt(SPUtil.get(this, WECHAT_BT_TYPE, 2) + "");
        switch (type) {
            case 1:
                btConfirm.setText("返回详情查看联系方式");
                break;
            case 2:
            case 3:
                btConfirm.setText("发布成功");
                break;
            case 4:
                btConfirm.setText("充值VIP成功");
                break;
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onResp(BaseResp resp) {
        L.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.errCode == 0) {
            ivPay.setImageResource(R.mipmap.ic_pay_success);
            tvPay.setText("支付成功！");
            payStatus = 1;
        } else {
            tvPay.setText("支付失败！");
            btConfirm.setVisibility(View.GONE);
            payStatus = 2;
        }
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("支付结果");
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
//        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("pay_status", payStatus));
        finish();
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @OnClick({R.id.ll_top_back, R.id.bt_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
            case R.id.bt_confirm:
                if (payStatus == 1 && type == 2) {
                    Intent intent = new Intent("clear_push");
                    intent.putExtra("is_clear", 1);
                    this.sendBroadcast(intent);
                    finish();
                } else {
                    setResult(RESULT_OK, new Intent().putExtra("pay_status", payStatus));
                    finish();
                }
                break;
        }
    }
}
