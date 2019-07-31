package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ForgetPwdBean;
import com.ipd.allpeopledemand.bean.SmsBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ForgetPwdContract;
import com.ipd.allpeopledemand.presenter.ForgetPwdPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.utils.VerifyUtils.isNumberAndLetter;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：忘记密码/修改密码
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class ForgetPwdActivity extends BaseActivity<ForgetPwdContract.View, ForgetPwdContract.Presenter> implements ForgetPwdContract.View {

    @BindView(R.id.tv_forget_pwd)
    TopView tvForgetPwd;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.et_captcha)
    EditText etCaptcha;
    @BindView(R.id.bt_captcha)
    SuperButton btCaptcha;
    @BindView(R.id.ll_captha)
    LinearLayout llCaptha;
    @BindView(R.id.et_pwd_code)
    EditText etPwdCode;
    @BindView(R.id.rv_forget_pwd)
    RippleView rvForgetPwd;
    @BindView(R.id.tv_test)
    TextView tvTest;

    private CountDownButtonHelper mCountDownHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public ForgetPwdContract.Presenter createPresenter() {
        return new ForgetPwdPresenter(this);
    }

    @Override
    public ForgetPwdContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvForgetPwd);

        tvTopTitle.setText(1 == getIntent().getIntExtra("modify_pwd", 1) ? "忘记密码" : "修改密码");
        mCountDownHelper = new CountDownButtonHelper(btCaptcha, 60);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        mCountDownHelper.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.bt_captcha, R.id.rv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_captcha:
                if (isFastClick()) {
                    if (etLoginCode.getText().toString().trim().length() > 0) {
                        TreeMap<String, String> smsMap = new TreeMap<>();
                        smsMap.put("telPhone", etLoginCode.getText().toString().trim());
                        smsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(smsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                        getPresenter().getSms(smsMap, true, false);
                    } else
                        ToastUtil.showShortToast("请填写号码!");
                }
                break;
            case R.id.rv_forget_pwd:
                if (isFastClick()) {
                    if (etLoginCode.getText().toString().trim().length() > 0 && etCaptcha.getText().toString().trim().length() > 0 && etPwdCode.getText().toString().trim().length() > 0) {
                        if (isNumberAndLetter(etPwdCode.getText().toString().trim())) {
                            TreeMap<String, String> forgetPwdMap = new TreeMap<>();
                            forgetPwdMap.put("telPhone", etLoginCode.getText().toString().trim());
                            forgetPwdMap.put("password", etPwdCode.getText().toString().trim());
                            forgetPwdMap.put("smsCode", etCaptcha.getText().toString().trim());
                            forgetPwdMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(forgetPwdMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                            getPresenter().getForgetPwd(forgetPwdMap, true, false);
                        } else
                            ToastUtil.showShortToast("密码为（数字+字母组合）");
                    } else
                        ToastUtil.showShortToast("请填写号码！");
                }
                break;
        }
    }

    @Override
    public void resultForgetPwd(ForgetPwdBean data) {
        if (data.getCode() == 200) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else
            ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultSms(SmsBean data) {
        if (data.getCode() == 200)
            mCountDownHelper.start();
        else
            ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
