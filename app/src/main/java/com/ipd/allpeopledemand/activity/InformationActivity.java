package com.ipd.allpeopledemand.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.InformationBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.InformationContract;
import com.ipd.allpeopledemand.presenter.InformationPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.ipd.allpeopledemand.common.config.IConstants.AGE;
import static com.ipd.allpeopledemand.common.config.IConstants.ALL_PEOPLE;
import static com.ipd.allpeopledemand.common.config.IConstants.AVATAR;
import static com.ipd.allpeopledemand.common.config.IConstants.MARITAL_STATUS;
import static com.ipd.allpeopledemand.common.config.IConstants.NAME;
import static com.ipd.allpeopledemand.common.config.IConstants.PHONE;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.allpeopledemand.common.config.IConstants.SEX;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：个人资料
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class InformationActivity extends BaseActivity<InformationContract.View, InformationContract.Presenter> implements InformationContract.View {

    @BindView(R.id.tv_information)
    TopView tvInformation;
    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.riv_head)
    RadiusImageView rivHead;
    @BindView(R.id.stv_nickname)
    SuperTextView stvNickname;
    @BindView(R.id.stv_phone)
    SuperTextView stvPhone;
    @BindView(R.id.stv_all_people_code)
    SuperTextView stvAllPeopleCode;
    @BindView(R.id.stv_sex)
    SuperTextView stvSex;
    @BindView(R.id.stv_age)
    SuperTextView stvAge;
    @BindView(R.id.stv_marital)
    SuperTextView stvMarital;

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器

    @Override
    public int getLayoutId() {
        return R.layout.activity_information;
    }

    @Override
    public InformationContract.Presenter createPresenter() {
        return new InformationPresenter(this);
    }

    @Override
    public InformationContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvInformation);

        Glide.with(this).load(BASE_LOCAL_URL + SPUtil.get(this, AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(rivHead);
        stvNickname.setRightString(SPUtil.get(this, NAME, "") + "");
        stvPhone.setRightString(SPUtil.get(this, PHONE, "") + "");
        stvAllPeopleCode.setRightString(SPUtil.get(this, ALL_PEOPLE, "") + "");
        stvSex.setRightString(SPUtil.get(this, SEX, "") + "");
        stvAge.setRightString(SPUtil.get(this, AGE, "") + "");
        stvMarital.setRightString(SPUtil.get(this, MARITAL_STATUS, "") + "");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    //条件选择器
    private void showPickerView(final int type) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        switch (type) {
            case 1:
                listData = getSexData();
                break;
            case 2:
                listData = getAgeData();
                break;
            case 3:
                listData = getMaritalData();
                break;
        }
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                switch (type) {
                    case 1:
                        ModifyInformation("", "男".equals(listData.get(options1)) ? 1 : 2, "", 0);
                        stvSex.setRightString(listData.get(options1));
                        break;
                    case 2:
                        ModifyInformation("", 0, listData.get(options1).replaceAll("岁", ""), 0);
                        stvAge.setRightString(listData.get(options1));
                        break;
                    case 3:
                        ModifyInformation("", 0, "", "未婚".equals(listData.get(options1)) ? 1 : 2);
                        stvMarital.setRightString(listData.get(options1));
                        break;
                }
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
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getSexData() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        return list;
    }

    private List<String> getAgeData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "岁");
        }
        return list;
    }

    private List<String> getMaritalData() {
        List<String> list = new ArrayList<>();
        list.add("未婚");
        list.add("已婚");
        return list;
    }

    public static RequestBody getImageRequestBody(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        return RequestBody.create(MediaType.parse(options.outMimeType), new File(filePath));
    }

    private void ModifyInformation(String avatar, int sex, String age, int maritalStatus) {
        TreeMap<String, String> informationMap = new TreeMap<>();
        informationMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        informationMap.put("avatar", avatar);
        informationMap.put("sex", sex + "");
        informationMap.put("age", age);
        informationMap.put("maritalStatus", maritalStatus + "");
        informationMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(informationMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getInformation(informationMap, true, false);
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
                case REQUEST_CODE_91:
                    stvNickname.setRightString(data.getStringExtra("nickname"));
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_head, R.id.stv_nickname, R.id.stv_sex, R.id.stv_age, R.id.stv_marital, R.id.ll_top_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.stv_nickname:
                startActivityForResult(new Intent(this, NickNameActivity.class), REQUEST_CODE_91);
                break;
            case R.id.stv_sex:
                showPickerView(1);
                break;
            case R.id.stv_age:
                showPickerView(2);
                break;
            case R.id.stv_marital:
                showPickerView(3);
                break;
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
        }
    }

    @Override
    public void resultInformation(InformationBean data) {
        ToastUtil.showShortToast(data.getMsg());
        if (data.getCode() == 900) {
            //清除所有临时储存
            SPUtil.clear(ApplicationUtil.getContext());
            ApplicationUtil.getManager().finishActivity(MainActivity.class);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        switch (data.getCode()) {
            case 200:
                SPUtil.put(this, AVATAR, data.getFileName());
                Glide.with(this)
                        .load(BASE_LOCAL_URL + data.getFileName())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                rivHead.setImageDrawable(resource);
                            }
                        });

                ModifyInformation(data.getFileName(), 0, "", 0);
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
