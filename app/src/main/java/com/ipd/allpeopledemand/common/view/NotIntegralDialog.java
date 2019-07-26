package com.ipd.allpeopledemand.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
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
public abstract class NotIntegralDialog extends Dialog implements View.OnClickListener {
    private TextView tvContent;
    private Button btNotIntrgral;
    private Activity activity;

    public NotIntegralDialog(Activity activity) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_class_room_not_integral);

        tvContent = (TextView) findViewById(R.id.tv_content);
        btNotIntrgral = (Button) findViewById(R.id.bt_not_intrgral);

        tvContent.setText("您当前积分不够支付，签到、浏览、邀请好友可获得更多积分哦~");

        btNotIntrgral.setOnClickListener(this);

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
                case R.id.bt_not_intrgral:
                    goPayIntrgral();
                    this.cancel();
                    break;
            }
        }
    }

    public abstract void goPayIntrgral();
}
