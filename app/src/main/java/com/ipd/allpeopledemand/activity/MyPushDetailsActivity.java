package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.MyPushCollectionBean;
import com.ipd.allpeopledemand.bean.MyPushDemandTypeBean;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MyPushDetailsContract;
import com.ipd.allpeopledemand.presenter.MyPushDetailsPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：我的发布详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MyPushDetailsActivity extends BaseActivity<MyPushDetailsContract.View, MyPushDetailsContract.Presenter> implements MyPushDetailsContract.View {

    @BindView(R.id.tv_my_push_details)
    TopView tvMyPushDetails;
    @BindView(R.id.riv_title)
    RadiusImageView rivTitle;
    @BindView(R.id.tv_synopsis)
    ExpandableTextView tvSynopsis;
    @BindView(R.id.riv_head)
    RadiusImageView rivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_read_num)
    TextView tvReadNum;
    @BindView(R.id.cb_collection)
    CheckBox cbCollection;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_contact_name)
    TextView tvContactName;
    @BindView(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @BindView(R.id.bt_obtained_push)
    Button btObtainedPush;
    @BindView(R.id.bt_edit_push)
    Button btEditPush;

    private int pushType = 1;//1.上架 2.已下架
    private int releaseId; //发布id
    private MyPushDetailsBean.DataBean.ReleaseBean myPushDetailsBean = new MyPushDetailsBean.DataBean.ReleaseBean();

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_push_details;
    }

    @Override
    public MyPushDetailsContract.Presenter createPresenter() {
        return new MyPushDetailsPresenter(this);
    }

    @Override
    public MyPushDetailsContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMyPushDetails);

        pushType = getIntent().getIntExtra("push_type", 0);
        releaseId = getIntent().getIntExtra("releaseId", 0);

        switch (pushType) {
            case 1:
                btObtainedPush.setText("立即下架");
                break;
            case 2:
                btObtainedPush.setText("重新上架");
                break;
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> myPushDetailsMap = new TreeMap<>();
        myPushDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        myPushDetailsMap.put("releaseId", releaseId + "");
        myPushDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(myPushDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getMyPushDetails(myPushDetailsMap, true, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_95:
                    initData();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.cb_collection, R.id.bt_obtained_push, R.id.bt_edit_push, R.id.ll_top_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_collection:
                TreeMap<String, String> myPushCollectionMap = new TreeMap<>();
                myPushCollectionMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                myPushCollectionMap.put("releaseId", releaseId + "");
                myPushCollectionMap.put("isFollow", cbCollection.isChecked() ? "2" : "1");
                myPushCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(myPushCollectionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMyPushCollection(myPushCollectionMap, true, false);

                break;
            case R.id.bt_obtained_push:
                TreeMap<String, String> myPushDemandTypeMap = new TreeMap<>();
                myPushDemandTypeMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                myPushDemandTypeMap.put("releaseId", releaseId + "");
                switch (pushType) {
                    case 1:
                        myPushDemandTypeMap.put("status", "2");
                        break;
                    case 2:
                        myPushDemandTypeMap.put("status", "1");
                        break;
                }
                myPushDemandTypeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(myPushDemandTypeMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMyPushDemandType(myPushDemandTypeMap, true, false);
                break;
            case R.id.bt_edit_push:
                startActivityForResult(new Intent(this, EditMyPushActivity.class).putExtra("details", myPushDetailsBean), REQUEST_CODE_95);
                break;
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
        }
    }

    @Override
    public void resultMyPushDetails(MyPushDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                myPushDetailsBean = data.getData().getRelease();
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.ic_test_ad)).into(rivTitle);
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);

                tvSynopsis.setText(data.getData().getRelease().getTitle());
                tvName.setText(data.getData().getRelease().getUserCall());
                tvTime.setText(FormatCurrentData.getTimeRange(data.getData().getRelease().getReleaseTime()));
                tvReadNum.setText(data.getData().getRelease().getBrowseNum() + "");
                tvContent.setText(data.getData().getRelease().getDetails());
                tvContactName.setText("联系人: " + data.getData().getRelease().getContacts());
                tvContactPhone.setText("电话号码: " + data.getData().getRelease().getContactNumber());
                if ("1".equals(data.getData().getRelease().getIsFollow()))
                    cbCollection.setChecked(false);
                else
                    cbCollection.setChecked(true);
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultMyPushCollection(MyPushCollectionBean data) {
        ToastUtil.showShortToast(data.getMsg());
        if (data.getCode() == 900) {
            //清除所有临时储存
            SPUtil.clear(ApplicationUtil.getContext());
            ApplicationUtil.getManager().finishActivity(MainActivity.class);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void resultMyPushDemandType(MyPushDemandTypeBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
