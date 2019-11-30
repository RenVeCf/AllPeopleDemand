package com.ipd.allpeopledemand.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.adapter.FlowTagAdapter;
import com.ipd.allpeopledemand.aliPay.AliPay;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.CityAddressBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.PushBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.BottomPayDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.PushContract;
import com.ipd.allpeopledemand.presenter.PushPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.LocationService;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

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
import okhttp3.RequestBody;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.ipd.allpeopledemand.activity.InformationActivity.getImageRequestBody;
import static com.ipd.allpeopledemand.common.config.IConstants.CITY;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.IConstants.WECHAT_BT_TYPE;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：发布
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class PushFragment extends BaseFragment<PushContract.View, PushContract.Presenter> implements PushContract.View {

    @BindView(R.id.tv_push)
    TopView tvPush;
    @BindView(R.id.ll_classification)
    LinearLayout llClassification;
    @BindView(R.id.et_title)
    MaterialEditText etTitle;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.et_add_key)
    EditText etAddKey;
    @BindView(R.id.bt_add_key)
    Button btAddKey;
    @BindView(R.id.ftl_key)
    FlowTagLayout ftlKey;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_classification)
    TextView tvClassification;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.riv_upload)
    RadiusImageView rivUpload;
    @BindView(R.id.et_content)
    MultiLineEditText etContent;
    @BindView(R.id.rv_push)
    RippleView rvPush;
    @BindView(R.id.tv_top_description)
    TextView tvTopDescription;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;

    private ReceiveBroadCast receiveBroadCast;
    private FlowTagAdapter tagAdapter;
    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    //    private List<HotCity> mHotCities; //热门城市
    private List<String> classificationDataList = new ArrayList<>();//选择类别
    private List<ClassIficationBean.DataBean.ClassListBean> classListBean = new ArrayList<>();//选择类别(取类别ID用)
    private int releaseClassId;//类别ID
    private String uploadImg = "";//添加的照片地址
    private ArrayList<CityAddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private BDAbstractLocationListener myListener;
    private int rbType = 0; //置顶

    @Override
    public int getLayoutId() {
        return R.layout.fragment_push;
    }

    @Override
    public PushContract.Presenter createPresenter() {
        return new PushPresenter(mContext);
    }

    @Override
    public PushContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ImmersionBar.setTitleBar(this, tvPush);
        tvCity.setText(SPUtil.get(getContext(), CITY, "") + "");
//        etPhone.setText(SPUtil.get(getContext(), PHONE, "") + "");

        tagAdapter = new FlowTagAdapter(getContext());
        ftlKey.setAdapter(tagAdapter);

        int mTheme = R.style.DefaultCityPickerTheme;
        getActivity().setTheme(mTheme);

        tvTopDescription.setText(Html.fromHtml("<font color=\"#E71B64\">注: </font>支付完毕后，我们在本类目进行排序的时候，按照8-3-1- vip-的阶梯来排，排序置顶周期为一周。"));

//        mHotCities = new ArrayList<>();
//        mHotCities.add(new HotCity("北京", "北京", "101010100"));
//        mHotCities.add(new HotCity("上海", "上海", "101020100"));
//        mHotCities.add(new HotCity("广州", "广东", "101280101"));
//        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
//        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
    }

    @Override
    public void initListener() {
        //关键词
        ftlKey.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                ftlKey.getAdapter().removeElement(position);
            }
        });

        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_one:
                        rbType = 0;
                        break;
                    case R.id.rb_two:
                        rbType = 1;
                        break;
                    case R.id.rb_three:
                        rbType = 2;
                        break;
                    case R.id.rb_four:
                        rbType = 3;
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        initJsonData();
        getPresenter().getClassIfication(false, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    String picturePath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    TreeMap<String, RequestBody> map = new TreeMap<>();
                    map.put("file\";filename=\"" + ".jpeg", getImageRequestBody(picturePath));
                    getPresenter().getUploadImg(map, true, false);
                    break;
            }
        }
    }

    //分类条件选择器
    private void showPickerView() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        listData = getClassificationData();
        pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                for (int i = 0; i < classListBean.size(); i++) {
                    if (classListBean.get(i).getClassName().equals(listData.get(options1)))
                        releaseClassId = classListBean.get(i).getReleaseClassId();
                }
                tvClassification.setText(listData.get(options1));
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setDecorView((ViewGroup) getActivity().getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getClassificationData() {
        return classificationDataList;
    }

    class ReceiveBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (receiveBroadCast != null)
                getActivity().unregisterReceiver(receiveBroadCast);

            int isClear = intent.getIntExtra("is_clear", 0);

            if (isClear == 1) {
                tvClassification.setText("选择分类");
                etTitle.setText("");
                etContact.setText("");
                rivUpload.setImageResource(R.mipmap.bg_upload_class_room);
                etContent.setContentText("");
                etPhone.setText("");
                tagAdapter.clearData();
                tagAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("clear_push");
        getActivity().registerReceiver(receiveBroadCast, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (receiveBroadCast != null)
            getActivity().unregisterReceiver(receiveBroadCast);
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
                                        tvCity.setText(bdLocation.getCity());
//                                        SPUtil.put(getContext(), CITY, bdLocation.getCity());
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
//                    OnBDLocationListener mListener = new OnBDLocationListener();
//
//                    @Override
//                    public void onPick(int position, City data) {
//                        tvCity.setText(data.getName());
//                        LocationService.stop(mListener);
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
                tvCity.setText(city);
//                SPUtil.put(getContext(), CITY, city);
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
                                    pvOptions.dismiss();
                                }
                            }
                        });
                        final TextView tvAllCity = (TextView) v.findViewById(R.id.tv_all_city);
                        tvAllCity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    tvCity.setText("全国");
//                                    SPUtil.put(getContext(), CITY, "全国");
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


    @OnClick({R.id.ll_classification, R.id.ll_city, R.id.bt_add_key, R.id.riv_upload, R.id.rv_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_classification:
                showPickerView();
                break;
            case R.id.ll_city:
                rxPermissionTest(2);
                break;
            case R.id.bt_add_key:
                if (!etAddKey.getText().toString().trim().equals("")) {
                    if (tagAdapter.getCount() < 3) {
                        ftlKey.getAdapter().addTag(etAddKey.getText().toString().trim());
                        etAddKey.setText("");
                    } else
                        ToastUtil.showShortToast("限添加3个");

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                } else
                    ToastUtil.showShortToast("请输入搜索关键词");
                break;
            case R.id.riv_upload:
                PictureSelector.create(PushFragment.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.rv_push:
                if (!"选择分类".equals(tvClassification) && !isEmpty(etTitle.getText().toString().trim()) && !"".equals(tvCity.getText().toString().trim()) && !isEmpty(etContact.getText().toString().trim()) && !isEmpty(etContent.getContentText().trim())) {
                    if (rbType > 0) {
                        new BottomPayDialog(getActivity(), 0) {
                            @Override
                            public void goPay(int payType) {
                                switch (payType) {
                                    case 1://支付宝
                                        TreeMap<String, String> pushMap = new TreeMap<>();
                                        pushMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                        pushMap.put("releaseClassId", releaseClassId + "");
                                        pushMap.put("title", etTitle.getText().toString().trim());
                                        pushMap.put("region", "全国".equals(tvCity.getText().toString().trim()) ? "0" : tvCity.getText().toString().trim());
                                        pushMap.put("contacts", etContact.getText().toString().trim());
                                        pushMap.put("contactNumber", etPhone.getText().toString().trim());//SPUtil.get(getContext(), PHONE, "") + "");
                                        pushMap.put("picPath", uploadImg);
                                        pushMap.put("details", etContent.getContentText().trim());
                                        pushMap.put("stick", rbType + "");
                                        pushMap.put("payway", rbType == 0 ? "0" : "2");
                                        pushMap.put("transactionId", "");
                                        pushMap.put("payload", "");
                                        pushMap.put("type", "");
                                        StringBuilder str = new StringBuilder();
                                        for (int i = 0; i < tagAdapter.getCount(); i++) {
                                            if (i < tagAdapter.getCount() - 1 && tagAdapter.getCount() > 1) {
                                                str.append(tagAdapter.getItem(i) + ",");
                                            } else {
                                                str.append(tagAdapter.getItem(i));
                                            }
                                        }
                                        pushMap.put("keyword", tagAdapter.getCount() > 0 ? str.toString() : "");
                                        pushMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(pushMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                        getPresenter().getPush(pushMap, true, false);
                                        break;
                                    case 2://微信
                                        TreeMap<String, String> pushMap1 = new TreeMap<>();
                                        pushMap1.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                        pushMap1.put("releaseClassId", releaseClassId + "");
                                        pushMap1.put("title", etTitle.getText().toString().trim());
                                        pushMap1.put("region", "全国".equals(tvCity.getText().toString().trim()) ? "0" : tvCity.getText().toString().trim());
                                        pushMap1.put("contacts", etContact.getText().toString().trim());
                                        pushMap1.put("contactNumber", etPhone.getText().toString().trim());//SPUtil.get(getContext(), PHONE, "") + "");
                                        pushMap1.put("picPath", uploadImg);
                                        pushMap1.put("details", etContent.getContentText().trim());
                                        pushMap1.put("stick", rbType + "");
                                        pushMap1.put("payway", rbType == 0 ? "0" : "1");
                                        pushMap1.put("transactionId", "");
                                        pushMap1.put("payload", "");
                                        pushMap1.put("type", "");
                                        StringBuilder str1 = new StringBuilder();
                                        for (int i = 0; i < tagAdapter.getCount(); i++) {
                                            if (i < tagAdapter.getCount() - 1 && tagAdapter.getCount() > 1) {
                                                str1.append(tagAdapter.getItem(i) + ",");
                                            } else {
                                                str1.append(tagAdapter.getItem(i));
                                            }
                                        }
                                        pushMap1.put("keyword", tagAdapter.getCount() > 0 ? str1.toString() : "");
                                        pushMap1.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(pushMap1.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                        getPresenter().getPush(pushMap1, true, false);
                                        break;
                                    default:
                                        ToastUtil.showShortToast("请选择支付方式！");
                                        break;
                                }
                            }
                        }.show();
                    } else {
                        TreeMap<String, String> pushMap = new TreeMap<>();
                        pushMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                        pushMap.put("releaseClassId", releaseClassId + "");
                        pushMap.put("title", etTitle.getText().toString().trim());
                        pushMap.put("region", "全国".equals(tvCity.getText().toString().trim()) ? "0" : tvCity.getText().toString().trim());
                        pushMap.put("contacts", etContact.getText().toString().trim());
                        pushMap.put("contactNumber", etPhone.getText().toString().trim());//SPUtil.get(getContext(), PHONE, "") + "");
                        pushMap.put("picPath", uploadImg);
                        pushMap.put("details", etContent.getContentText().trim());
                        pushMap.put("stick", rbType + "");
                        pushMap.put("payway", "0");
                        pushMap.put("transactionId", "");
                        pushMap.put("payload", "");
                        pushMap.put("type", "");
                        StringBuilder str = new StringBuilder();
                        for (int i = 0; i < tagAdapter.getCount(); i++) {
                            if (i < tagAdapter.getCount() - 1 && tagAdapter.getCount() > 1) {
                                str.append(tagAdapter.getItem(i) + ",");
                            } else {
                                str.append(tagAdapter.getItem(i));
                            }
                        }
                        pushMap.put("keyword", tagAdapter.getCount() > 0 ? str.toString() : "");
                        pushMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(pushMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                        getPresenter().getPush(pushMap, true, false);
                    }
                } else
                    ToastUtil.showShortToast("请将信息填写完整");
                break;
        }
    }

    @Override
    public void resultPush(PushBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                if (!isEmpty(data.getData().getSign2()))
                    new AliPay(getActivity(), data.getData().getSign2(), 2);
                else if (data.getData().getSign1() != null) {
                    SPUtil.put(getContext(), WECHAT_BT_TYPE, 2);

                    IWXAPI api = WXAPIFactory.createWXAPI(getContext(), null);
                    api.registerApp("wx57313d36c4b4d0d7");
                    PayReq req = new PayReq();
                    req.appId = data.getData().getSign1().getAppid();//你的微信appid
                    req.partnerId = data.getData().getSign1().getPartnerid();//商户号
                    req.prepayId = data.getData().getSign1().getPrepayid();//预支付交易会话ID
                    req.nonceStr = data.getData().getSign1().getNoncestr();//随机字符串
                    req.timeStamp = data.getData().getSign1().getTimestamp() + "";//时间戳
                    req.packageValue = data.getData().getSign1().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
                    req.sign = data.getData().getSign1().getSign();//签名
                    //  req.extData         = "app data"; // optional
                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                    api.sendReq(req);
                } else {
                    tvClassification.setText("选择分类");
                    etTitle.setText("");
                    etContact.setText("");
                    rivUpload.setImageResource(R.mipmap.bg_upload_class_room);
                    etContent.setContentText("");
                    etPhone.setText("");
                    tagAdapter.clearData();
                    tagAdapter.notifyDataSetChanged();
                }
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultClassIfication(ClassIficationBean data) {
        switch (data.getCode()) {
            case 200:
                classListBean.clear();
                classListBean.addAll(data.getData().getClassList());
                for (ClassIficationBean.DataBean.ClassListBean classListBean : data.getData().getClassList()) {
                    if ("2".equals(classListBean.getIsNot()))
                        classificationDataList.add(classListBean.getClassName());
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

    @Override
    public void resultUploadImg(UploadImgBean data) {
        switch (data.getCode()) {
            case 200:
                uploadImg = data.getFileName();
                Glide.with(this)
                        .load(BASE_LOCAL_URL + data.getFileName())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                rivUpload.setImageDrawable(resource);
                            }
                        });
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
