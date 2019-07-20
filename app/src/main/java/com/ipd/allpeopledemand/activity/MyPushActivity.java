package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.MyPushAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.MyPushListBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MyPushListContract;
import com.ipd.allpeopledemand.presenter.MyPushListPresenter;
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

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_93;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_94;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：我的发布
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class MyPushActivity extends BaseActivity<MyPushListContract.View, MyPushListContract.Presenter> implements MyPushListContract.View {

    @BindView(R.id.tv_my_push)
    TopView tvMyPush;
    @BindView(R.id.rb_point)
    RadioButton rbPoint;
    @BindView(R.id.rb_reward)
    RadioButton rbReward;
    @BindView(R.id.rv_my_push_online)
    RecyclerView rvMyPushOnline;
    @BindView(R.id.srl_my_push_online)
    SwipeRefreshLayout srlMyPushOnline;
    @BindView(R.id.rv_my_push_offline)
    RecyclerView rvMyPushOffline;
    @BindView(R.id.srl_my_push_offline)
    SwipeRefreshLayout srlMyPushOffline;

    private List<MyPushListBean.DataBean.ReleaseListBean> releaseListBean = new ArrayList<>();
    private MyPushAdapter myPushAdapter;
    private int pushType = 1;//1上架中 2.已下架
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_push;
    }

    @Override
    public MyPushListContract.Presenter createPresenter() {
        return new MyPushListPresenter(this);
    }

    @Override
    public MyPushListContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMyPush);

        rbPoint.setText("上架中");
        rbReward.setText("已下架");

        // 上架设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMyPushOnline.setLayoutManager(layoutManager);
        rvMyPushOnline.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMyPushOnline.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMyPushOnline.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        // 下架设置管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMyPushOffline.setLayoutManager(layoutManager1);
        rvMyPushOffline.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMyPushOffline.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMyPushOffline.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> myPushListMap = new TreeMap<>();
        myPushListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        myPushListMap.put("status", pushType + "");
        myPushListMap.put("pageNum", pageNum + "");
        myPushListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(myPushListMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getMyPushList(myPushListMap, true, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMyPushOnline.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMyPushOnline.setRefreshing(false);
            }
        });

        //下拉刷新
        srlMyPushOffline.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMyPushOffline.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_93:
                    pushType = 1;
                    pageNum = 1;
                    initData();
                    break;
                case REQUEST_CODE_94:
                    pushType = 2;
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
                srlMyPushOnline.setVisibility(View.VISIBLE);
                srlMyPushOffline.setVisibility(View.GONE);
                pushType = 1;
                pageNum = 1;
                initData();
                break;
            case R.id.rb_reward:
                srlMyPushOnline.setVisibility(View.GONE);
                srlMyPushOffline.setVisibility(View.VISIBLE);
                pushType = 2;
                pageNum = 1;
                initData();
                break;
        }
    }

    @Override
    public void resultMyPushList(MyPushListBean data) {
        switch (data.getCode()) {
            case 200:
                switch (pushType) {
                    case 1:
                        if (data.getTotal() > 0) {
                            if (pageNum == 1) {
                                releaseListBean.clear();
                                releaseListBean.addAll(data.getData().getReleaseList());
                                myPushAdapter = new MyPushAdapter(releaseListBean);
                                rvMyPushOnline.setAdapter(myPushAdapter);
                                myPushAdapter.bindToRecyclerView(rvMyPushOnline);
                                myPushAdapter.setEnableLoadMore(true);
                                myPushAdapter.openLoadAnimation();
                                myPushAdapter.disableLoadMoreIfNotFullPage();

                                myPushAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        startActivityForResult(new Intent(MyPushActivity.this, MyPushDetailsActivity.class).putExtra("push_type", pushType).putExtra("releaseId", releaseListBean.get(position).getReleaseId()), REQUEST_CODE_93);
                                    }
                                });

                                myPushAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        startActivityForResult(new Intent(MyPushActivity.this, MyPushDetailsActivity.class).putExtra("push_type", pushType).putExtra("releaseId", releaseListBean.get(position).getReleaseId()), REQUEST_CODE_93);
                                    }
                                });

                                //上拉加载
                                myPushAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                    @Override
                                    public void onLoadMoreRequested() {
                                        rvMyPushOnline.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                initData();
                                            }
                                        }, 1000);
                                    }
                                }, rvMyPushOnline);

                                if (data.getTotal() > 10) {
                                    pageNum += 1;
                                } else {
                                    myPushAdapter.loadMoreEnd();
                                }
                            } else {
                                if ((data.getTotal() - pageNum * 10) > 0) {
                                    pageNum += 1;
                                    myPushAdapter.addData(data.getData().getReleaseList());
                                    myPushAdapter.loadMoreComplete(); //完成本次
                                } else {
                                    myPushAdapter.addData(data.getData().getReleaseList());
                                    myPushAdapter.loadMoreEnd(); //完成所有加载
                                }
                            }
                        } else {
                            releaseListBean.clear();
                            myPushAdapter = new MyPushAdapter(releaseListBean);
                            rvMyPushOnline.setAdapter(myPushAdapter);
                            myPushAdapter.loadMoreEnd(); //完成所有加载
                            myPushAdapter.setEmptyView(R.layout.null_data, rvMyPushOnline);
                        }
                        break;
                    case 2:
                        if (data.getTotal() > 0) {
                            if (pageNum == 1) {
                                releaseListBean.clear();
                                releaseListBean.addAll(data.getData().getReleaseList());
                                myPushAdapter = new MyPushAdapter(releaseListBean);
                                rvMyPushOffline.setAdapter(myPushAdapter);
                                myPushAdapter.bindToRecyclerView(rvMyPushOffline);
                                myPushAdapter.setEnableLoadMore(true);
                                myPushAdapter.openLoadAnimation();
                                myPushAdapter.disableLoadMoreIfNotFullPage();

                                myPushAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        startActivityForResult(new Intent(MyPushActivity.this, MyPushDetailsActivity.class).putExtra("push_type", pushType).putExtra("releaseId", releaseListBean.get(position).getReleaseId()), REQUEST_CODE_94);
                                    }
                                });

                                myPushAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        startActivityForResult(new Intent(MyPushActivity.this, MyPushDetailsActivity.class).putExtra("push_type", pushType).putExtra("releaseId", releaseListBean.get(position).getReleaseId()), REQUEST_CODE_94);
                                    }
                                });

                                //上拉加载
                                myPushAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                    @Override
                                    public void onLoadMoreRequested() {
                                        rvMyPushOffline.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                initData();
                                            }
                                        }, 1000);
                                    }
                                }, rvMyPushOffline);

                                if (data.getTotal() > 10) {
                                    pageNum += 1;
                                } else {
                                    myPushAdapter.loadMoreEnd();
                                }
                            } else {
                                if ((data.getTotal() - pageNum * 10) > 0) {
                                    pageNum += 1;
                                    myPushAdapter.addData(data.getData().getReleaseList());
                                    myPushAdapter.loadMoreComplete(); //完成本次
                                } else {
                                    myPushAdapter.addData(data.getData().getReleaseList());
                                    myPushAdapter.loadMoreEnd(); //完成所有加载
                                }
                            }
                        } else {
                            releaseListBean.clear();
                            myPushAdapter = new MyPushAdapter(releaseListBean);
                            rvMyPushOffline.setAdapter(myPushAdapter);
                            myPushAdapter.loadMoreEnd(); //完成所有加载
                            myPushAdapter.setEmptyView(R.layout.null_data, rvMyPushOffline);
                        }
                        break;
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
