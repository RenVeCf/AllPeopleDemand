package com.ipd.allpeopledemand.fragment;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.AccountActivity;
import com.ipd.allpeopledemand.activity.AttentionActivity;
import com.ipd.allpeopledemand.activity.CheckInActivity;
import com.ipd.allpeopledemand.activity.InformationActivity;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.activity.MyBuyActivity;
import com.ipd.allpeopledemand.activity.MyPushActivity;
import com.ipd.allpeopledemand.activity.SettingActivity;
import com.ipd.allpeopledemand.activity.ShareActivity;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.CheckInBean;
import com.ipd.allpeopledemand.bean.CheckInLayoutBean;
import com.ipd.allpeopledemand.bean.UserInfoBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.CheckInContract;
import com.ipd.allpeopledemand.presenter.CheckInPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.ALL_PEOPLE;
import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_96;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.DateUtils.getTodayDateTime;

/**
 * Description ：我的
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MyFragment extends BaseFragment<CheckInContract.View, CheckInContract.Presenter> implements CheckInContract.View {
    @BindView(R.id.tv_my)
    TopView tvMy;
    @BindView(R.id.riv_head)
    RadiusImageView rivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_all_people_code)
    TextView tvAllPeopleCode;
    @BindView(R.id.ib_check_in)
    ImageButton ibCheckIn;
    @BindView(R.id.tv_online_time)
    TextView tvOnlineTime;
    @BindView(R.id.tv_rank_lable)
    TextView tvRankLable;
    @BindView(R.id.tv_certification_lable)
    TextView tvCertificationLable;
    @BindView(R.id.stv_information)
    SuperTextView stvInformation;
    @BindView(R.id.stv_account)
    SuperTextView stvAccount;
    @BindView(R.id.stv_push)
    SuperTextView stvPush;
    @BindView(R.id.stv_attention)
    SuperTextView stvAttention;
    @BindView(R.id.stv_buy)
    SuperTextView stvBuy;
    @BindView(R.id.stv_share)
    SuperTextView stvShare;
    @BindView(R.id.stv_setting)
    SuperTextView stvSetting;

    private List<CheckInLayoutBean.DataBean.SignListBean> signListBean = new ArrayList<>();
    private int continueDays = 0;
    private String isSign = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public CheckInContract.Presenter createPresenter() {
        return new CheckInPresenter(mContext);
    }

    @Override
    public CheckInContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(false).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        ImmersionBar.setTitleBar(getActivity(), tvMy);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        TreeMap<String, String> userInfoMap = new TreeMap<>();
        userInfoMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        userInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(userInfoMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getUserInfo(userInfoMap, false, false);

        TreeMap<String, String> checkInLayoutMap = new TreeMap<>();
        checkInLayoutMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        checkInLayoutMap.put("signDate", getTodayDateTime());
        checkInLayoutMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(checkInLayoutMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getCheckInLayout(checkInLayoutMap, true, false);

        Glide.with(this).load(BASE_LOCAL_URL + SPUtil.get(getContext(), AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);
        tvName.setText(SPUtil.get(getContext(), NAME, "") + "");
        tvAllPeopleCode.setText("(" + SPUtil.get(getContext(), ALL_PEOPLE, "") + ")");
        tvOnlineTime.setText("浏览时长：12小时");//TODO  后台没有,让写死
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_92:
                    tvName.setText(SPUtil.get(getContext(), NAME, "") + "");
                    Glide.with(this).load(BASE_LOCAL_URL + SPUtil.get(getContext(), AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);
                    break;
                case REQUEST_CODE_96:
                    initData();
                    break;
            }
        }
    }

    @OnClick({R.id.ib_check_in, R.id.stv_information, R.id.stv_account, R.id.stv_push, R.id.stv_attention, R.id.stv_buy, R.id.stv_share, R.id.stv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_check_in:
                //签到
                startActivityForResult(new Intent(getContext(), CheckInActivity.class).putParcelableArrayListExtra("signListBean", (ArrayList<? extends Parcelable>) signListBean).putExtra("continueDays", continueDays).putExtra("isSign", isSign), REQUEST_CODE_96);
                break;
            case R.id.stv_information:
                //个人资料
                startActivityForResult(new Intent(getContext(), InformationActivity.class), REQUEST_CODE_92);
                break;
            case R.id.stv_account:
                //我的账户
                startActivity(new Intent(getContext(), AccountActivity.class));
                break;
            case R.id.stv_push:
                //我的发布
                startActivity(new Intent(getContext(), MyPushActivity.class));
                break;
            case R.id.stv_attention:
                //我的关注
                startActivity(new Intent(getContext(), AttentionActivity.class));
                break;
            case R.id.stv_buy:
                //我的购买
                startActivity(new Intent(getContext(), MyBuyActivity.class));
                break;
            case R.id.stv_share:
                //分享好友
                startActivity(new Intent(getContext(), ShareActivity.class));
                break;
            case R.id.stv_setting:
                //设置
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void resultCheckInLayout(CheckInLayoutBean data) {
        switch (data.getCode()) {
            case 200:
                signListBean.clear();
                signListBean.addAll(data.getData().getSignList());
                continueDays = data.getData().getContinueDays();
                isSign = data.getData().isIsSign();
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultCheckIn(CheckInBean data) {

    }

    @Override
    public void resultUserInfo(UserInfoBean data) {
        if (data.getData().getUserLabel().size() > 0) {
            if (data.getData().getUserLabel().size() < 2) {
                tvRankLable.setText(data.getData().getUserLabel().get(0).getName());
                tvCertificationLable.setVisibility(View.GONE);
            } else if (data.getData().getUserLabel().size() < 3) {
                tvRankLable.setText(data.getData().getUserLabel().get(0).getName());
                tvCertificationLable.setText(data.getData().getUserLabel().get(1).getName());
            }
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
