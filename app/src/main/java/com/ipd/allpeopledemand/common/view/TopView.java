package com.ipd.allpeopledemand.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.WebViewActivity;
import com.ipd.allpeopledemand.utils.ApplicationUtil;

import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;


/**
 * Description : 公用标题栏
 * Author : rmy
 * Email : 942685687@qq.com
 * Time : 2017/11/loading1
 */

public class TopView extends RelativeLayout implements View.OnClickListener {

    private TextView tvTopTitle;
    private LinearLayout llTopBack;
    private ImageButton ivTopMsg;
    private Button btTopCancel;
    private ImageButton ivTopFAQ;
    private LinearLayout llTopLocation;
    private TextView tvTopCity;
    private LinearLayout llTopSearch;
    private LinearLayout llTopLongSearch;
    private LinearLayout llTopLongSearchEd;
    private RadioGroup rbTopAccount;
    private Button btTopReport;

    //各icon是否显示
    private Boolean isBack;
    private Boolean isMsg;
    private Boolean isCancel;
    private Boolean isTopFAQ;
    private Boolean isLocation;
    private Boolean isTopSearch;
    private Boolean isTopLongSearch;
    private Boolean isTopLongSearchEd;
    private Boolean isTopAccount;
    private Boolean isTopReport;
    private Context mContext;

    public TopView(Context context) {
        super(context);
        initValues(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValues(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopView);
        tvTopTitle.setText(typedArray.getString(R.styleable.TopView_title));
        tvTopTitle.setTextColor(typedArray.getColor(R.styleable.TopView_title_color, getResources().getColor(R.color.black)));
        isBack = typedArray.getBoolean(R.styleable.TopView_is_back, false);
        isMsg = typedArray.getBoolean(R.styleable.TopView_is_msg, false);
        isCancel = typedArray.getBoolean(R.styleable.TopView_is_cancel, false);
        isTopFAQ = typedArray.getBoolean(R.styleable.TopView_is_faq, false);
        isLocation = typedArray.getBoolean(R.styleable.TopView_is_location, false);
        isTopSearch = typedArray.getBoolean(R.styleable.TopView_is_search, false);
        isTopLongSearch = typedArray.getBoolean(R.styleable.TopView_is_long_search, false);
        isTopLongSearchEd = typedArray.getBoolean(R.styleable.TopView_is_long_search_ed, false);
        isTopAccount = typedArray.getBoolean(R.styleable.TopView_is_account, false);
        isTopReport = typedArray.getBoolean(R.styleable.TopView_is_report, false);
        typedArray.recycle();

        llTopBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        ivTopMsg.setVisibility(isMsg ? View.VISIBLE : View.GONE);
        btTopCancel.setVisibility(isCancel ? View.VISIBLE : View.GONE);
        ivTopFAQ.setVisibility(isTopFAQ ? View.VISIBLE : View.GONE);
        llTopLocation.setVisibility(isLocation ? View.VISIBLE : View.GONE);
        llTopSearch.setVisibility(isTopSearch ? View.VISIBLE : View.GONE);
        llTopLongSearch.setVisibility(isTopLongSearch ? View.VISIBLE : View.GONE);
        llTopLongSearchEd.setVisibility(isTopLongSearchEd ? View.VISIBLE : View.GONE);
        rbTopAccount.setVisibility(isTopAccount ? View.VISIBLE : View.GONE);
        btTopReport.setVisibility(isTopReport ? View.VISIBLE : View.GONE);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValues(context);
    }

    private void initValues(final Context context) {
        mContext = context;
        View.inflate(context, R.layout.top_view, this);
        tvTopTitle = (TextView) this.findViewById(R.id.tv_top_title);
        llTopBack = (LinearLayout) this.findViewById(R.id.ll_top_back);
        ivTopMsg = (ImageButton) this.findViewById(R.id.ib_top_msg);
        btTopCancel = (Button) this.findViewById(R.id.bt_top_cancel);
        ivTopFAQ = (ImageButton) this.findViewById(R.id.ib_top_faq);
        llTopLocation = (LinearLayout) this.findViewById(R.id.ll_top_location);
        tvTopCity = (TextView) this.findViewById(R.id.tv_top_city);
        llTopSearch = (LinearLayout) this.findViewById(R.id.ll_top_search);
        llTopLongSearch = (LinearLayout) this.findViewById(R.id.ll_top_long_search);
        llTopLongSearchEd = (LinearLayout) this.findViewById(R.id.ll_top_long_search_ed);
        rbTopAccount = (RadioGroup) this.findViewById(R.id.rg_top_account);
        btTopReport = (Button) this.findViewById(R.id.bt_top_report);

        llTopBack.setOnClickListener(this);
        ivTopMsg.setOnClickListener(this);
        btTopCancel.setOnClickListener(this);
        ivTopFAQ.setOnClickListener(this);
        btTopReport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                if (mContext instanceof Activity && isFastClick()) {
                    ((Activity) mContext).finish();
                    if (((Activity) mContext).getCurrentFocus() != null) {
                        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.ib_top_faq:
                if (isFastClick()) {
                    ApplicationUtil.getContext().startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 1).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                }
                break;
            case R.id.bt_top_cancel:
                if (mContext instanceof Activity && isFastClick()) {
                    ((Activity) mContext).finish();
                    if (((Activity) mContext).getCurrentFocus() != null) {
                        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            default:
                break;
        }
    }
}
