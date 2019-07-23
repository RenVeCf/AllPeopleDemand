package com.ipd.allpeopledemand.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.ViewPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.common.view.NavitationFollowScrollLayoutText;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MainPagerContract;
import com.ipd.allpeopledemand.presenter.MainPagerPresenter;
import com.ipd.allpeopledemand.utils.LocationService;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.citypicker.CityPicker;
import com.xuexiang.citypicker.adapter.OnLocationListener;
import com.xuexiang.citypicker.adapter.OnPickListener;
import com.xuexiang.citypicker.model.City;
import com.xuexiang.citypicker.model.HotCity;
import com.xuexiang.citypicker.model.LocateState;
import com.xuexiang.citypicker.model.LocatedCity;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.ipd.allpeopledemand.common.config.IConstants.CITY;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.DateUtils.getTodayDateTime;

/**
 * Description ：首页-顶部滑动导航栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MainFragment extends BaseFragment<MainPagerContract.View, MainPagerContract.Presenter> implements MainPagerContract.View {

    @BindView(R.id.tv_main)
    TopView tvMain;
    @BindView(R.id.et_top_search)
    EditText etTopSearch;
    @BindView(R.id.ll_top_location)
    LinearLayout llTopLocation;
    @BindView(R.id.tv_top_city)
    TextView tvTopCity;
    @BindView(R.id.vp_fragment_main)
    ViewPager vpFragmentMain;
    @BindView(R.id.nfsl_fragment_main)
    NavitationFollowScrollLayoutText nfslFragmentMain;

    private String[] titles;
    private List<Fragment> fragments;
    private MainPagerFragment fm;
    private ViewPagerAdapter viewPagerAdapter;
    private List<HotCity> mHotCities; //热门城市
    private OnBDLocationListener mListener = new OnBDLocationListener();
    private List<ClassIficationBean.DataBean.ClassListBean> classListBean = new ArrayList<>();
    private int selectMainPosition = 0;//搜索时传的releaseClassId的position

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public MainPagerContract.Presenter createPresenter() {
        return new MainPagerPresenter(mContext);
    }

    @Override
    public MainPagerContract.View createView() {
        return this;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.ipd.labelPosition");
        BroadcastReceiver mItemViewListClickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                for (int i = 0; i < titles.length; i++) {
                    if (titles[i].equals(intent.getStringExtra("label_name"))) {
                        // 跳转到某个Fragment
                        nfslFragmentMain.StartLabel(i);
                    }
                }
            }
        };
        broadcastManager.registerReceiver(mItemViewListClickReceiver, intentFilter);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            ImmersionBar.with(this).statusBarDarkFont(true).init();
        }
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ImmersionBar.setTitleBar(getActivity(), tvMain);

        if (!"".equals(SPUtil.get(getContext(), CITY, "") + ""))
            tvTopCity.setText(SPUtil.get(getContext(), CITY, "") + "");
        rxPermissionTest(1);

        int mTheme = R.style.DefaultCityPickerTheme;
        getActivity().setTheme(mTheme);

        mHotCities = new ArrayList<>();
        mHotCities.add(new HotCity("北京", "北京", "101010100"));
        mHotCities.add(new HotCity("上海", "上海", "101020100"));
        mHotCities.add(new HotCity("广州", "广东", "101280101"));
        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
    }

    @Override
    public void initListener() {
        etTopSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);

                    Intent intent = new Intent("android.ipd.main_search");
                    intent.putExtra("releaseClassId", selectMainPosition == 0 ? "0" : classListBean.get(selectMainPosition - 1).getReleaseClassId() + "");
                    intent.putExtra("region", tvTopCity.getText());
                    intent.putExtra("title", etTopSearch.getText().toString().trim());
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    etTopSearch.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().getMainPager(false, false);
    }

    // 定位权限
    private void rxPermissionTest(final int locationType) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    switch (locationType) {
                        case 1:
                            //开始定位
                            BDAbstractLocationListener myListener = new BDAbstractLocationListener() {
                                @Override
                                public void onReceiveLocation(BDLocation bdLocation) {
                                    tvTopCity.setText(bdLocation.getCity().replaceAll("市", ""));
                                    SPUtil.put(getContext(), CITY, bdLocation.getCity().replaceAll("市", ""));
                                    LocationService.get().unregisterListener(this);
                                }
                            };
                            LocationService.start(myListener);
                            break;
                        case 2:
                            // 定位
                            pickCity();
                            break;
                    }
                } else {
                    // 权限被拒绝
                }
            }
        });
    }

    // 选择城市
    private void pickCity() {
        CityPicker.from(this)
                .enableAnimation(false)
                .setAnimationStyle(R.style.CityPickerAnimation)
                .setLocatedCity(null)
                .setHotCities(mHotCities)
                .setOnPickListener(new OnPickListener() {

                    @Override
                    public void onPick(int position, City data) {
                        tvTopCity.setText(data.getName());
                        SPUtil.put(getContext(), CITY, data.getName());
                        LocationService.stop(mListener);

                        Intent intent = new Intent("android.ipd.location");
                        intent.putExtra("releaseClassId", selectMainPosition == 0 ? "0" : classListBean.get(selectMainPosition - 1).getReleaseClassId() + "");
                        intent.putExtra("region", tvTopCity.getText());
                        intent.putExtra("title", "");
                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    }

                    @Override
                    public void onCancel() {
                        LocationService.stop(mListener);
                    }

                    @Override
                    public void onLocate(final OnLocationListener locationListener) {
                        //开始定位
                        mListener.setOnLocationListener(locationListener);
                        LocationService.start(mListener);
                    }
                })
                .show();
    }

    // 百度定位
    private static class OnBDLocationListener extends BDAbstractLocationListener {

        private OnLocationListener mOnLocationListener;

        private OnBDLocationListener setOnLocationListener(OnLocationListener onLocationListener) {
            mOnLocationListener = onLocationListener;
            return this;
        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (mOnLocationListener != null) {
                mOnLocationListener.onLocationChanged(new LocatedCity(bdLocation.getCity(), bdLocation.getProvince(), bdLocation.getCityCode()), LocateState.SUCCESS);
                LocationService.get().unregisterListener(this);
            }
        }
    }

    @OnClick(R.id.ll_top_location)
    public void onViewClicked() {
        rxPermissionTest(2);
    }

    @Override
    public void resultMainPager(ClassIficationBean data) {
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
            fm = new MainPagerFragment();
            Bundle args = new Bundle();
            args.putString("releaseClassId", i == 0 ? "0" : classListBean.get(i - 1).getReleaseClassId() + "");
            fm.setArguments(args);
            fragments.add(fm);
        }
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpFragmentMain.setAdapter(viewPagerAdapter);
        vpFragmentMain.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslFragmentMain.setViewPager(getContext(), titles, vpFragmentMain, R.color.tx_bottom_navigation, R.color.black, 14, 14, 24, true, R.color.black, 0, 0, 0, 80);
        nfslFragmentMain.setBgLine(getContext(), 1, R.color.whitesmoke);
        nfslFragmentMain.setNavLine(getActivity(), 3, R.color.colorAccent);

        nfslFragmentMain.setOnNaPageChangeListener(new NavitationFollowScrollLayoutText.OnNaPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                selectMainPosition = position;
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
    public void resultMainList(MainListBean data) {

    }

    @Override
    public void resultAttentionCollection(AttentionCollectionBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
