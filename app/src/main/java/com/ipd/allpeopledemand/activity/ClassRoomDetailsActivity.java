package com.ipd.allpeopledemand.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.SslError;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomBalancePayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.bean.ClassRoomWechatPayBean;
import com.ipd.allpeopledemand.bean.ShareBean;
import com.ipd.allpeopledemand.common.view.JzvdStdMp3;
import com.ipd.allpeopledemand.common.view.MyJzvdStd;
import com.ipd.allpeopledemand.common.view.ShareDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ClassRoomPagerContract;
import com.ipd.allpeopledemand.presenter.ClassRoomPagerPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;

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
    @BindView(R.id.wv_player)
    WebView wvPlayer;
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
    private String shareUrl = "", shareTitle = ""; //分享链接/标题

    /**
     * 视频全屏参数
     */
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private View customView;
    private FrameLayout fullscreenContainer;
    private WebChromeClient.CustomViewCallback customViewCallback;

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
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        wvContent.setWebViewClient(new MyWebViewClient(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        WebSettings webSettings = wvPlayer.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        webSettings.setLoadWithOverviewMode(true);
        // 保存表单数据
        webSettings.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setJavaScriptEnabled(true);
        // 页面加载好以后，再放开图片
        webSettings.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        webSettings.setDomStorageEnabled(true);
        // WebView是否新窗口打开(加了后可能打不开网页)
        webSettings.setSupportMultipleWindows(false);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
/** 设置字体默认缩放大小(改变网页字体大小,setTextSize api14被弃用)*/
        webSettings.setTextZoom(100);
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    // 分享微信好友
    private void showWeChatShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.disableSSOWhenAuthorize();
        oks.setTitle(getString(R.string.app_name));
        oks.setText(shareTitle);
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        oks.setUrl(url);
        oks.setComment("很棒，值得分享！！");
        oks.show(this);
    }

    // 分享微信朋友圈
    private void showWechatMomentsShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(shareTitle);
        // text是分享文本，所有平台都需要这个字段
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_logo);//显示APP本身自带图片
        oks.setImageData(bitmap);//bitmap格式图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("很棒，值得分享！！");
        // 启动分享GUI
        oks.show(this);
    }

    @OnClick(R.id.ib_share)
    public void onViewClicked() {
        new ShareDialog(this) {
            @Override
            public void goWechatShare() {
                if (!isEmpty(shareUrl))
                    showWeChatShare(shareUrl, Wechat.NAME);
            }

            @Override
            public void goMomentsShare() {
                if (!isEmpty(shareUrl))
                    showWechatMomentsShare(shareUrl, WechatMoments.NAME);
            }
        }.show();
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
                shareTitle = roomDetailsBean.getTitle();
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("图文");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_text_label);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                break;
            case "2":
                llVideoPlayer.setVisibility(View.GONE);
                ivClassRoomDetails.setVisibility(View.GONE);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                shareTitle = roomDetailsBean.getTitle();
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("音频");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_audio_label);
                switch (roomDetailsBean.getAudioType()) {
                    case "1":
                        jsAudioPlayer.setUp(BASE_LOCAL_URL + roomDetailsBean.getAudioFile(), roomDetailsBean.getTitle());
                        break;
                    case "2":
                        llAudioPlayer.setVisibility(View.GONE);
                        llVideoPlayer.setVisibility(View.GONE);
                        ivClassRoomDetails.setVisibility(View.GONE);
                        wvPlayer.loadUrl(roomDetailsBean.getAudioUrl());
//                        jsAudioPlayer.setUp(roomDetailsBean.getAudioUrl(), roomDetailsBean.getTitle());
                        break;
                }
                Glide.with(this).load(BASE_LOCAL_URL + roomDetailsBean.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_big_class_room)).into(jsAudioPlayer.thumbImageView);
                break;
            case "3":
                llAudioPlayer.setVisibility(View.GONE);
                ivClassRoomDetails.setVisibility(View.GONE);
                wvContent.loadData(getHtmlData(roomDetailsBean.getContent()), "text/html;charset=utf-8", "utf-8");
                shareTitle = roomDetailsBean.getTitle();
                tvClassRoomTypeTitle.setText(roomDetailsBean.getTitle());
                tvClassRoomType.setText("视频");
                tvClassRoomType.setBackgroundResource(R.drawable.bg_class_room_video_label);
                switch (roomDetailsBean.getVideoType()) {
                    case "1":
                        jsVideoPlayer.setUp(BASE_LOCAL_URL + roomDetailsBean.getVideoFile(), roomDetailsBean.getTitle());
                        break;
                    case "2":
                        llVideoPlayer.setVisibility(View.GONE);
                        llAudioPlayer.setVisibility(View.GONE);
                        ivClassRoomDetails.setVisibility(View.GONE);
                        wvPlayer.loadUrl(roomDetailsBean.getVideoUrl());
//                        wvPlayer.loadUrl("https://player.youku.com/embed/XMTkzODQ3NjU5Ng==?client_id=b598bfd8ec862716&password=&autoplay=false#www.vmovier.com");
//                        jsVideoPlayer.setUp(roomDetailsBean.getVideoUrl(), roomDetailsBean.getTitle());
                        break;
                }
                Glide.with(this).load(BASE_LOCAL_URL + roomDetailsBean.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_big_class_room)).into(jsVideoPlayer.thumbImageView);
                break;
        }

        tvClassRoomReadNum.setText(roomDetailsBean.getWatchNum() + "观看");
        tvClassRoomDate.setText(roomDetailsBean.getCreateTime().substring(0, 10));
        tvClassRoomPayFee.setText(money + "元");// + integral + "积分");

        TreeMap<String, String> shareMap = new TreeMap<>();
        shareMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        shareMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(shareMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getShare(shareMap, false, false);
    }

    @Override
    public void initListener() {
        //设置客户端，让点击跳转操作在当前应用内存进行操作
        wvPlayer.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                if (dialogUtils != null) {
//                    dialogUtils.dismissProgress();
//                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //当发生证书认证错误时，采用默认的处理方法handler.cancel()，停止加载问题页面
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
            }
        });

        wvPlayer.setWebChromeClient(new WebChromeClient() {
            @Override
            public View getVideoLoadingProgressView() {
                FrameLayout frameLayout = new FrameLayout(ClassRoomDetailsActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
                return frameLayout;
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                showCustomView(view, callback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView();
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                ToastUtil.showLongToast(message);
                result.confirm();
                return true;
            }

            //返回当前加载网页的进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            //获取当前网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
    }

    /**
     * 视频播放全屏
     **/
    private void showCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }
        setStatusBarVisibility(false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        wvPlayer.setVisibility(View.INVISIBLE);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(this);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        customViewCallback = callback;
    }

    /**
     * 隐藏视频全屏
     */
    private void hideCustomView() {
        if (customView == null) {
            return;
        }

        setStatusBarVisibility(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        wvPlayer.setVisibility(View.VISIBLE);
    }

    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        wvPlayer.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        /** 回退键 事件处理 优先级:视频播放全屏-网页回退-关闭页面 */
        if (customView != null) {
            hideCustomView();
        } else if (wvPlayer.canGoBack()) {
            wvPlayer.goBack();
        } else {
            super.onBackPressed();
        }
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
    public void resultClassRoomWechatPay(ClassRoomWechatPayBean data) {

    }

    @Override
    public void resultClassRoomBalancePay(ClassRoomBalancePayBean data) {

    }

    @Override
    public void resultShare(ShareBean data) {
        switch (data.getCode()) {
            case 200:
                shareUrl = data.getData().getShareUrl() + "?" + "invitationCode=" + data.getData().getUser().getInvitationCode();
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
