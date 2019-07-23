package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.RegisterBean;
import com.ipd.allpeopledemand.bean.SmsBean;
import com.ipd.allpeopledemand.common.view.RegisterDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.RegisterContract;
import com.ipd.allpeopledemand.presenter.RegisterPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.AGE;
import static com.ipd.allpeopledemand.common.config.IConstants.ALL_PEOPLE;
import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.MARITAL_STATUS;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.PHONE;
import static com.ipd.allpeopledemand.common.config.IConstants.SEX;
import static com.ipd.allpeopledemand.common.config.IConstants.TOKEN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：注册
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class RegisterActivity extends BaseActivity<RegisterContract.View, RegisterContract.Presenter> implements RegisterContract.View {

    @BindView(R.id.tv_register)
    TopView tvRegister;
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
    @BindView(R.id.cb_protocol)
    CheckBox cbProtocol;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.ll_protocol)
    LinearLayout llProtocol;
    @BindView(R.id.rv_register)
    RippleView rvRegister;

    private CountDownButtonHelper mCountDownHelper;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterContract.Presenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public RegisterContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvRegister);

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

    //注册成功
    private void showPopWindow(String allPeopleCode) {
        new RegisterDialog(this, 100, allPeopleCode) {
            @Override
            public void goPay() {
                SPUtil.put(RegisterActivity.this, IS_LOGIN, "is_login");
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        }.show();
    }

    @OnClick({R.id.bt_captcha, R.id.tv_protocol, R.id.rv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_captcha:
                if (etLoginCode.getText().toString().trim().length() > 0) {
                    TreeMap<String, String> smsMap = new TreeMap<>();
                    smsMap.put("telPhone", etLoginCode.getText().toString().trim());
                    smsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(smsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                    getPresenter().getSms(smsMap, true, false);
                } else
                    ToastUtil.showShortToast("请填写号码!");
                break;
            case R.id.tv_protocol:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 3));
                break;
            case R.id.rv_register:
                if (cbProtocol.isChecked())
                    if (etLoginCode.getText().toString().trim().length() > 0 && etCaptcha.getText().toString().trim().length() > 0 && etPwdCode.getText().toString().trim().length() > 0) {
                        TreeMap<String, String> registerMap = new TreeMap<>();
                        registerMap.put("telPhone", etLoginCode.getText().toString().trim());
                        registerMap.put("password", etPwdCode.getText().toString().trim());
                        registerMap.put("smsCode", etCaptcha.getText().toString().trim());
                        registerMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(registerMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                        getPresenter().getRegister(registerMap, true, false);
                    } else
                        ToastUtil.showShortToast("请填写号码！");
                else
                    ToastUtil.showShortToast("请同意用户协议！");
                break;
        }
    }

    @Override
    public void resultRegister(RegisterBean data) {
        if (data.getCode() == 200) {
            SPUtil.put(this, TOKEN, data.getData().getToken());
            SPUtil.put(this, USER_ID, data.getData().getUser().getUserId() + "");
            SPUtil.put(this, AVATAR, data.getData().getUser().getAvatar());
            SPUtil.put(this, NAME, data.getData().getUser().getUserCall());
            SPUtil.put(this, PHONE, data.getData().getUser().getTelPhone());
            SPUtil.put(this, ALL_PEOPLE, data.getData().getUser().getNationalNum());
            String sex = "";
            switch (data.getData().getUser().getSex()) {
                case "1":
                    sex = "男";
                    break;
                case "2":
                    sex = "女";
                    break;
            }
            SPUtil.put(this, SEX, sex);
            SPUtil.put(this, AGE, data.getData().getUser().getAge() == 0 ? "" : data.getData().getUser().getAge() + "岁");
            String maritalStatus = "";
            switch (data.getData().getUser().getSex()) {
                case "1":
                    maritalStatus = "未婚";
                    break;
                case "2":
                    maritalStatus = "已婚";
                    break;
            }
            SPUtil.put(this, MARITAL_STATUS, maritalStatus);
            showPopWindow(data.getData().getUser().getNationalNum());
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
