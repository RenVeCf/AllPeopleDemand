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
public abstract class RegisterDialog extends Dialog implements View.OnClickListener {
    private TextView tvContentTop, tvContentBottom;
    private Button bvRegisterSuccess;
    private Activity activity;
    private int points = 0; //赠送的积分
    private String allPeopleCode = ""; //全民号

    public RegisterDialog(Activity activity, int points, String allPeopleCode) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.points = points;
        this.allPeopleCode = allPeopleCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_register);

        tvContentTop = (TextView) findViewById(R.id.tv_content_top);
        tvContentBottom = (TextView) findViewById(R.id.tv_content_bottom);
        bvRegisterSuccess = (Button) findViewById(R.id.bv_register_success);

        tvContentTop.setText(Html.fromHtml("恭喜您注册成功，送您<font color=\"#E71B64\">" + points + "积分</font>!"));
        tvContentBottom.setText(Html.fromHtml("您的全民号是: <font color=\"#E71B64\">" + allPeopleCode));

        bvRegisterSuccess.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(false);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕中部
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
                case R.id.bv_register_success:
                    goPay();
                    this.cancel();
                    break;
            }
        }
    }

    public abstract void goPay();
}
