package com.ipd.allpeopledemand.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.ClassRoomDetailsActivity;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.activity.WebViewActivity;
import com.ipd.allpeopledemand.adapter.ClassRoomPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.common.view.BottomPayDialog;
import com.ipd.allpeopledemand.common.view.ClassRoomPayPromptDialog;
import com.ipd.allpeopledemand.common.view.NotIntegralDialog;
import com.ipd.allpeopledemand.contract.ClassRoomPagerContract;
import com.ipd.allpeopledemand.presenter.ClassRoomPagerPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：全民课堂
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public class ClassRoomPagerFragment extends BaseFragment<ClassRoomPagerContract.View, ClassRoomPagerContract.Presenter> implements ClassRoomPagerContract.View {

    @BindView(R.id.iv_sort_time)
    ImageView ivSortTime;
    @BindView(R.id.ll_sort_time)
    LinearLayout llSortTime;
    @BindView(R.id.iv_sort_sales_volume)
    ImageView ivSortSalesVolume;
    @BindView(R.id.ll_sort_sales_volume)
    LinearLayout llSortSalesVolume;
    @BindView(R.id.rv_class_room_pager)
    RecyclerView rvClassRoomPager;
    @BindView(R.id.srl_class_room_pager)
    SwipeRefreshLayout srlClassRoomPager;

    private List<ClassRoomPagerBean.DataBean.RoomListBean> roomListBean = new ArrayList<>();
    private ClassRoomPagerBean.DataBean.PriceBean priceBean = new ClassRoomPagerBean.DataBean.PriceBean();
    private ClassRoomPagerAdapter classRoomPagerAdapter;
    private int pageNum = 1;//页数
    private String roomClassId = "";
    private ClassRoomDetailsBean.DataBean.RoomDetailsBean roomDetailsBean = new ClassRoomDetailsBean.DataBean.RoomDetailsBean();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class_room_pager;
    }

    @Override
    public ClassRoomPagerContract.Presenter createPresenter() {
        return new ClassRoomPagerPresenter(mContext);
    }

    @Override
    public ClassRoomPagerContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {

        Bundle args = getArguments();
        if (args != null) {
            roomClassId = args.getString("roomClassId");
        }

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvClassRoomPager.setLayoutManager(layoutManager);
        rvClassRoomPager.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvClassRoomPager.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlClassRoomPager.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        sort("", "", pageNum + "", "");
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlClassRoomPager.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlClassRoomPager.setRefreshing(false);
            }
        });
    }

    @OnClick({R.id.ll_sort_time, R.id.ll_sort_sales_volume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sort_time:
                Drawable.ConstantState drawableSortTime = ivSortTime.getDrawable().getConstantState();
                if (getResources().getDrawable(R.mipmap.ic_default_sc).getConstantState().equals(drawableSortTime)) {
                    ivSortTime.setImageResource(R.mipmap.ic_asc);
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_default_sc);

                    sort("videoDate", "asc", "1", "");
                } else if (getResources().getDrawable(R.mipmap.ic_asc).getConstantState().equals(drawableSortTime)) {
                    ivSortTime.setImageResource(R.mipmap.ic_desc);

                    sort("videoDate", "desc", "1", "");
                } else {
                    ivSortTime.setImageResource(R.mipmap.ic_asc);

                    sort("videoDate", "asc", "1", "");
                }
                break;
            case R.id.ll_sort_sales_volume:
                Drawable.ConstantState drawableSortSalesVolume = ivSortSalesVolume.getDrawable().getConstantState();
                if (getResources().getDrawable(R.mipmap.ic_default_sc).getConstantState().equals(drawableSortSalesVolume)) {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_asc);
                    ivSortTime.setImageResource(R.mipmap.ic_default_sc);

                    sort("purchaseNum", "asc", "1", "");
                } else if (getResources().getDrawable(R.mipmap.ic_asc).getConstantState().equals(drawableSortSalesVolume)) {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_desc);

                    sort("purchaseNum", "desc", "1", "");
                } else {
                    ivSortSalesVolume.setImageResource(R.mipmap.ic_asc);

                    sort("purchaseNum", "asc", "1", "");
                }
                break;
        }
    }

    public void sort(String orderByColumn, String isAsc, String pageNum, String title) {
        TreeMap<String, String> classRoomPagerMap = new TreeMap<>();
        classRoomPagerMap.put("roomClassId", roomClassId);
        classRoomPagerMap.put("orderByColumn", orderByColumn);
        classRoomPagerMap.put("isAsc", isAsc);
        classRoomPagerMap.put("pageNum", pageNum);
        classRoomPagerMap.put("title", title);
        classRoomPagerMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomPagerMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getClassRoomPager(classRoomPagerMap, false, false);
    }

    @Override
    public void resultClassRoomPager(ClassRoomPagerBean data) {
        if (data.getTotal() > 0) {
            priceBean = data.getData().getPrice();
            for (int i = 0; i < data.getData().getRoomList().size(); i++) {
                data.getData().getRoomList().get(i).setIntegral(priceBean.getIntegral());
                data.getData().getRoomList().get(i).setMoney(priceBean.getMoney());
            }

            if (pageNum == 1) {
                roomListBean.clear();
                roomListBean.addAll(data.getData().getRoomList());
                classRoomPagerAdapter = new ClassRoomPagerAdapter(roomListBean);
                rvClassRoomPager.setAdapter(classRoomPagerAdapter);
                classRoomPagerAdapter.bindToRecyclerView(rvClassRoomPager);
                classRoomPagerAdapter.setEnableLoadMore(true);
                classRoomPagerAdapter.openLoadAnimation();
                classRoomPagerAdapter.disableLoadMoreIfNotFullPage();

                classRoomPagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if ("".equals(SPUtil.get(getContext(), IS_LOGIN, "" + "")))
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        else {
                            TreeMap<String, String> classRoomDetailsMap = new TreeMap<>();
                            classRoomDetailsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                            classRoomDetailsMap.put("classroomId", data.getData().getRoomList().get(position).getClassroomId() + "");
                            classRoomDetailsMap.put("priceId", priceBean.getPriceId() + "");
                            classRoomDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                            getPresenter().getClassRoomDetails(classRoomDetailsMap, false, false);
                        }
                    }
                });

                //上拉加载
                classRoomPagerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvClassRoomPager.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvClassRoomPager);

                if (data.getTotal() > 10) {
                    pageNum += 1;
                } else {
                    classRoomPagerAdapter.loadMoreEnd();
                }
            } else {
                if ((data.getTotal() - pageNum * 10) > 0) {
                    pageNum += 1;
                    classRoomPagerAdapter.addData(data.getData().getRoomList());
                    classRoomPagerAdapter.loadMoreComplete(); //完成本次
                } else {
                    classRoomPagerAdapter.addData(data.getData().getRoomList());
                    classRoomPagerAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            roomListBean.clear();
            classRoomPagerAdapter = new ClassRoomPagerAdapter(roomListBean);
            rvClassRoomPager.setAdapter(classRoomPagerAdapter);
            classRoomPagerAdapter.loadMoreEnd(); //完成所有加载
            classRoomPagerAdapter.setEmptyView(R.layout.null_data, rvClassRoomPager);
        }
    }

    @Override
    public void resultClassRoomDetails(ClassRoomDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                roomDetailsBean = data.getData().getRoomDetails();
                switch (data.getData().getIsPurchase()) {
                    case "1":
                        startActivity(new Intent(getActivity(), ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean).putExtra("integral", priceBean.getIntegral()).putExtra("money", priceBean.getMoney()));
                        break;
                    case "2":
                        startActivity(new Intent(getActivity(), ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean));
                        break;
                    case "3":
                        new NotIntegralDialog(getActivity()) {
                            @Override
                            public void goPayIntrgral() {
                                //积分不足跳积分规则
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 1));
                            }
                        }.show();
                        break;
                    case "4":
                        new ClassRoomPayPromptDialog(getActivity(), priceBean.getMoney(), priceBean.getIntegral()) {
                            @Override
                            public void goPay() {
                                new BottomPayDialog(getActivity(), data.getData().getBalance()) {
                                    @Override
                                    public void goPay(int payType, boolean isRewardBalance) {
                                        startActivity(new Intent(getActivity(), ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean));
//                                        TreeMap<String, String> classRoomAliPayMap = new TreeMap<>();
//                                        classRoomAliPayMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
//                                        classRoomAliPayMap.put("classroomId", data.getData().getRoomDetails().getClassroomId() + "");
//                                        classRoomAliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomAliPayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
//                                        getPresenter().getClassRoomAliPay(classRoomAliPayMap, false, false);
                                    }
                                }.show();
                            }
                        }.show();
                        break;
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
    public void resultClassRoomAliPay(ClassRoomAliPayBean data) {
//        ToastUtil.showLongToast(data.getMsg());
//        if (data.getCode() == 900)
//            startActivity(new Intent(getActivity(), ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean));
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
