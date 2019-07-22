package com.ipd.allpeopledemand.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionDetailsBean;
import com.ipd.allpeopledemand.bean.MainDetailsBean;
import com.ipd.allpeopledemand.bean.ReportBean;
import com.ipd.allpeopledemand.bean.ReportListBean;
import com.ipd.allpeopledemand.common.view.BottomPayDialog;
import com.ipd.allpeopledemand.common.view.NotIntegralDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.AttentionContract;
import com.ipd.allpeopledemand.presenter.AttentionPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：资讯详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/22.
 */
public class InformationDetailsActivity extends BaseActivity<AttentionContract.View, AttentionContract.Presenter> implements AttentionContract.View {

    @BindView(R.id.tv_report)
    TopView tvReport;
    @BindView(R.id.bt_top_report)
    Button btTopReport;
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
    @BindView(R.id.tv_pay_fee)
    TextView tvPayFee;
    @BindView(R.id.bt_pay)
    Button btPay;
    @BindView(R.id.ll_not_pay)
    LinearLayout llNotPay;
    @BindView(R.id.tv_contact_name)
    TextView tvContactName;
    @BindView(R.id.tv_contact_phone)
    TextView tvContactPhone;
    @BindView(R.id.bt_contact_msg)
    Button btContactMsg;
    @BindView(R.id.bt_contact_phone)
    Button btContactPhone;
    @BindView(R.id.ll_pay)
    LinearLayout llPay;

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private int releaseId;
    private List<String> reportDataList = new ArrayList<>();//选择举报
    private List<ReportListBean.DataBean.ReportListBeans> reportListBeans = new ArrayList<>();//选择举报(取举报ID用)
    private int activityType; //1: 首页， 2:首页列表的广告， 3: 我的关注， 4: 我的购买
    private double money; //金额
    private double balance; //余额
    private String IsPurchase; //1积分不足，2未购买  3.购买了

    @Override
    public int getLayoutId() {
        return R.layout.activity_information_details;
    }

    @Override
    public AttentionContract.Presenter createPresenter() {
        return new AttentionPresenter(this);
    }

    @Override
    public AttentionContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvReport);

        activityType = getIntent().getIntExtra("activityType", 0);
        releaseId = getIntent().getIntExtra("releaseId", 0);
    }

    @Override
    public void initData() {
        switch (activityType) {
            case 1:
                TreeMap<String, String> mainDetailsMap = new TreeMap<>();
                mainDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                mainDetailsMap.put("releaseId", releaseId + "");
                mainDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(mainDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMainDetails(mainDetailsMap, true, false);
                break;
            case 2:
                TreeMap<String, String> mainDetailsADMap = new TreeMap<>();
                mainDetailsADMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                mainDetailsADMap.put("releaseId", releaseId + "");
                mainDetailsADMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(mainDetailsADMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMainDetails(mainDetailsADMap, true, false);
                break;
            case 3:
                TreeMap<String, String> attentionDetailsMap = new TreeMap<>();
                attentionDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                attentionDetailsMap.put("releaseId", releaseId + "");
                attentionDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getAttentionDetails(attentionDetailsMap, true, false);
                break;
            case 4:
                break;
        }
        TreeMap<String, String> reportListMap = new TreeMap<>();
        reportListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        reportListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(reportListMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getReportList(reportListMap, false, false);
    }

    @Override
    public void initListener() {

    }

    //举报条件选择器
    private void showPickerView() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        listData = getReportData();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                TreeMap<String, String> reportMap = new TreeMap<>();
                reportMap.put("userId", SPUtil.get(InformationDetailsActivity.this, USER_ID, "") + "");
                reportMap.put("releaseId", releaseId + "");
                reportMap.put("reportId", reportListBeans.get(options1).getReportId() + "");
                reportMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(reportMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getReport(reportMap, true, false);
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getReportData() {
        return reportDataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_90:
                    llNotPay.setVisibility(View.GONE);
                    llPay.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.cb_collection, R.id.bt_pay, R.id.bt_contact_msg, R.id.bt_contact_phone, R.id.bt_top_report})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.cb_collection:
                //收藏
                TreeMap<String, String> attentionCollectionMap = new TreeMap<>();
                attentionCollectionMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                attentionCollectionMap.put("releaseId", releaseId + "");
                attentionCollectionMap.put("isFollow", cbCollection.isChecked() ? "2" : "1");
                attentionCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionCollectionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getAttentionCollection(attentionCollectionMap, false, false);
                break;
            case R.id.bt_pay:
                switch (IsPurchase) {
                    case "1":
                        new NotIntegralDialog(this) {
                            @Override
                            public void goPayIntrgral() {
                                //积分不足跳积分规则
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 1));
                            }
                        }.show();
                        break;
                    case "2":
                        new BottomPayDialog(this, balance) {
                            @Override
                            public void goPay(int payType) {
                                if (balance >= money) {
                                    //余额直接支付
                                    payType(3);
                                } else {
                                    switch (payType) {
                                        case 1://支付宝
                                            payType(1);
                                            break;
                                        case 2://微信
                                            payType(2);
                                            break;
                                        default:
                                            ToastUtil.showShortToast("余额不足，请选择支付方式！");
                                            break;
                                    }
                                }
                            }
                        }.show();
                        break;
                    case "3":
                        llNotPay.setVisibility(View.GONE);
                        llPay.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case R.id.bt_contact_msg:
                break;
            case R.id.bt_contact_phone:
                break;
            case R.id.bt_top_report:
                showPickerView();
                break;
        }
    }

    //支付
    private void payType(int payType) {
        switch (payType) {
            case 1:
//                TreeMap<String, String> aliPayMap = new TreeMap<>();
//                aliPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
//                aliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(aliPayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
//                getPresenter().getOrderAliPay(aliPayMap, true, false);
                break;
            case 2:
//                TreeMap<String, String> weixinPayMap = new TreeMap<>();
//                weixinPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
//                weixinPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(weixinPayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
//                getPresenter().getOrderWeiXinPay(weixinPayMap, true, false);
                break;
            case 3:
                startActivityForResult(new Intent(InformationDetailsActivity.this, PayStatusActivity.class).putExtra("pay_status", 1), REQUEST_CODE_90);
                break;
        }
    }

    @Override
    public void resultMainDetails(MainDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                money = data.getData().getPrice().getMoney();
                balance = data.getData().getRelease().getBalance();
                IsPurchase = data.getData().getIsPurchase();
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.ic_test_ad)).into(rivTitle);
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);

                tvSynopsis.setText(data.getData().getRelease().getTitle());
                tvName.setText(data.getData().getRelease().getUserCall());
                tvTime.setText(FormatCurrentData.getTimeRange(data.getData().getRelease().getReleaseTime()));
                tvReadNum.setText(data.getData().getRelease().getBrowseNum() + "");
                cbCollection.setChecked("1".equals(data.getData().getRelease().getIsFollow()) ? false : true);
                tvContent.setText(data.getData().getRelease().getDetails());
                tvPayFee.setText(data.getData().getPrice().getMoney() + "元 + " + data.getData().getPrice().getIntegral() + "积分");

                tvContactName.setText("联系人: " + data.getData().getRelease().getContacts());
                tvContactPhone.setText("电话号码: " + data.getData().getRelease().getContactNumber());
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
    public void resultAttentionDetails(AttentionDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                money = data.getData().getPrice().getMoney();
                balance = data.getData().getRelease().getBalance();
                IsPurchase = data.getData().getIsPurchase();
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.ic_test_ad)).into(rivTitle);
                Glide.with(this).load(BASE_LOCAL_URL + data.getData().getRelease().getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);

                tvSynopsis.setText(data.getData().getRelease().getTitle());
                tvName.setText(data.getData().getRelease().getUserCall());
                tvTime.setText(FormatCurrentData.getTimeRange(data.getData().getRelease().getReleaseTime()));
                tvReadNum.setText(data.getData().getRelease().getBrowseNum() + "");
                cbCollection.setChecked("1".equals(data.getData().getRelease().getIsFollow()) ? false : true);
                tvContent.setText(data.getData().getRelease().getDetails());
                tvPayFee.setText(data.getData().getPrice().getMoney() + "元 + " + data.getData().getPrice().getIntegral() + "积分");

                tvContactName.setText("联系人: " + data.getData().getRelease().getContacts());
                tvContactPhone.setText("电话号码: " + data.getData().getRelease().getContactNumber());
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
    public void resultAttentionCollection(AttentionCollectionBean data) {
        ToastUtil.showShortToast(data.getMsg());
        if (data.getCode() == 900) {
            ToastUtil.showLongToast(data.getMsg());
            //清除所有临时储存
            SPUtil.clear(ApplicationUtil.getContext());
            ApplicationUtil.getManager().finishActivity(MainActivity.class);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void resultReportList(ReportListBean data) {
        switch (data.getCode()) {
            case 200:
                reportListBeans.clear();
                reportListBeans.addAll(data.getData().getReportList());
                for (ReportListBean.DataBean.ReportListBeans reportListBean : reportListBeans)
                    reportDataList.add(reportListBean.getTitle());
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
    public void resultReport(ReportBean data) {
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
