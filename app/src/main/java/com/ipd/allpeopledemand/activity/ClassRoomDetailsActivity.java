package com.ipd.allpeopledemand.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.common.view.JzvdStdMp3;
import com.ipd.allpeopledemand.common.view.MyJzvdStd;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ClassRoomPagerContract;
import com.ipd.allpeopledemand.presenter.ClassRoomPagerPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：课堂详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public class ClassRoomDetailsActivity extends BaseActivity<ClassRoomPagerContract.View, ClassRoomPagerContract.Presenter> implements ClassRoomPagerContract.View {

    @BindView(R.id.tv_class_room_details)
    TopView tvClassRoomDetails;
    @BindView(R.id.tv_class_room_type_title)
    TextView tvClassRoomTypeTitle;
    @BindView(R.id.tv_class_room_type)
    TextView tvClassRoomType;
    @BindView(R.id.tv_class_room_read_num)
    TextView tvClassRoomReadNum;
    @BindView(R.id.tv_class_room_date)
    TextView tvClassRoomDate;
    @BindView(R.id.tv_class_room_pay_fee)
    TextView tvClassRoomPayFee;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.iv_class_room_details)
    AppCompatImageView ivClassRoomDetails;
    @BindView(R.id.js_audio_player)
    JzvdStdMp3 jsAudioPlayer;
    @BindView(R.id.js_video_player)
    MyJzvdStd jsVideoPlayer;
    @BindView(R.id.ll_audio_player)
    LinearLayout llAudioPlayer;
    @BindView(R.id.ll_video_player)
    LinearLayout llVideoPlayer;

    private ClassRoomDetailsBean.DataBean.RoomDetailsBean roomDetailsBean = new ClassRoomDetailsBean.DataBean.RoomDetailsBean();
    private int integral;
    private double money;

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_room_details;
    }

    @Override
    public ClassRoomPagerContract.Presenter createPresenter() {
        return new ClassRoomPagerPresenter(this);
    }

    @Override
    public ClassRoomPagerContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvClassRoomDetails);

        roomDetailsBean = getIntent().getParcelableExtra("roomDetailsBean");
        integral = getIntent().getIntExtra("integral", 0);
        money = getIntent().getDoubleExtra("money", 0);

        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wvContent.setWebViewClient(new MyWebViewClient(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    static class MyWebViewClient extends WebViewClient {
        private Dialog dialog;
        private Activity activity;

        public MyWebViewClient(Activity activity) {
            dialog = new Dialog(activity);
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (!activity.isFinishing()) dialog.show();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!activity.isFinishing()) dialog.dismiss();
        }
    }

    @Override
    public void initData() {
        switch (roomDetailsBean.getType()) {
            case "1":
                llAudioPlayer.setVisibility(View.GONE);
                llVideoPlayer.setVisibility(View.GONE);
                Glide.with(this).load(BASE_LOCAL_URL + roomDetailsBean.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_big_class_room)).into(ivClassRoomDetails);
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("图文");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_text_label);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                break;
            case "2":
                llVideoPlayer.setVisibility(View.GONE);
                ivClassRoomDetails.setVisibility(View.GONE);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("音频");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_audio_label);
                switch (roomDetailsBean.getAudioType()) {
                    case "1":
                        jsAudioPlayer.setUp(BASE_LOCAL_URL + roomDetailsBean.getAudioFile(), roomDetailsBean.getTitle());
                        break;
                    case "2":
                        jsAudioPlayer.setUp(roomDetailsBean.getAudioUrl(), roomDetailsBean.getTitle());
                        break;
                }
                Glide.with(this).load(BASE_LOCAL_URL + roomDetailsBean.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_big_class_room)).into(jsAudioPlayer.thumbImageView);
                break;
            case "3":
                llAudioPlayer.setVisibility(View.GONE);
                ivClassRoomDetails.setVisibility(View.GONE);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("视频");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_video_label);
                switch (roomDetailsBean.getVideoType()) {
                    case "1":
                        jsVideoPlayer.setUp(BASE_LOCAL_URL + roomDetailsBean.getVideoFile(), roomDetailsBean.getTitle());
                        break;
                    case "2":
                        jsVideoPlayer.setUp(roomDetailsBean.getVideoUrl(), roomDetailsBean.getTitle());
                        break;
                }
                Glide.with(this).load(BASE_LOCAL_URL + roomDetailsBean.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_big_class_room)).into(jsVideoPlayer.thumbImageView);
                break;
        }

        tvClassRoomReadNum.setText(roomDetailsBean.getWatchNum() + "观看");
        tvClassRoomDate.setText(roomDetailsBean.getCreateTime().substring(0, 10));
        tvClassRoomPayFee.setText(money + "元+" + integral + "积分");
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void resultClassRoomPager(ClassRoomPagerBean data) {

    }

    @Override
    public void resultClassRoomDetails(ClassRoomDetailsBean data) {

    }

    @Override
    public void resultClassRoomAliPay(ClassRoomAliPayBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
