package com.ipd.allpeopledemand.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.FlowTagAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
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
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

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
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;

/**
 * Description ：编辑我的发布
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class EditMyPushActivity extends BaseActivity<MyPushEditContract.View, MyPushEditContract.Presenter> implements MyPushEditContract.View {

    @BindView(R.id.tv_edit_my_push)
    TopView tvEditMyPush;
    @BindView(R.id.stv_title)
    SuperTextView stvTitle;
    @BindView(R.id.stv_city)
    SuperTextView stvCity;
    @BindView(R.id.stv_contact_name)
    SuperTextView stvContactName;
    @BindView(R.id.stv_contact_phone)
    SuperTextView stvContactPhone;
    @BindView(R.id.riv_modify)
    RadiusImageView rivModify;
    @BindView(R.id.et_content)
    EditText etContent;
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
    private List<HotCity> mHotCities; //热门城市
    private String uploadImg = "";//修改照片的地址

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

        mHotCities = new ArrayList<>();
        mHotCities.add(new HotCity("北京", "北京", "101010100"));
        mHotCities.add(new HotCity("上海", "上海", "101020100"));
        mHotCities.add(new HotCity("广州", "广东", "101280101"));
        mHotCities.add(new HotCity("深圳", "广东", "101280601"));
        mHotCities.add(new HotCity("杭州", "浙江", "101210101"));
    }

    @Override
    public void initData() {
        stvTitle.setCenterEditString(releaseBean.getTitle());
        stvCity.setCenterString(releaseBean.getRegion());
        stvContactName.setCenterString(releaseBean.getUserCall());
        stvContactPhone.setCenterString(releaseBean.getContactNumber());
        Glide.with(this).load(BASE_LOCAL_URL + releaseBean.getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.bg_upload_class_room)).into(rivModify);
        uploadImg = releaseBean.getPicPath();
        etContent.setText(releaseBean.getDetails());
        String[] keyWord = releaseBean.getKeyword().split(",");
        for (String str : keyWord) {
            ftlKey.getAdapter().addTag(str);
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
    }

    // 百度定位
    public static class OnBDLocationListener extends BDAbstractLocationListener {

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
    }

    @OnClick({R.id.stv_city, R.id.riv_modify, R.id.bt_modify_key, R.id.rv_modify_push})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_city:
                rxPermissionTest();
                break;
            case R.id.riv_modify:
                PictureSelector.create(EditMyPushActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.bt_modify_key:
                if (!etModifyKey.getText().toString().trim().equals("")) {
                    ftlKey.getAdapter().addTag(etModifyKey.getText().toString().trim());
                    etModifyKey.setText("");
                } else
                    ToastUtil.showShortToast("请输入搜索关键词");
                break;
            case R.id.rv_modify_push:
                if (!isEmpty(stvTitle.getCenterEditValue()) && !"".equals(stvCity.getCenterString().trim()) && !isEmpty(etContent.getText().toString().trim())) {
                    TreeMap<String, String> pushMap = new TreeMap<>();
                    pushMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    pushMap.put("releaseId", releaseBean.getReleaseId() + "");
                    pushMap.put("title", stvTitle.getCenterEditValue());
                    pushMap.put("region", stvCity.getCenterString().trim());
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
                    getPresenter().getMyPushEdit(pushMap, true, false);
                } else
                    ToastUtil.showShortToast("请将信息填写完整");
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
