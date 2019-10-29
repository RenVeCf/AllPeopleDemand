package com.ipd.allpeopledemand.activity;

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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：支付结果
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class PayStatusActivity extends BaseActivity {

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

    private int payStatus = 0;//1 : 成功，2 :失败
    private int btTx = 0;//1 : 返回详情查看联系方式，2 :发布成功, 3: 充值VIP成功

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

        btTx = getIntent().getIntExtra("bt_tx", 0);
        switch (btTx) {
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
        payStatus = getIntent().getIntExtra("pay_status", 0);
        switch (payStatus) {
            case 1:
                ivPay.setImageResource(R.mipmap.ic_pay_success);
                tvPay.setText("支付成功！");
                break;
            case 2:
                tvPay.setText("支付失败！");
                btConfirm.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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
                if (payStatus == 1 && btTx == 2) {
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
