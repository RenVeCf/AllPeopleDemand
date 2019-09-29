package com.ipd.allpeopledemand.wxapi;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.WithdrawBean;
import com.ipd.allpeopledemand.contract.WithdrawContract;
import com.ipd.allpeopledemand.presenter.WithdrawPresenter;
import com.ipd.allpeopledemand.utils.L;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import cn.sharesdk.wechat.utils.WechatResp;
import io.reactivex.ObservableTransformer;

/**
 * Description ：微信授权
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/9/29.
 */
public class WXEntryActivity extends BaseActivity<WithdrawContract.View, WithdrawContract.Presenter> implements WithdrawContract.View, IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wxentry;
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
        api = WXAPIFactory.createWXAPI(this, "wxbb948d62bc17b798", false);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(getIntent(), this);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case WechatResp.ErrCode.ERR_OK: //成功
                if (baseResp instanceof SendAuth.Resp) {
                    String code = ((SendAuth.Resp) baseResp).code;
                    L.i("code = " + code);

                    Intent intent = new Intent("authlogin");
                    intent.putExtra("code", code);
                    WXEntryActivity.this.sendBroadcast(intent);
                    finish();
                }
                break;
            case WechatResp.ErrCode.ERR_USER_CANCEL: //取消
                L.i("ERR_USER_CANCEL");
                finish();
                break;
            case WechatResp.ErrCode.ERR_AUTH_DENIED: //拒绝
                L.i("ERR_AUTH_DENIED");
                finish();
                break;
            default: //返回
                L.i("default");
                finish();
                break;
        }
    }

    @Override
    public void resultWithdraw(WithdrawBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
