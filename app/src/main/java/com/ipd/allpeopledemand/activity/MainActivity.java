package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;

import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.fragment.ClassRoomFragment;
import com.ipd.allpeopledemand.fragment.FeedbackFragment;
import com.ipd.allpeopledemand.fragment.MainFragment;
import com.ipd.allpeopledemand.fragment.MyFragment;
import com.ipd.allpeopledemand.fragment.PushFragment;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.NavigationBarUtil;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.allpeopledemand.common.config.IConstants.FIRST_APP;
import static com.ipd.allpeopledemand.common.config.IConstants.HOW_PAGE;
import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;

/**
 * Description ：首页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.rb_navigation_main)
    RadioButton rbNavigationMain;
    @BindView(R.id.rb_navigation_class_room)
    RadioButton rbNavigationClassRoom;
    @BindView(R.id.rb_navigation_push)
    RadioButton rbNavigationPush;
    @BindView(R.id.rb_navigation_feedback)
    RadioButton rbNavigationFeedback;
    @BindView(R.id.rb_navigation_my)
    RadioButton rbNavigationMy;

    private long firstTime = 0;
    private Fragment currentFragment = new Fragment();
    private MainFragment mainFragment = new MainFragment();
    private ClassRoomFragment classRoomFragment = new ClassRoomFragment();
    private PushFragment pushFragment = new PushFragment();
    private FeedbackFragment feedbackFragment = new FeedbackFragment();
    private MyFragment myFragment = new MyFragment();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //适配虚拟按键
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);

        SPUtil.put(this, FIRST_APP, "is_first");

        //定义底部标签SVG图片大小
        Drawable drawableMain = getResources().getDrawable(R.drawable.select_main);
        drawableMain.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y60), (int) getResources().getDimension(R.dimen.x60));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationMain.setCompoundDrawables(null, drawableMain, null, null);//只放上面

        Drawable drawableClassRoom = getResources().getDrawable(R.drawable.select_class_room);
        drawableClassRoom.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y60), (int) getResources().getDimension(R.dimen.x60));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationClassRoom.setCompoundDrawables(null, drawableClassRoom, null, null);//只放上面

        Drawable drawablePush = getResources().getDrawable(R.drawable.select_push);
        drawablePush.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y60), (int) getResources().getDimension(R.dimen.x60));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationPush.setCompoundDrawables(null, drawablePush, null, null);//只放上面

        Drawable drawableFeedback = getResources().getDrawable(R.drawable.select_feedback);
        drawableFeedback.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y60), (int) getResources().getDimension(R.dimen.x60));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationFeedback.setCompoundDrawables(null, drawableFeedback, null, null);//只放上面

        Drawable drawableMy = getResources().getDrawable(R.drawable.select_my);
        drawableMy.setBounds(0, 0, (int) getResources().getDimension(R.dimen.y60), (int) getResources().getDimension(R.dimen.x60));//第一0是距左右边距离，第二0是距上下边距离，第三长度,第四宽度
        rbNavigationMy.setCompoundDrawables(null, drawableMy, null, null);//只放上面

        //上一层界面跳过来时，要求显示对的碎片
        switch (getIntent().getIntExtra("howFragment", 0)) {
            case 0:
                rbNavigationMain.setChecked(true);
                switchFragment(mainFragment).commit();
                break;
            case 1:
                rbNavigationClassRoom.setChecked(true);
                switchFragment(classRoomFragment).commit();
                break;
            case 2:
                rbNavigationPush.setChecked(true);
                switchFragment(pushFragment).commit();
                break;
            case 3:
                rbNavigationFeedback.setChecked(true);
                switchFragment(feedbackFragment).commit();
                break;
            case 4:
                rbNavigationMy.setChecked(true);
                switchFragment(myFragment).commit();
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    //Fragment优化
    public FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.ll_main, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    //双击退出程序
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showShortToast(getResources().getString(R.string.click_out_again));
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();

        }
    }

    @OnClick({R.id.rb_navigation_main, R.id.rb_navigation_class_room, R.id.rb_navigation_push, R.id.rb_navigation_feedback, R.id.rb_navigation_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_navigation_main:
                switchFragment(mainFragment).commit();
                break;
            case R.id.rb_navigation_class_room:
                switchFragment(classRoomFragment).commit();
                break;
            case R.id.rb_navigation_push:
                if (!(SPUtil.get(this, IS_LOGIN, "") + "").equals(""))
                    switchFragment(pushFragment).commit();
                else {
                    SPUtil.put(this, HOW_PAGE, "2");
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
            case R.id.rb_navigation_feedback:
                if (!(SPUtil.get(this, IS_LOGIN, "") + "").equals(""))
                    switchFragment(feedbackFragment).commit();
                else {
                    SPUtil.put(this, HOW_PAGE, "3");
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
            case R.id.rb_navigation_my:
                if (!(SPUtil.get(this, IS_LOGIN, "") + "").equals(""))
                    switchFragment(myFragment).commit();
                else {
                    SPUtil.put(this, HOW_PAGE, "4");
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
        }
    }
}
