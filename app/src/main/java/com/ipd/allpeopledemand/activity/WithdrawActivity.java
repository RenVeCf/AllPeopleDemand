package com.ipd.allpeopledemand.activity;

import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class WithdrawActivity extends BaseActivity {

    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_withdraw_fee)
    SuperTextView tvWithdrawFee;
    @BindView(R.id.tv_bind_wechat)
    SuperTextView tvBindWechat;
    @BindView(R.id.tv_is_wechat)
    SuperTextView tvIsWechat;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_withdraw)
    TopView tvWithdraw;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
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
        ImmersionBar.setTitleBar(this, tvWithdraw);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.cl_1, R.id.rv_withdraw, R.id.tv_bind_wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_1:
                break;
            case R.id.rv_withdraw:
                break;
            case R.id.tv_bind_wechat:
                break;
        }
    }
}
