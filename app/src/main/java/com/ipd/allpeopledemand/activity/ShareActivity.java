package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ShareBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ShareContract;
import com.ipd.allpeopledemand.presenter.SharePresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.xuexiang.xqrcode.XQRCode;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;

/**
 * Description ：分享好友
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class ShareActivity extends BaseActivity<ShareContract.View, ShareContract.Presenter> implements ShareContract.View {

    @BindView(R.id.tv_share)
    TopView tvShare;
    @BindView(R.id.iv_top_back)
    ImageView ivTopBack;
    @BindView(R.id.tv_average_share_num)
    TextView tvAverageShareNum;
    @BindView(R.id.stv_title)
    SuperTextView stvTitle;
    @BindView(R.id.iv_qr)
    AppCompatImageView ivQr;
    @BindView(R.id.ib_wechat)
    ImageButton ibWechat;
    @BindView(R.id.ib_moments)
    ImageButton ibMoments;
    @BindView(R.id.rv_invite_my_friends)
    RippleView rvInviteMyFriends;

    private String shareUrl = "";//分享链接

    @Override
    public int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    public ShareContract.Presenter createPresenter() {
        return new SharePresenter(this);
    }

    @Override
    public ShareContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        ImmersionBar.setTitleBar(this, tvShare);

        ivTopBack.setImageResource(R.mipmap.ic_back_white);
    }

    @Override
    public void initData() {
        TreeMap<String, String> shareMap = new TreeMap<>();
        shareMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        shareMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(shareMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getShare(shareMap, true, false);
    }

    @Override
    public void initListener() {

    }

    //生成二维码
    private void createQRCodeWithLogo(Bitmap logo, String qr_url) {
        showQRCode(XQRCode.createQRCodeWithLogo(qr_url, (int) getResources().getDimension(R.dimen.x500), (int) getResources().getDimension(R.dimen.y500), logo));
    }

    @MainThread
    private void showQRCode(Bitmap QRCode) {
        ivQr.setImageBitmap(QRCode);
    }

    // 分享微信好友
    private void showWeChatShare(String url, String platform) {
        OnekeyShare oks = new OnekeyShare();
        if (platform != null) {
            oks.setPlatform(platform);
        }
        oks.disableSSOWhenAuthorize();
        oks.setTitle(getString(R.string.app_name));
        oks.setText("全民需求，解决您一时半会儿解决不了的问题");
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
        oks.setTitle("全民需求，解决您一时半会儿解决不了的问题");
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

    @OnClick({R.id.ib_wechat, R.id.ib_moments, R.id.rv_invite_my_friends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_wechat:
                if (!isEmpty(shareUrl))
                    showWeChatShare(shareUrl, Wechat.NAME);
                break;
            case R.id.ib_moments:
                if (!isEmpty(shareUrl))
                    showWechatMomentsShare(shareUrl, WechatMoments.NAME);
                break;
            case R.id.rv_invite_my_friends:
                startActivity(new Intent(this, InviteMyFriendsActivity.class));
                break;
        }
    }

    @Override
    public void resultShare(ShareBean data) {
        switch (data.getCode()) {
            case 200:
                shareUrl = data.getData().getShareUrl() + "?" + "invitationCode=" + data.getData().getUser().getInvitationCode();
                tvAverageShareNum.setText("推荐邀请好友平均值: " + data.getData().getInvAverage());
                Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + data.getData().getUser().getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(stvTitle.getLeftIconIV());
                stvTitle.setLeftString(data.getData().getUser().getUserCall());
                createQRCodeWithLogo(null, data.getData().getShareUrl());
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
