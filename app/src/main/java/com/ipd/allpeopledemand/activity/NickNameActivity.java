package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.InformationBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.InformationContract;
import com.ipd.allpeopledemand.presenter.InformationPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：修改昵称
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class NickNameActivity extends BaseActivity<InformationContract.View, InformationContract.Presenter> implements InformationContract.View {

    @BindView(R.id.tv_modify_nickname)
    TopView tvModifyNickname;
    @BindView(R.id.et_modify_nickname)
    EditText etModifyNickname;

    @Override
    public int getLayoutId() {
        return R.layout.activity_nick_name;
    }

    @Override
    public InformationContract.Presenter createPresenter() {
        return new InformationPresenter(this);
    }

    @Override
    public InformationContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyNickname);

        if (!"".equals(SPUtil.get(this, NAME, "") + ""))
            etModifyNickname.setText(SPUtil.get(this, NAME, "") + "");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.rv_modify_nickname)
    public void onViewClicked() {
        if (!"".equals(etModifyNickname.getText().toString().trim())) {
            TreeMap<String, String> informationMap = new TreeMap<>();
            informationMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
            informationMap.put("userCall", etModifyNickname.getText().toString().trim());
            informationMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(informationMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
            getPresenter().getInformation(informationMap, true, false);
        } else
            ToastUtil.showShortToast("请填写要修改的昵称!");
    }

    @Override
    public void resultInformation(InformationBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                SPUtil.put(this, NAME, etModifyNickname.getText().toString().trim());
                setResult(RESULT_OK, new Intent().putExtra("nickname", etModifyNickname.getText().toString().trim()));
                finish();
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
    public void resultUploadImg(UploadImgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
