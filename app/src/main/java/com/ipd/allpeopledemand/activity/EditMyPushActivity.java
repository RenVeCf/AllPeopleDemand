package com.ipd.allpeopledemand.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.FlowTagAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.CityAddressBean;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;
import com.ipd.allpeopledemand.bean.MyPushEditBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MyPushEditContract;
import com.ipd.allpeopledemand.presenter.MyPushEditPresenter;
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
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：编辑我的发布
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class EditMyPushActivity extends BaseActivity<MyPushEditContract.View, MyPushEditContract.Presenter> implements MyPushEditContract.View {

    @BindView(R.id.tv_edit_my_push)
    TopView tvEditMyPush;
    @BindView(R.id.et_title)
    MaterialEditText etTitle;
    @BindView(R.id.stv_city)
    SuperTextView stvCity;
    @BindView(R.id.stv_contact_name)
    SuperTextView stvContactName;
    @BindView(R.id.stv_contact_phone)
    SuperTextView stvContactPhone;
    @BindView(R.id.riv_modify)
    RadiusImageView rivModify;
    @BindView(R.id.et_content)
    MultiLineEditText etContent;
    @BindView(R.id.et_modify_key)
    EditText etModifyKey;
    @BindView(R.id.bt_modify_key)
    Button btModifyKey;
    @BindView(R.id.ftl_key)
    FlowTagLayout ftlKey;
    @BindView(R.id.rv_modify_push)
    RippleView rvModifyPush;

    private FlowTagAdapter tagAdapter;
    private MyPushDetailsBean.DataBean.ReleaseBean releaseBean;
    //    private List<HotCity> mHotCities; //热门城市
    private OptionsPickerView pvOptions;
    private String uploadImg = "";//修改照片的地址
    private ArrayList<CityAddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private BDAbstractLocationListener myListener;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_my_push;
    }

    @Override
    public MyPushEditContract.Presenter createPresenter() {
        return new MyPushEditPresenter(this);
    }

    @Override
    public MyPushEditContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvEditMyPush);

        releaseBean = getIntent().getParcelableExtra("details");

        tagAdapter = new FlowTagAdapter(this);
        ftlKey.setAdapter(tagAdapter);

//        mHotCities = new ArrayList<>();
//        mHotCities.add(new HotCity("北京", "北京", "101010100"));
//        mHotCities.add(new HotCity("上海", "上海", "101020100"));
//        mHotCities.add(new HotCity("广州", "广东", "101280101"));
//        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
//        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
    }

    @Override
    public void initData() {
        initJsonData();
        etTitle.setText(releaseBean.getTitle());
        stvCity.setCenterString(releaseBean.getRegion());
        stvContactName.setCenterString(releaseBean.getUserCall());
        stvContactPhone.setCenterString(releaseBean.getContactNumber());
        Glide.with(this).load(BASE_LOCAL_URL + releaseBean.getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.bg_upload_class_room)).into(rivModify);
        uploadImg = releaseBean.getPicPath();
        etContent.setContentText(releaseBean.getDetails());
        if (!isEmpty(releaseBean.getKeyword())) {
            String[] keyWord = releaseBean.getKeyword().split(",");
            for (String str : keyWord) {
                ftlKey.getAdapter().addTag(str);
            }
        }
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
                    getPresenter().getUploadImg(map, false, false);
                    break;
            }
        }
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
                                        stvCity.setCenterString(bdLocation.getCity());
                                        SPUtil.put(EditMyPushActivity.this, CITY, bdLocation.getCity());
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
    /*private void pickCity() {
        CityPicker.from(this)
                .enableAnimation(true)
                .setAnimationStyle(R.style.CityPickerAnimation)
                .setLocatedCity(null)
                .setHotCities(mHotCities)
                .setOnPickListener(new OnPickListener() {

                    OnBDLocationListener mListener = new OnBDLocationListener();

                    @Override
                    public void onPick(int position, City data) {
                        LocationService.stop(mListener);
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
    }*/

    // 百度定位
    /*public static class OnBDLocationListener extends BDAbstractLocationListener {

        private OnLocationListener mOnLocationListener;

        public OnBDLocationListener setOnLocationListener(OnLocationListener onLocationListener) {
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
    }*/

    private void pickCity() {
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String city = //options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2);// +
//                        options3Items.get(options1).get(options2).get(options3);
                stvCity.setCenterString(city);
                SPUtil.put(EditMyPushActivity.this, CITY, city);
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
                                    stvCity.setCenterString("全国");
                                    SPUtil.put(EditMyPushActivity.this, CITY, "全国");
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
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
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
        String JsonData = getJson(this, "province.json");//获取assets目录下的json文件数据

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

    @OnClick({R.id.stv_city, R.id.riv_modify, R.id.bt_modify_key, R.id.rv_modify_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_city:
                if (isFastClick())
                    rxPermissionTest(2);
                break;
            case R.id.riv_modify:
                if (isFastClick()) {
                    PictureSelector.create(EditMyPushActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(1)// 最大图片选择数量 int
                            .isCamera(true)
                            .compress(true)
                            .minimumCompressSize(100)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                }
                break;
            case R.id.bt_modify_key:
                if (isFastClick()) {
                    if (!etModifyKey.getText().toString().trim().equals("")) {
                        ftlKey.getAdapter().addTag(etModifyKey.getText().toString().trim());
                        etModifyKey.setText("");
                    } else
                        ToastUtil.showShortToast("请输入搜索关键词");
                }
                break;
            case R.id.rv_modify_push:
                if (isFastClick()) {
                    if (!isEmpty(etTitle.getText()) && !"".equals(stvCity.getCenterString().trim()) && !isEmpty(etContent.getContentText().trim())) {
                        TreeMap<String, String> pushMap = new TreeMap<>();
                        pushMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                        pushMap.put("releaseId", releaseBean.getReleaseId() + "");
                        pushMap.put("title", etTitle.getText().toString().trim());
                        pushMap.put("region", "全国".equals(stvCity.getCenterString().trim()) ? "0" : stvCity.getCenterString().trim());
                        pushMap.put("picPath", uploadImg);
                        pushMap.put("details", etContent.getContentText().toString().trim());
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
                        getPresenter().getMyPushEdit(pushMap, true, false);
                    } else
                        ToastUtil.showShortToast("请将信息填写完整");
                }
                break;
        }
    }

    @Override
    public void resultMyPushEdit(MyPushEditBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case 900:
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
        switch (data.getCode()) {
            case 200:
                uploadImg = data.getFileName();
                Glide.with(this)
                        .load(BASE_LOCAL_URL + data.getFileName())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                rivModify.setImageDrawable(resource);
                            }
                        });
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
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
