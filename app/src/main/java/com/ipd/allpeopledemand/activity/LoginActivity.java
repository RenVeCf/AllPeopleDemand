package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.RegisterBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.LoginContract;
import com.ipd.allpeopledemand.presenter.LoginPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.button.RippleView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.AGE;
import static com.ipd.allpeopledemand.common.config.IConstants.ALL_PEOPLE;
import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.HOW_PAGE;
import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.MARITAL_STATUS;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.PHONE;
import static com.ipd.allpeopledemand.common.config.IConstants.SEX;
import static com.ipd.allpeopledemand.common.config.IConstants.TOKEN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：登录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.tv_login)
    TopView tvLogin;
    @BindView(R.id.ll_top_back)
    LinearLayout llTopBack;
    @BindView(R.id.et_login_code)
    EditText etLoginCode;
    @BindView(R.id.et_pwd_code)
    EditText etPwdCode;
    @BindView(R.id.rv_login)
    RippleView rvLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvLogin);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.tv_forget_pwd, R.id.tv_register, R.id.rv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                if (isFastClick()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
            case R.id.tv_forget_pwd:
                if (isFastClick())
                    startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.tv_register:
                if (isFastClick())
                    startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.rv_login:
                if (isFastClick()) {
                    if (etLoginCode.getText().toString().trim().length() > 0 && etPwdCode.getText().toString().trim().length() > 0) {
                        TreeMap<String, String> loginMap = new TreeMap<>();
                        loginMap.put("telPhone", etLoginCode.getText().toString().trim());
                        loginMap.put("password", etPwdCode.getText().toString().trim());
                        loginMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(loginMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                        getPresenter().getLogin(loginMap, true, false);
                    } else
                        ToastUtil.showShortToast("请填写号码！");
                }
                break;
        }
    }

    @Override
    public void resultLogin(RegisterBean data) {
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

            startActivity(new Intent(this, MainActivity.class).putExtra("howFragment", Integer.parseInt(SPUtil.get(this, HOW_PAGE, "0") + "")));
            SPUtil.put(this, IS_LOGIN, "is_login");
            finish();
        } else
            ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
