package com.ipd.allpeopledemand.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MsgActivity;
import com.ipd.allpeopledemand.activity.WebViewActivity;
import com.ipd.allpeopledemand.adapter.ViewPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.CityAddressBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.IsMsgBean;
import com.ipd.allpeopledemand.bean.MainADImgBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.common.view.MainADImgDialog;
import com.ipd.allpeopledemand.common.view.NavitationFollowScrollLayoutText;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MainPagerContract;
import com.ipd.allpeopledemand.presenter.MainPagerPresenter;
import com.ipd.allpeopledemand.utils.LocationService;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.textview.badge.Badge;
import com.xuexiang.xui.widget.textview.badge.BadgeView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：首页-顶部滑动导航栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MainFragment extends BaseFragment<MainPagerContract.View, MainPagerContract.Presenter> implements MainPagerContract.View {

    @BindView(R.id.tv_main)
    TopView tvMain;
    @BindView(R.id.ib_top_msg)
    ImageButton ibTopMsg;
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
    private OptionsPickerView pvOptions;//城市选择
    //    private List<HotCity> mHotCities; //热门城市
//    private OnBDLocationListener mListener = new OnBDLocationListener();
    private List<ClassIficationBean.DataBean.ClassListBean> classListBean = new ArrayList<>();
    private ArrayList<CityAddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private BDAbstractLocationListener myListener;
    private List<Badge> mBadges = new ArrayList<>();

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

            if (!isEmpty(SPUtil.get(getContext(), CITY, "") + ""))
                tvTopCity.setText(SPUtil.get(getContext(), CITY, "") + "");

            TreeMap<String, String> isMsgMap = new TreeMap<>();
            isMsgMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
            isMsgMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isMsgMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
            getPresenter().getIsMsg(isMsgMap, false, false);
        }
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ImmersionBar.setTitleBar(getActivity(), tvMain);

        if (!isEmpty(SPUtil.get(getContext(), CITY, "") + ""))
            tvTopCity.setText(SPUtil.get(getContext(), CITY, "") + "");
        rxPermissionTest(1);

        int mTheme = R.style.DefaultCityPickerTheme;
        getActivity().setTheme(mTheme);

//        mHotCities = new ArrayList<>();
//        mHotCities.add(new HotCity("北京", "北京", "101010100"));
//        mHotCities.add(new HotCity("上海", "上海", "101020100"));
//        mHotCities.add(new HotCity("广州", "广东", "101280101"));
//        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
//        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
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

                    vpFragmentMain.removeAllViewsInLayout();
                    viewPagerAdapter.notifyDataSetChanged();
                    //向集合添加Fragment
                    fragments.clear();
                    for (int i = 0; i < titles.length; i++) {
                        fm = new MainPagerFragment();
                        Bundle args = new Bundle();
                        args.putString("releaseClassId", i == 0 ? "0" : classListBean.get(i - 1).getReleaseClassId() + "");
                        args.putString("region", "全国".equals(tvTopCity.getText().toString().trim()) ? "" : tvTopCity.getText().toString().trim());
                        args.putString("title", etTopSearch.getText().toString().trim());
                        fm.setArguments(args);
                        fragments.add(fm);
                    }

                    vpFragmentMain.setAdapter(viewPagerAdapter);
                    vpFragmentMain.setOffscreenPageLimit(titles.length);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TreeMap<String, String> mainADImgMap = new TreeMap<>();
                mainADImgMap.put("password", "F9A75BB045D75998E1509B75ED3A5225");
                getPresenter().getMainADImg(mainADImgMap, false, false);
            }
        }, 4000);    //延时1s执行


        initJsonData();

        getPresenter().getMainPager(false, false);

        TreeMap<String, String> isMsgMap = new TreeMap<>();
        isMsgMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        isMsgMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(isMsgMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getIsMsg(isMsgMap, false, false);
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
                            myListener = new BDAbstractLocationListener() {
                                @Override
                                public void onReceiveLocation(BDLocation bdLocation) {
                                    if (!isEmpty(bdLocation.getCity())) {
                                        tvTopCity.setText(bdLocation.getCity());
                                        SPUtil.put(getContext(), CITY, bdLocation.getCity());
                                    } else
                                        ToastUtil.showLongToast("定位失败!");
                                    LocationService.get().unregisterListener(this);

                                    LocationService.stop(myListener);
                                }
                            };
                            LocationService.start(myListener);
                            break;
                        case 2:
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
//    private void pickCity() {
//        CityPicker.from(this)
//                .enableAnimation(false)
//                .setAnimationStyle(R.style.CityPickerAnimation)
//                .setLocatedCity(null)
//                .setHotCities(mHotCities)
//                .setOnPickListener(new OnPickListener() {
//
//                    @Override
//                    public void onPick(int position, City data) {
//                        tvTopCity.setText(data.getName());
//                        SPUtil.put(getContext(), CITY, data.getName());
//                        LocationService.stop(mListener);
//
//                        Intent intent = new Intent("android.ipd.location");
//                        intent.putExtra("releaseClassId", selectMainPosition == 0 ? "0" : classListBean.get(selectMainPosition - 1).getReleaseClassId() + "");
//                        intent.putExtra("region", tvTopCity.getText());
//                        intent.putExtra("title", "");
//                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        LocationService.stop(mListener);
//                    }
//
//                    @Override
//                    public void onLocate(final OnLocationListener locationListener) {
//                        //开始定位
//                        mListener.setOnLocationListener(locationListener);
//                        LocationService.start(mListener);
//                    }
//                })
//                .show();
//    }

    private void pickCity() {
        pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String city = //options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2);// +
//                        options3Items.get(options1).get(options2).get(options3);
                if (!isEmpty(city)) {
                    tvTopCity.setText(city);
                    SPUtil.put(getContext(), CITY, city);

                    vpFragmentMain.removeAllViewsInLayout();
                    viewPagerAdapter.notifyDataSetChanged();
                    //向集合添加Fragment
                    fragments.clear();
                    for (int i = 0; i < titles.length; i++) {
                        fm = new MainPagerFragment();
                        Bundle args = new Bundle();
                        args.putString("releaseClassId", i == 0 ? "0" : classListBean.get(i - 1).getReleaseClassId() + "");
                        args.putString("region", city);
                        args.putString("title", etTopSearch.getText().toString().trim());
                        fm.setArguments(args);
                        fragments.add(fm);
                    }

                    vpFragmentMain.setAdapter(viewPagerAdapter);
                    vpFragmentMain.setOffscreenPageLimit(titles.length);
                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_city, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvLocation = (TextView) v.findViewById(R.id.tv_location);
                        tvLocation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    rxPermissionTest(1);

                                    vpFragmentMain.removeAllViewsInLayout();
                                    viewPagerAdapter.notifyDataSetChanged();
                                    //向集合添加Fragment
                                    fragments.clear();
                                    for (int i = 0; i < titles.length; i++) {
                                        fm = new MainPagerFragment();
                                        Bundle args = new Bundle();
                                        args.putString("releaseClassId", i == 0 ? "0" : classListBean.get(i - 1).getReleaseClassId() + "");
                                        args.putString("region", tvTopCity.getText().toString().trim());
                                        args.putString("title", etTopSearch.getText().toString().trim());
                                        fm.setArguments(args);
                                        fragments.add(fm);
                                    }

                                    vpFragmentMain.setAdapter(viewPagerAdapter);
                                    vpFragmentMain.setOffscreenPageLimit(titles.length);

                                    pvOptions.dismiss();
                                }
                            }
                        });
                        final TextView tvAllCity = (TextView) v.findViewById(R.id.tv_all_city);
                        tvAllCity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    tvTopCity.setText("全国");
                                    SPUtil.put(getContext(), CITY, "全国");

                                    vpFragmentMain.removeAllViewsInLayout();
                                    viewPagerAdapter.notifyDataSetChanged();
                                    //向集合添加Fragment
                                    fragments.clear();
                                    for (int i = 0; i < titles.length; i++) {
                                        fm = new MainPagerFragment();
                                        Bundle args = new Bundle();
                                        args.putString("releaseClassId", i == 0 ? "0" : classListBean.get(i - 1).getReleaseClassId() + "");
                                        args.putString("region", "");
                                        args.putString("title", etTopSearch.getText().toString().trim());
                                        fm.setArguments(args);
                                        fragments.add(fm);
                                    }

                                    vpFragmentMain.setAdapter(viewPagerAdapter);
                                    vpFragmentMain.setOffscreenPageLimit(titles.length);

                                    pvOptions.dismiss();
                                }
                            }
                        });
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    pvOptions.returnData();
                                    pvOptions.dismiss();
                                }
                            }
                        });
                    }
                })
                .setTitleText("")
                .setCancelText(getResources().getString(R.string.cancel))
                .setSubmitText(getResources().getString(R.string.sure))
                .setOutSideCancelable(true)
                .setTextColorCenter(Color.BLACK)
                .setDividerColor(getResources().getColor(R.color.transparent))
                .setContentTextSize(16)
                .setDecorView((ViewGroup) getActivity().getWindow().getDecorView().findViewById(android.R.id.content))
                .build();
        pvOptions.setPicker(options1Items, options2Items);//二级联动城市选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = getJson(getContext(), "province.json");//获取assets目录下的json文件数据

        ArrayList<CityAddressBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public ArrayList<CityAddressBean> parseData(String result) {//Gson 解析
        ArrayList<CityAddressBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityAddressBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityAddressBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    // 百度定位
//    private static class OnBDLocationListener extends BDAbstractLocationListener {
//
//        private OnLocationListener mOnLocationListener;
//
//        private OnBDLocationListener setOnLocationListener(OnLocationListener onLocationListener) {
//            mOnLocationListener = onLocationListener;
//            return this;
//        }
//
//        @Override
//        public void onReceiveLocation(BDLocation bdLocation) {
//            if (mOnLocationListener != null) {
//                mOnLocationListener.onLocationChanged(new LocatedCity(bdLocation.getCity(), bdLocation.getProvince(), bdLocation.getCityCode()), LocateState.SUCCESS);
//                LocationService.get().unregisterListener(this);
//            }
//        }
//    }

    @OnClick({R.id.ib_top_msg, R.id.ll_top_location})
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
            case R.id.ll_top_location:
                rxPermissionTest(2);
                break;
        }
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
            args.putString("region", tvTopCity.getText().toString().trim());
            args.putString("title", etTopSearch.getText().toString().trim());
            fm.setArguments(args);
            fragments.add(fm);
        }
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpFragmentMain.setAdapter(viewPagerAdapter);
        vpFragmentMain.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslFragmentMain.setViewPager(getContext(), titles, vpFragmentMain, R.color.tx_bottom_navigation, R.color.black, 16, 16, 24, true, R.color.black, 0, 0, 0, 80);
        nfslFragmentMain.setBgLine(getContext(), 1, R.color.whitesmoke);
        nfslFragmentMain.setNavLine(getActivity(), 3, R.color.colorAccent);
    }

    @Override
    public void resultMainList(MainListBean data) {

    }

    @Override
    public void resultAttentionCollection(AttentionCollectionBean data) {

    }

    @Override
    public void resultMainADImg(MainADImgBean data) {
        if (data.getCode() == 200 && !isEmpty(data.getData().getUpadvert().getPicPath())) {
            new MainADImgDialog(getActivity(), data.getData().getUpadvert().getPicPath()) {
                @Override
                public void goSee() {
                    switch (data.getData().getUpadvert().getDetailType()) {
                        case "1":
                            break;
                        case "2":
                            startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 4).putExtra("h5_url", data.getData().getUpadvert().getDetailUrl()));
                            break;
                        case "3":
                            startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 5).putExtra("h5_url", data.getData().getUpadvert().getDetails()));
                            break;
                    }
                }
            }.show();
        }
    }

    @Override
    public void resultIsMsg(IsMsgBean data) {
        if (data.getCode() == 200)
            if (data.getData().getUreads() == 1) {
                mBadges.add(new BadgeView(getContext()).bindTarget(ibTopMsg).setBadgeText("1").setBadgeTextSize(6, true)
                        .setBadgeBackgroundColor(getResources().getColor(R.color.red)).setBadgeTextColor(getResources().getColor(R.color.red))
                        .setBadgePadding(0, true));
            } else {
                for (Badge badge : mBadges) {
                    badge.hide(true);
                }
            }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
