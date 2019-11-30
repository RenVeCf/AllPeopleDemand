package com.ipd.allpeopledemand.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.activity.MsgActivity;
import com.ipd.allpeopledemand.activity.SearchClassRoomActivity;
import com.ipd.allpeopledemand.adapter.ViewPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.ClassRoomInicationBean;
import com.ipd.allpeopledemand.bean.IsMsgBean;
import com.ipd.allpeopledemand.common.view.NavitationFollowScrollLayoutText;
import com.ipd.allpeopledemand.common.view.NoScrollViewPager;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ClassRoomInicationContract;
import com.ipd.allpeopledemand.presenter.ClassRoomInicationPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.textview.badge.Badge;
import com.xuexiang.xui.widget.textview.badge.BadgeView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：全民课堂-顶部滑动导航栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class ClassRoomFragment extends BaseFragment<ClassRoomInicationContract.View, ClassRoomInicationContract.Presenter> implements ClassRoomInicationContract.View {

    @BindView(R.id.tv_class_room)
    TopView tvClassRoom;
    @BindView(R.id.ib_top_msg)
    ImageButton ibTopMsg;
    @BindView(R.id.et_top_long_search)
    TextView etTopLongSearch;
    @BindView(R.id.nfsl_fragment_class_room)
    NavitationFollowScrollLayoutText nfslFragmentClassRoom;
    @BindView(R.id.vp_fragment_class_room)
    NoScrollViewPager vpFragmentClassRoom;

    private String[] titles;
    private List<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private ClassRoomPagerFragment fm;
    private List<ClassRoomInicationBean.DataBean.ClassListBean> classListBean = new ArrayList<>();
    private List<Badge> mBadges = new ArrayList<>();
    private int positions;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class_room;
    }

    @Override
    public ClassRoomInicationContract.Presenter createPresenter() {
        return new ClassRoomInicationPresenter(mContext);
    }

    @Override
    public ClassRoomInicationContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();

        TreeMap<String, String> isMsgMap = new TreeMap<>();
        isMsgMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        isMsgMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isMsgMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getIsMsg(isMsgMap, false, false);
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ImmersionBar.setTitleBar(getActivity(), tvClassRoom);
    }

    @Override
    public void initListener() {
//        etTopLongSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    // 隐藏软键盘
//                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
//
//                    vpFragmentClassRoom.removeAllViewsInLayout();
//                    viewPagerAdapter.notifyDataSetChanged();
//                    //向集合添加Fragment
//                    fragments.clear();
//                    for (int i = 0; i < titles.length; i++) {
//                        fm = new ClassRoomPagerFragment();
//                        Bundle args = new Bundle();
//                        args.putString("roomClassId", i == 0 ? "0" : classListBean.get(i - 1).getRoomClassId() + "");
//                        args.putString("title", etTopLongSearch.getText().toString().trim());
//                        fm.setArguments(args);
//                        fragments.add(fm);
//                    }
//
//                    vpFragmentClassRoom.setAdapter(viewPagerAdapter);
//                    vpFragmentClassRoom.setOffscreenPageLimit(titles.length);
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    @Override
    public void initData() {
        getPresenter().getClassRoomInication(false, false);


        TreeMap<String, String> isMsgMap = new TreeMap<>();
        isMsgMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        isMsgMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isMsgMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getIsMsg(isMsgMap, false, false);
    }

    @Override
    public void resultClassRoomInication(ClassRoomInicationBean data) {
        classListBean.clear();
        classListBean.addAll(data.getData().getClassList());
        titles = new String[classListBean.size() + 1];
        for (int i = 0; i < classListBean.size() + 1; i++) {
            if (i == 0)
                titles[0] = "全部";
            else
                titles[i] = classListBean.get(i - 1).getClassName();
        }
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fm = new ClassRoomPagerFragment();
            Bundle args = new Bundle();
            args.putString("roomClassId", i == 0 ? "0" : classListBean.get(i - 1).getRoomClassId() + "");
            args.putString("title", "");//etTopLongSearch.getText().toString().trim());
            fm.setArguments(args);
            fragments.add(fm);
        }
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpFragmentClassRoom.setAdapter(viewPagerAdapter);
        vpFragmentClassRoom.setOffscreenPageLimit(titles.length);
//        vpFragmentClassRoom.setScanScroll(false);//禁止滑动

        //设置导航条
        nfslFragmentClassRoom.setViewPager(getContext(), titles, vpFragmentClassRoom, R.color.tx_bottom_navigation, R.color.black, 16, 16, 24, true, R.color.black, 0, 0, 0, 80);
        nfslFragmentClassRoom.setBgLine(getContext(), 1, R.color.whitesmoke);
        nfslFragmentClassRoom.setNavLine(getActivity(), 3, R.color.colorAccent);

        nfslFragmentClassRoom.setOnNaPageChangeListener(new NavitationFollowScrollLayoutText.OnNaPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                positions = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void resultIsMsg(IsMsgBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getData().getUreads() == 1) {
                    mBadges.add(new BadgeView(getContext()).bindTarget(ibTopMsg).setBadgeText("1").setBadgeTextSize(6, true)
                            .setBadgeBackgroundColor(getResources().getColor(R.color.red)).setBadgeTextColor(getResources().getColor(R.color.red))
                            .setBadgePadding(0, true));
                } else {
                    for (Badge badge : mBadges) {
                        badge.hide(true);
                    }
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @OnClick({R.id.ib_top_msg, R.id.et_top_long_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_top_msg:
                if (isFastClick()) {
                    if (!isEmpty(SPUtil.get(getActivity(), IS_LOGIN, "") + "")) {
                        for (Badge badge : mBadges) {
                            badge.hide(true);
                        }
                        startActivity(new Intent(getActivity(), MsgActivity.class));
                    } else {
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                }
                break;
            case R.id.et_top_long_search:
                startActivity(new Intent(getActivity(), SearchClassRoomActivity.class).putExtra("roomClassId", positions > 0 ? classListBean.get(positions - 1).getRoomClassId() : 0));
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
