package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codbking.calendar.CaledarAdapter;
import com.codbking.calendar.CalendarDate;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.CheckInBean;
import com.ipd.allpeopledemand.bean.CheckInLayoutBean;
import com.ipd.allpeopledemand.common.view.CalendarDateView;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.CheckInContract;
import com.ipd.allpeopledemand.presenter.CheckInPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xui.utils.DensityUtils;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：签到
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/27.
 */
public class CheckInActivity extends BaseActivity<CheckInContract.View, CheckInContract.Presenter> implements CheckInContract.View {

    @BindView(R.id.tv_check_in)
    TopView tvCheckIn;
    @BindView(R.id.tv_title)
    SuperTextView tvTitle;
    @BindView(R.id.calendarDateView)
    com.ipd.allpeopledemand.common.view.CalendarDateView calendarDateView;
    @BindView(R.id.rv_check_in)
    RippleView rvCheckIn;
    @BindView(R.id.tv_check_in_now)
    TextView tvCheckInNow;

    private boolean isToday = false; // 今日之前 : false, 今日之后 : true
    private int today = 0; //今日多少号
    private List<CheckInLayoutBean.DataBean.SignListBean> signListBean = new ArrayList<>();
    private String isSign = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_in;
    }

    @Override
    public CheckInContract.Presenter createPresenter() {
        return new CheckInPresenter(this);
    }

    @Override
    public CheckInContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvCheckIn);

        signListBean = getIntent().getParcelableArrayListExtra("signListBean");
        isSign = getIntent().getStringExtra("isSign");

        CalendarDate date = CalendarDate.get(new Date());
        tvTitle.setCenterString(String.format("%d年%d月", date.year, date.month));
    }

    @Override
    public void initData() {
        tvTitle.setLeftString(Html.fromHtml("您已累计签到<font color=\"#E71B64\">" + getIntent().getIntExtra("continueDays", 0) + "</font>天"));
        if ("1".equals(isSign)) {
            rvCheckIn.setBackgroundResource(R.drawable.bg_search);
            tvCheckInNow.setText("已签到");
            tvCheckInNow.setTextColor(getResources().getColor(R.color.tx_bottom_navigation));
            rvCheckIn.setEnabled(false);
        }
    }

    @Override
    public void initListener() {
        calendarDateView.setAdapter(new CaledarAdapter() {
            @Override
            public View getView(View convertView, ViewGroup parentView, CalendarDate calendarDate) {
                TextView textView;
                ImageView img;
                if (convertView == null) {
                    convertView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.adapter_calendar_item, null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DensityUtils.dp2px(48), DensityUtils.dp2px(48));
                    convertView.setLayoutParams(params);
                }

                if (calendarDate.monthFlag == -1) { //上个月
//                    textView.setTextColor(getResources().getColor(R.color.tx_bottom_navigation));
                } else if (calendarDate.monthFlag == 1) { //下个月
//                    textView.setTextColor(getResources().getColor(R.color.tx_bottom_navigation));
//                    img.setImageResource(0);
                } else { //本月
                    textView = convertView.findViewById(R.id.tv_text);
                    img = convertView.findViewById(R.id.iv_status);
                    textView.setBackgroundResource(R.color.transparent);

                    img.setImageResource(R.mipmap.ic_not_check_in);
                    for (CheckInLayoutBean.DataBean.SignListBean signListBean : signListBean) {
                        if (calendarDate.day == Integer.valueOf(signListBean.getSignDate().substring(8, 10)))
                            img.setImageResource(R.mipmap.ic_is_check_in);
                    }
                    textView.setText(String.valueOf(calendarDate.day));

                    if (calendarDate.isToday()) {
                        isToday = true;
                        today = calendarDate.day;
                        if ("1".equals(isSign))
                            img.setImageResource(R.mipmap.ic_is_check_in);
                        else
                            img.setImageResource(0);
                        textView.setTextColor(Color.BLACK);
                        textView.setBackground(getResources().getDrawable(R.drawable.bg_calendar));
                    } else {
                        if (isToday)
                            if (calendarDate.day > today)
                                img.setImageResource(0);
                        textView.setTextColor(Color.BLACK);
                    }
                }
                return convertView;
            }
        });

        calendarDateView.setOnMonthChangedListener(new CalendarDateView.OnMonthChangedListener() {
            @Override
            public void onMonthChanged(View view, int position, CalendarDate date) {
                tvTitle.setCenterString(String.format("%d年%d月", date.year, date.month));
            }
        });
    }


    @Override
    public void resultCheckInLayout(CheckInLayoutBean data) {

    }

    @Override
    public void resultCheckIn(CheckInBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                rvCheckIn.setEnabled(false);
                rvCheckIn.setBackgroundResource(R.drawable.bg_search);
                tvCheckInNow.setText("已签到");
                tvCheckInNow.setTextColor(getResources().getColor(R.color.tx_bottom_navigation));
                tvTitle.setLeftString(Html.fromHtml("您已累计签到<font color=\"#E71B64\">" + data.getData().getContinueDays() + "</font>天"));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                        finish();
                    }
                }, 1000);
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

    @OnClick(R.id.rv_check_in)
    public void onViewClicked() {
        TreeMap<String, String> checkInMap = new TreeMap<>();
        checkInMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        checkInMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(checkInMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getCheckIn(checkInMap, true, false);
    }
}