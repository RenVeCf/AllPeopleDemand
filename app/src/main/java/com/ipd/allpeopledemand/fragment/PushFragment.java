package com.ipd.allpeopledemand.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.adapter.FlowTagAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.PushBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.PushContract;
import com.ipd.allpeopledemand.presenter.PushPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.L;
import com.ipd.allpeopledemand.utils.LocationService;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.citypicker.CityPicker;
import com.xuexiang.citypicker.adapter.OnLocationListener;
import com.xuexiang.citypicker.adapter.OnPickListener;
import com.xuexiang.citypicker.model.City;
import com.xuexiang.citypicker.model.HotCity;
import com.xuexiang.citypicker.model.LocateState;
import com.xuexiang.citypicker.model.LocatedCity;
import com.xuexiang.xui.widget.button.RippleView;
import com.xuexiang.xui.widget.flowlayout.FlowTagLayout;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

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
import static com.ipd.allpeopledemand.common.config.IConstants.PHONE;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;

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
    EditText etTitle;
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
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_classification)
    TextView tvClassification;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.riv_upload)
    RadiusImageView rivUpload;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_push)
    RippleView rvPush;

    private FlowTagAdapter tagAdapter;
    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private List<HotCity> mHotCities; //热门城市
    private List<String> classificationDataList = new ArrayList<>();//选择类别
    private List<ClassIficationBean.DataBean.ClassListBean> classListBean = new ArrayList<>();//选择类别(取类别ID用)
    private int releaseClassId;//类别ID
    private String uploadImg = "";//添加的照片地址

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
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvPush);
        tvCity.setText(SPUtil.get(getContext(), CITY, "") + "");
        tvPhone.setText(SPUtil.get(getContext(), PHONE, "") + "");

        tagAdapter = new FlowTagAdapter(getContext());
        ftlKey.setAdapter(tagAdapter);

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
        //关键词
        ftlKey.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                ftlKey.getAdapter().removeElement(position);
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().getClassIfication(true, false);
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
                releaseClassId = classListBean.get(options1).getReleaseClassId();
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

    // 定位权限
    private void rxPermissionTest() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    // 定位
                    pickCity();
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

                    OnBDLocationListener mListener = new OnBDLocationListener();

                    @Override
                    public void onPick(int position, City data) {
                        tvCity.setText(data.getName());
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

    @OnClick({R.id.ll_classification, R.id.ll_city, R.id.bt_add_key, R.id.riv_upload, R.id.rv_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_classification:
                showPickerView();
                break;
            case R.id.ll_city:
                rxPermissionTest();
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
                if (!"选择分类".equals(tvClassification) && !isEmpty(etTitle.getText().toString().trim()) && !"".equals(tvCity.getText().toString().trim()) && !isEmpty(etContact.getText().toString().trim()) && !isEmpty(uploadImg) && !isEmpty(etContent.getText().toString().trim())) {
                    TreeMap<String, String> pushMap = new TreeMap<>();
                    pushMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                    pushMap.put("releaseClassId", releaseClassId + "");
                    pushMap.put("title", etTitle.getText().toString().trim());
                    pushMap.put("region", tvCity.getText().toString().trim());
                    pushMap.put("contacts", etContact.getText().toString().trim());
                    pushMap.put("contactNumber", SPUtil.get(getContext(), PHONE, "") + "");
                    pushMap.put("picPath", uploadImg);
                    pushMap.put("details", etContent.getText().toString().trim());
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
                tvClassification.setText("选择分类");
                etTitle.setText("");
                etContact.setText("");
                rivUpload.setImageResource(R.mipmap.bg_upload_class_room);
                etContent.setText("");
                tagAdapter.clearData();
                tagAdapter.notifyDataSetChanged();
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
