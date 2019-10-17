package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.MyBuyClassRoomAdapter;
import com.ipd.allpeopledemand.adapter.MyBuyDemandAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomListBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandDetailsBean;
import com.ipd.allpeopledemand.bean.MyBuyDemandListBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MyBuyContract;
import com.ipd.allpeopledemand.presenter.MyBuyPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_99;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：我的购买
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class MyBuyActivity extends BaseActivity<MyBuyContract.View, MyBuyContract.Presenter> implements MyBuyContract.View {

    @BindView(R.id.tv_my_buy)
    TopView tvMyBuy;
    //    @BindView(R.id.rb_point)
//    RadioButton rbPoint;
//    @BindView(R.id.rb_reward)
//    RadioButton rbReward;
    @BindView(R.id.rv_my_buy)
    RecyclerView rvMyBuy;
    @BindView(R.id.srl_my_buy)
    SwipeRefreshLayout srlMyBuy;

    private List<MyBuyDemandListBean.DataBean.DemandListBean> demandListBean = new ArrayList<>();
    private MyBuyDemandAdapter myBuyDemandAdapter;
    private List<MyBuyClassRoomListBean.DataBean.RoomListBean> roomListBean = new ArrayList<>();
    private MyBuyClassRoomAdapter myBuyClassRoomAdapter;
    private int buyType = 2;//1:需求咨询，2:课堂教程
    private int pricePosition;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_buy;
    }

    @Override
    public MyBuyContract.Presenter createPresenter() {
        return new MyBuyPresenter(this);
    }

    @Override
    public MyBuyContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMyBuy);

//        rbPoint.setText("需求咨询");
//        rbReward.setText("课堂教程");

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMyBuy.setLayoutManager(layoutManager);
        rvMyBuy.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMyBuy.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMyBuy.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        switch (buyType) {
            case 1:
                TreeMap<String, String> demandMap = new TreeMap<>();
                demandMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                demandMap.put("pageNum", pageNum + "");
                demandMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(demandMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMyBuyDemandList(demandMap, true, false);
                break;
            case 2:
                TreeMap<String, String> classRoomMap = new TreeMap<>();
                classRoomMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                classRoomMap.put("pageNum", pageNum + "");
                classRoomMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getMyBuyClassRoomList(classRoomMap, true, false);
                break;
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMyBuy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMyBuy.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_99:
                    pageNum = 1;
                    initData();
                    break;
            }
        }
    }

    @OnClick({R.id.rb_point, R.id.rb_reward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_point:
                buyType = 1;
                pageNum = 1;
                initData();
                break;
            case R.id.rb_reward:
                buyType = 2;
                pageNum = 1;
                initData();
                break;
        }
    }

    @Override
    public void resultMyBuyDemandList(MyBuyDemandListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        demandListBean.clear();
                        demandListBean.addAll(data.getData().getDemandList());
                        myBuyDemandAdapter = new MyBuyDemandAdapter(demandListBean);
                        rvMyBuy.setAdapter(myBuyDemandAdapter);
                        myBuyDemandAdapter.bindToRecyclerView(rvMyBuy);
                        myBuyDemandAdapter.setEnableLoadMore(true);
                        myBuyDemandAdapter.openLoadAnimation();
                        myBuyDemandAdapter.disableLoadMoreIfNotFullPage();

                        myBuyDemandAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivityForResult(new Intent(MyBuyActivity.this, InformationDetailsActivity.class).putExtra("orderId", demandListBean.get(position).getOrderId()).putExtra("releaseId", demandListBean.get(position).getReleaseId()).putExtra("activityType", 5), REQUEST_CODE_99);
                            }
                        });

                        myBuyDemandAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.cb_collection: //收藏
                                        CheckBox checkBox = (CheckBox) view;
                                        TreeMap<String, String> attentionCollectionMap = new TreeMap<>();
                                        attentionCollectionMap.put("userId", SPUtil.get(MyBuyActivity.this, USER_ID, "") + "");
                                        attentionCollectionMap.put("releaseId", demandListBean.get(position).getReleaseId() + "");
                                        attentionCollectionMap.put("isFollow", checkBox.isChecked() ? "2" : "1");
                                        attentionCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionCollectionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                        getPresenter().getAttentionCollection(attentionCollectionMap, true, false);
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        myBuyDemandAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvMyBuy.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvMyBuy);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            myBuyDemandAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            myBuyDemandAdapter.addData(data.getData().getDemandList());
                            myBuyDemandAdapter.loadMoreComplete(); //完成本次
                        } else {
                            myBuyDemandAdapter.addData(data.getData().getDemandList());
                            myBuyDemandAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    demandListBean.clear();
                    myBuyDemandAdapter = new MyBuyDemandAdapter(demandListBean);
                    rvMyBuy.setAdapter(myBuyDemandAdapter);
                    myBuyDemandAdapter.loadMoreEnd(); //完成所有加载
                    myBuyDemandAdapter.setEmptyView(R.layout.null_data, rvMyBuy);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultMyBuyDemandDetails(MyBuyDemandDetailsBean data) {

    }

    @Override
    public void resultMyBuyClassRoomList(MyBuyClassRoomListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        roomListBean.clear();
                        roomListBean.addAll(data.getData().getRoomList());
                        myBuyClassRoomAdapter = new MyBuyClassRoomAdapter(roomListBean);
                        rvMyBuy.setAdapter(myBuyClassRoomAdapter);
                        myBuyClassRoomAdapter.bindToRecyclerView(rvMyBuy);
                        myBuyClassRoomAdapter.setEnableLoadMore(true);
                        myBuyClassRoomAdapter.openLoadAnimation();
                        myBuyClassRoomAdapter.disableLoadMoreIfNotFullPage();

                        myBuyClassRoomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                pricePosition = position;
                                TreeMap<String, String> demandMap = new TreeMap<>();
                                demandMap.put("userId", SPUtil.get(MyBuyActivity.this, USER_ID, "") + "");
                                demandMap.put("classroomId", roomListBean.get(position).getClassroomId() + "");
                                demandMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(demandMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                getPresenter().getMyBuyClassRoomDetails(demandMap, true, false);
                            }
                        });

                        //上拉加载
                        myBuyClassRoomAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvMyBuy.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvMyBuy);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            myBuyClassRoomAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            myBuyClassRoomAdapter.addData(data.getData().getRoomList());
                            myBuyClassRoomAdapter.loadMoreComplete(); //完成本次
                        } else {
                            myBuyClassRoomAdapter.addData(data.getData().getRoomList());
                            myBuyClassRoomAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    roomListBean.clear();
                    myBuyClassRoomAdapter = new MyBuyClassRoomAdapter(roomListBean);
                    rvMyBuy.setAdapter(myBuyClassRoomAdapter);
                    myBuyClassRoomAdapter.loadMoreEnd(); //完成所有加载
                    myBuyClassRoomAdapter.setEmptyView(R.layout.null_data, rvMyBuy);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultMyBuyClassRoomDetails(MyBuyClassRoomDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getData().getRoomDetails() != null) {
                    ClassRoomDetailsBean.DataBean.RoomDetailsBean roomDetailsBean = new ClassRoomDetailsBean.DataBean.RoomDetailsBean();
                    roomDetailsBean.setType(data.getData().getRoomDetails().getType());
                    roomDetailsBean.setThumbnail(data.getData().getRoomDetails().getThumbnail());
                    roomDetailsBean.setTitle(data.getData().getRoomDetails().getTitle());
                    roomDetailsBean.setContent(data.getData().getRoomDetails().getContent());
                    roomDetailsBean.setAudioType(data.getData().getRoomDetails().getAudioType());
                    roomDetailsBean.setAudioUrl(data.getData().getRoomDetails().getAudioUrl());
                    roomDetailsBean.setAudioFile(data.getData().getRoomDetails().getAudioFile());
                    roomDetailsBean.setVideoType(data.getData().getRoomDetails().getVideoType());
                    roomDetailsBean.setVideoUrl(data.getData().getRoomDetails().getVideoUrl());
                    roomDetailsBean.setAudioFile(data.getData().getRoomDetails().getAudioFile());
                    roomDetailsBean.setWatchNum(data.getData().getRoomDetails().getWatchNum());
                    roomDetailsBean.setCreateTime(data.getData().getRoomDetails().getCreateTime());
                    startActivity(new Intent(MyBuyActivity.this, ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean).putExtra("integral", roomListBean.get(pricePosition).getIntegral()).putExtra("money", roomListBean.get(pricePosition).getMoney()));
                } else
                    ToastUtil.showShortToast("该课程已下架！");
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            default:
                ToastUtil.showShortToast(data.getMsg());
                break;
        }
    }

    @Override
    public void resultAttentionCollection(AttentionCollectionBean data) {
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
