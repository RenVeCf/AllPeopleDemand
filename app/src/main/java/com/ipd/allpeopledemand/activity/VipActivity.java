package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：开通会员
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/9/23.
 */
public class VipActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
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
        ImmersionBar.setTitleBar(this, tvVip);
    }

    @Override
    public void initData() {
        Glide.with(this).load(BASE_LOCAL_URL + SPUtil.get(this, AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(ivHead);
        name.setText(SPUtil.get(this, NAME, "") + "");
        fee.setText("8.0元/月");
        time.setText("到期时间：2020-11-21");
        tvDayFee.setText(Html.fromHtml("每天仅需: <font color=\"#E71B64\">" + "￥0.33" + "</font>"));
        tvFee.setText("价格：￥8.0");
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_vip, R.id.tv_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_vip:
                if (cbProtocol.isChecked()) {

                } else
                    ToastUtil.showShortToast("请同意用户协议！");
                break;
            case R.id.tv_protocol:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 3));
                break;
        }
    }
}
