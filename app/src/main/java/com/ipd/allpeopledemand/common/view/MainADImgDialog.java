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

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ipd.allpeopledemand.R;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：自定义Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class MainADImgDialog extends Dialog implements View.OnClickListener {

    private AppCompatImageView acivMainAd;
    private Activity activity;
    private String imgUrl;

    public MainADImgDialog(Activity activity, String imgUrl) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.imgUrl = imgUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main_ad_img);

        acivMainAd = (AppCompatImageView) findViewById(R.id.aciv_main_ad);
        Glide.with(activity).load(BASE_LOCAL_URL + imgUrl).apply(new RequestOptions()).into(acivMainAd);

        acivMainAd.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
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
                case R.id.aciv_main_ad:
                    goSee();
                    this.cancel();
                    break;
            }
        }
    }

    public abstract void goSee();
}
