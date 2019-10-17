package com.ipd.allpeopledemand.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ipd.allpeopledemand.R;

import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：自定义Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class ClassRoomPayPromptDialog extends Dialog implements View.OnClickListener {
    private TextView tvContent, tvContent2;
    private Button btCancel, btPay, btVip;
    private Activity activity;
    private double money = 0; //钱
    private int integral = 0; //积分

    public ClassRoomPayPromptDialog(Activity activity, double money, int integral) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.money = money;
        this.integral = integral;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class_room_pay_prompt);

        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent2 = (TextView) findViewById(R.id.tv_content_2);
        btCancel = (Button) findViewById(R.id.bt_cancel);
        btPay = (Button) findViewById(R.id.bt_pay);
        btVip = (Button) findViewById(R.id.bt_vip);

        tvContent.setText(Html.fromHtml("您需先支付<font color=\"#E71B64\">" + money + "元</font>才可观看教程哦~"));
        tvContent2.setText(Html.fromHtml("您还可以支付<font color=\"#E71B64\">" + money + "元</font>开通VIP全部课程包月"));

        btCancel.setOnClickListener(this);
        btPay.setOnClickListener(this);
        btVip.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        if (isFastClick()) {
            switch (v.getId()) {
                case R.id.bt_cancel:
                    this.cancel();
                    break;
                case R.id.bt_pay:
                    goPay();
                    this.cancel();
                    break;
                case R.id.bt_vip:
                    goVip();
                    this.cancel();
                    break;
            }
        }
    }

    public abstract void goPay();

    public abstract void goVip();
}
