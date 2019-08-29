package com.ipd.allpeopledemand.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.InformationDetailsActivity;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.activity.WebViewActivity;
import com.ipd.allpeopledemand.adapter.MainPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.IsMsgBean;
import com.ipd.allpeopledemand.bean.MainADImgBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.contract.MainPagerContract;
import com.ipd.allpeopledemand.presenter.MainPagerPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.rxutil2.rxjava.RxJavaUtils;
import com.xuexiang.xui.widget.textview.MarqueeTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：首页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/22.
 */
public class MainPagerFragment extends BaseFragment<MainPagerContract.View, MainPagerContract.Presenter> implements MainPagerContract.View {

    @BindView(R.id.tv_horn)
    MarqueeTextView tvHorn;
    @BindView(R.id.ll_sort_time)
    LinearLayout llSortTime;
    //    @BindView(R.id.ll_sort_distance)
//    LinearLayout llSortDistance;
    @BindView(R.id.ll_sort_sales_volume)
    LinearLayout llSortSalesVolume;
    @BindView(R.id.rv_main_page)
    RecyclerView rvMainPage;
    @BindView(R.id.srl_main_page)
    SwipeRefreshLayout srlMainPage;
    @BindView(R.id.iv_sort_time)
    ImageView ivSortTime;
    //    @BindView(R.id.iv_sort_distance)
//    ImageView ivSortDistance;
    @BindView(R.id.iv_sort_sales_volume)
    ImageView ivSortSalesVolume;

    private List<MainListBean.DataBean.ReleaseListBean> releaseListBean = new ArrayList<>();
    private String releaseClassId = "";
    private MainPagerAdapter mainPagerAdapter;
    private int pageNum = 1; //页数
    private String region = "";//搜索时的区域
    private String title = "";//搜索时的文本
    private boolean isFirstPage = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_pager;
    }

    @Override
    public MainPagerContract.Presenter createPresenter() {
        return new MainPagerPresenter(mContext);
    }

    @Override
    public MainPagerContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            releaseClassId = args.getString("releaseClassId");
            region = args.getString("region");
            title = args.getString("title");
        }

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMainPage.setLayoutManager(layoutManager);
        rvMainPage.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMainPage.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMainPage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMainPage.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        sort("", "", pageNum + "");
    }

    private void sort(String orderByColumn, String isAsc, String pageNum) {
        TreeMap<String, String> classRoomPagerMap = new TreeMap<>();
        String userId = "";
        if (SPUtil.get(getActivity(), USER_ID, "") == null)
            userId = "0";
        else if (isEmpty(SPUtil.get(getActivity(), USER_ID, "") + ""))
            userId = "0";
        else
            userId = SPUtil.get(getActivity(), USER_ID, "") + "";

        classRoomPagerMap.put("userId", userId);
        classRoomPagerMap.put("releaseClassId", releaseClassId);
        classRoomPagerMap.put("orderByColumn", orderByColumn);
        classRoomPagerMap.put("isAsc", isAsc);
        classRoomPagerMap.put("pageNum", pageNum);
        classRoomPagerMap.put("region", region);
        classRoomPagerMap.put("title", title);
        classRoomPagerMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomPagerMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getMainList(classRoomPagerMap, true, false);
    }

    @OnClick({R.id.ll_sort_time, R.id.ll_sort_sales_volume})//,R.id.ll_sort_distance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sort_time:
                Drawable.ConstantState drawableSortTime = ivSortTime.getDrawable().getConstantState();
                if (getResources().getDrawable(R.mipmap.ic_default_sc).getConstantState().equals(drawableSortTime)) {
                    ivSortTime.setImageResource(R.mipmap.ic_asc);
//                    ivSortDistance.setImageResource(R.mipmap.ic_default_sc);
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_default_sc);

                    sort("releaseTime", "asc", "1");
                } else if (getResources().getDrawable(R.mipmap.ic_asc).getConstantState().equals(drawableSortTime)) {
                    ivSortTime.setImageResource(R.mipmap.ic_desc);

                    sort("releaseTime", "desc", "1");
                } else {
                    ivSortTime.setImageResource(R.mipmap.ic_default_sc);

                    sort("", "", "1");
                }
                break;
//            case R.id.ll_sort_distance:
//                Drawable.ConstantState drawableSortDistance = ivSortDistance.getDrawable().getConstantState();
//                if (getResources().getDrawable(R.mipmap.ic_default_sc).getConstantState().equals(drawableSortDistance)) {
//                    ivSortDistance.setImageResource(R.mipmap.ic_asc);
//                    ivSortTime.setImageResource(R.mipmap.ic_default_sc);
//                    ivSortSalesVolume.setImageResource(R.mipmap.ic_default_sc);
//                } else if (getResources().getDrawable(R.mipmap.ic_asc).getConstantState().equals(drawableSortDistance))
//                    ivSortDistance.setImageResource(R.mipmap.ic_desc);
//                else
//                    ivSortDistance.setImageResource(R.mipmap.ic_asc);
//                break;
            case R.id.ll_sort_sales_volume:
                Drawable.ConstantState drawableSortSalesVolume = ivSortSalesVolume.getDrawable().getConstantState();
                if (getResources().getDrawable(R.mipmap.ic_default_sc).getConstantState().equals(drawableSortSalesVolume)) {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_asc);
//                    ivSortDistance.setImageResource(R.mipmap.ic_default_sc);
                    ivSortTime.setImageResource(R.mipmap.ic_default_sc);

                    sort("purchaseNum", "asc", "1");
                } else if (getResources().getDrawable(R.mipmap.ic_asc).getConstantState().equals(drawableSortSalesVolume)) {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_desc);

                    sort("purchaseNum", "desc", "1");
                } else {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_default_sc);

                    sort("", "", "1");
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_97:
                    pageNum = 1;
                    initData();
                    break;
            }
        }
    }

    @Override
    public void resultMainPager(ClassIficationBean data) {

    }

    @Override
    public void resultMainList(MainListBean data) {
        if (data.getCode() == 200) {
            String[] myArray = new String[data.getData().getNoticeList().size()];
            for (int i = 0; i < data.getData().getNoticeList().size(); i++) {
                myArray[i] = "【热点】" + data.getData().getNoticeList().get(i).getNoticeContent();
            }
            final List<String> msgData = Arrays.asList(myArray);
            tvHorn.startSimpleRoll(msgData);
            RxJavaUtils.delay(5, new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    tvHorn.removeDisplayString(msgData.get(3));
                }
            });

            if (data.getTotal() > 0) {
                for (int i = 0; i < data.getData().getReleaseList().size(); i++) {
                    data.getData().getReleaseList().get(i).setItemType(Integer.valueOf(data.getData().getReleaseList().get(i).getType()));
                }
                for (int i = 0; i < 10; i++) {
                    if (data.getData().getReleaseList().size() < 10) {
                        data.getData().getReleaseList().add(data.getData().getReleaseList().get(data.getData().getReleaseList().size() - 1));
                    }
                }
                if (pageNum == 1) {
                    if (isFirstPage) {
                        releaseListBean.clear();
                        releaseListBean.addAll(data.getData().getReleaseList());
                        mainPagerAdapter = new MainPagerAdapter(releaseListBean);
                        rvMainPage.setAdapter(mainPagerAdapter);
                        mainPagerAdapter.bindToRecyclerView(rvMainPage);
                        mainPagerAdapter.setEnableLoadMore(true);
                        mainPagerAdapter.openLoadAnimation();
                        mainPagerAdapter.disableLoadMoreIfNotFullPage();
                        isFirstPage = false;
                    } else {
                        mainPagerAdapter.addData(data.getData().getReleaseList());
                    }
                    mainPagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (isFastClick()) {
                                switch (adapter.getItemViewType(position)) {
                                    case 1:
                                        if ("".equals(SPUtil.get(getContext(), IS_LOGIN, "" + "")))
                                            startActivity(new Intent(getActivity(), LoginActivity.class));
                                        else
                                            startActivityForResult(new Intent(getActivity(), InformationDetailsActivity.class).putExtra("releaseId", releaseListBean.get(position).getReleaseId()).putExtra("activityType", 1), REQUEST_CODE_97);
                                        break;
                                    case 2:
                                        if ("".equals(SPUtil.get(getContext(), IS_LOGIN, "" + "")))
                                            startActivity(new Intent(getActivity(), LoginActivity.class));
                                        else {
                                            switch (releaseListBean.get(position).getDetailType()) {
                                                case "1":
                                                    startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 4).putExtra("h5_url", releaseListBean.get(position).getDetailUrl()));
                                                    break;
                                                case "2":
                                                    startActivityForResult(new Intent(getActivity(), InformationDetailsActivity.class).putExtra("releaseId", releaseListBean.get(position).getReleaseId()).putExtra("activityType", 2), REQUEST_CODE_97);
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                    });

                    mainPagerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            if (isFastClick()) {
                                switch (view.getId()) {
                                    case R.id.tv_label: //标签
                                        TextView tv = (TextView) view;
                                        Intent intent = new Intent("android.ipd.labelPosition");
                                        intent.putExtra("label_name", tv.getText());
                                        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                                        break;
//                                    case R.id.cb_collection: //收藏
//                                        if ("".equals(SPUtil.get(getContext(), IS_LOGIN, "" + "")))
//                                            startActivity(new Intent(getActivity(), LoginActivity.class));
//                                        else {
//                                            CheckBox checkBox = (CheckBox) view;
//                                            TreeMap<String, String> attentionCollectionMap = new TreeMap<>();
//                                            attentionCollectionMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
//                                            attentionCollectionMap.put("releaseId", releaseListBean.get(position).getReleaseId() + "");
//                                            attentionCollectionMap.put("isFollow", checkBox.isChecked() ? "2" : "1");
//                                            attentionCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionCollectionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
//                                            getPresenter().getAttentionCollection(attentionCollectionMap, true, false);
//                                        }
//                                        break;
                                }
                            }
                        }
                    });

                    //上拉加载
                    mainPagerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            rvMainPage.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                }
                            }, 1000);
                        }
                    }, rvMainPage);

                    if (data.getTotal() > 10) {
                        pageNum += 1;
                        mainPagerAdapter.loadMoreComplete(); //完成本次
                    } else {
                        pageNum = 1;
                        mainPagerAdapter.loadMoreComplete(); //完成本次
//                        mainPagerAdapter.loadMoreEnd();
                    }
                } else {
                    if ((data.getTotal() - pageNum * 10) > 0) {
                        pageNum += 1;
                        mainPagerAdapter.addData(data.getData().getReleaseList());
                        mainPagerAdapter.loadMoreComplete(); //完成本次
                    } else {
                        pageNum = 1;
                        mainPagerAdapter.addData(data.getData().getReleaseList());
                        mainPagerAdapter.loadMoreComplete(); //完成本次
//                        mainPagerAdapter.loadMoreEnd(); //完成所有加载
                    }
                }
            } else {
                releaseListBean.clear();
                mainPagerAdapter = new MainPagerAdapter(releaseListBean);
                rvMainPage.setAdapter(mainPagerAdapter);
                mainPagerAdapter.loadMoreEnd(); //完成所有加载
                mainPagerAdapter.setEmptyView(R.layout.null_data, rvMainPage);
            }
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public void onDestroyView() {
        tvHorn.clear();
        super.onDestroyView();
    }

    @Override
    public void resultAttentionCollection(AttentionCollectionBean data) {
        ToastUtil.showShortToast(data.getMsg());
        if (data.getCode() == 900) {
            //清除所有临时储存
            SPUtil.clear(ApplicationUtil.getContext());
            ApplicationUtil.getManager().finishActivity(MainActivity.class);
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public void resultMainADImg(MainADImgBean data) {

    }

    @Override
    public void resultIsMsg(IsMsgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
