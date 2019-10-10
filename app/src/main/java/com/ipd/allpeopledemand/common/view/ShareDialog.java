package com.ipd.allpeopledemand.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.ipd.allpeopledemand.R;

import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：底部弹出Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class ShareDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private ImageButton ib_wechat, ib_moments;

    public ShareDialog(Activity activity) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);

        ib_wechat = (ImageButton) findViewById(R.id.ib_wechat);
        ib_moments = (ImageButton) findViewById(R.id.ib_moments);

        ib_wechat.setOnClickListener(this);
        ib_moments.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        if (isFastClick()) {
            switch (v.getId()) {
                case R.id.ib_wechat:
                    goWechatShare();
                    this.cancel();
                case R.id.ib_moments:
                    goMomentsShare();
                    this.cancel();
                    break;
            }
        }
    }

    public abstract void goWechatShare();

    public abstract void goMomentsShare();
}
