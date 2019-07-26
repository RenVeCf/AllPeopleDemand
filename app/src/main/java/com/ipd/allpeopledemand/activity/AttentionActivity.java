package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.ViewPagerAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.PushBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.NavitationFollowScrollLayoutText;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.PushContract;
import com.ipd.allpeopledemand.fragment.AttentionPagerFragment;
import com.ipd.allpeopledemand.presenter.PushPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：我的关注-顶部滑动导航栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class AttentionActivity extends BaseActivity<PushContract.View, PushContract.Presenter> implements PushContract.View {

    @BindView(R.id.tv_attention)
    TopView tvAttention;
    @BindView(R.id.nfsl_fragment_attention)
    NavitationFollowScrollLayoutText nfslFragmentAttention;
    @BindView(R.id.vp_fragment_attention)
    ViewPager vpFragmentAttention;

    private String[] titles;
    private List<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private AttentionPagerFragment fm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_attention;
    }

    @Override
    public PushContract.Presenter createPresenter() {
        return new PushPresenter(this);
    }

    @Override
    public PushContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAttention);
    }

    @Override
    public void initData() {
        getPresenter().getClassIfication(true, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void resultPush(PushBean data) {

    }

    @Override
    public void resultClassIfication(ClassIficationBean data) {
        switch (data.getCode()) {
            case 200:
                titles = new String[data.getData().getClassList().size() + 1];
                for (int i = 0; i < data.getData().getClassList().size() + 1; i++) {
                    if (i == 0)
                        titles[0] = "全部";
                    else
                        titles[i] = data.getData().getClassList().get(i - 1).getClassName();
                }
                //向集合添加Fragment
                fragments = new ArrayList<>();
                for (int i = 0; i < titles.length; i++) {
                    fm = new AttentionPagerFragment();
                    Bundle args = new Bundle();
                    args.putString("releaseClassId", i == 0 ? "0" : data.getData().getClassList().get(i - 1).getReleaseClassId() + "");
                    fm.setArguments(args);
                    fragments.add(fm);
                }
                viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
                vpFragmentAttention.setAdapter(viewPagerAdapter);
                vpFragmentAttention.setOffscreenPageLimit(titles.length);

                //设置导航条
                nfslFragmentAttention.setViewPager(this, titles, vpFragmentAttention, R.color.tx_bottom_navigation, R.color.black, 16, 16, 24, true, R.color.black, 0, 0, 0, 80);
                nfslFragmentAttention.setBgLine(this, 1, R.color.whitesmoke);
                nfslFragmentAttention.setNavLine(this, 3, R.color.colorAccent);
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
    public void resultUploadImg(UploadImgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
