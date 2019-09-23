package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.PointsAccountAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.AccountBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.AccountContract;
import com.ipd.allpeopledemand.presenter.AccountPresenter;
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

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：我的账户
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class AccountActivity extends BaseActivity<AccountContract.View, AccountContract.Presenter> implements AccountContract.View {

    @BindView(R.id.tv_account)
    TopView tvAccount;
    @BindView(R.id.rb_point)
    RadioButton rbPoint;
    @BindView(R.id.rb_reward)
    RadioButton rbReward;
    @BindView(R.id.tv_total_points)
    TextView tvTotalPoints;
    @BindView(R.id.rv_points_details)
    RecyclerView rvPointsDetails;
    @BindView(R.id.srl_points_details)
    SwipeRefreshLayout srlPointsDetails;
    @BindView(R.id.ll_points_account)
    LinearLayout llPointsAccount;
    @BindView(R.id.tv_total_balance)
    TextView tvTotalBalance;
    @BindView(R.id.rv_balance_details)
    RecyclerView rvBalanceDetails;
    @BindView(R.id.srl_balance_details)
    SwipeRefreshLayout srlBalanceDetails;
    @BindView(R.id.ll_reward_account)
    LinearLayout llRewardAccount;

    private List<AccountBean.DataBean.DetailedListBean> detailedListBean = new ArrayList<>();
    private PointsAccountAdapter pointsAccountAdapter;
    private int pageNum = 1;//页数
    private int type = 2;//1.积分账户 2.奖励账户

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public AccountContract.Presenter createPresenter() {
        return new AccountPresenter(this);
    }

    @Override
    public AccountContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAccount);

        // 积分账户设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvPointsDetails.setLayoutManager(layoutManager);
        rvPointsDetails.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlPointsDetails.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        // 奖励账户设置管理器
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvBalanceDetails.setLayoutManager(layoutManager1);
        rvBalanceDetails.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlBalanceDetails.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> accountMap = new TreeMap<>();
        accountMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        accountMap.put("type", type + "");
        accountMap.put("pageNum", pageNum + "");
        accountMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(accountMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getAccount(accountMap, true, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlPointsDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlPointsDetails.setRefreshing(false);
            }
        });

        //下拉刷新
        srlBalanceDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlBalanceDetails.setRefreshing(false);
            }
        });
    }

    @OnClick({R.id.rb_point, R.id.rb_reward, R.id.bt_withdraw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_point:
                llPointsAccount.setVisibility(View.VISIBLE);
                llRewardAccount.setVisibility(View.GONE);
                type = 1;
                pageNum = 1;
                initData();
                break;
            case R.id.rb_reward:
                llPointsAccount.setVisibility(View.GONE);
                llRewardAccount.setVisibility(View.VISIBLE);
                type = 2;
                pageNum = 1;
                initData();
                break;
            case R.id.bt_withdraw:
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
        }
    }

    @Override
    public void resultAccount(AccountBean data) {
        switch (data.getCode()) {
            case 200:
                switch (type) {
                    case 1:
                        tvTotalPoints.setText(data.getData().getIntegral() + "");

                        if (data.getTotal() > 0) {
                            if (pageNum == 1) {
                                detailedListBean.clear();
                                detailedListBean.addAll(data.getData().getDetailedList());
                                pointsAccountAdapter = new PointsAccountAdapter(detailedListBean);
                                rvPointsDetails.setAdapter(pointsAccountAdapter);
                                pointsAccountAdapter.bindToRecyclerView(rvPointsDetails);
                                pointsAccountAdapter.setEnableLoadMore(true);
                                pointsAccountAdapter.openLoadAnimation();
                                pointsAccountAdapter.disableLoadMoreIfNotFullPage();

                                //上拉加载
                                pointsAccountAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                    @Override
                                    public void onLoadMoreRequested() {
                                        rvPointsDetails.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                initData();
                                            }
                                        }, 1000);
                                    }
                                }, rvPointsDetails);

                                if (data.getTotal() > 10) {
                                    pageNum += 1;
                                } else {
                                    pointsAccountAdapter.loadMoreEnd();
                                }
                            } else {
                                if ((data.getTotal() - pageNum * 10) > 0) {
                                    pageNum += 1;
                                    pointsAccountAdapter.addData(data.getData().getDetailedList());
                                    pointsAccountAdapter.loadMoreComplete(); //完成本次
                                } else {
                                    pointsAccountAdapter.addData(data.getData().getDetailedList());
                                    pointsAccountAdapter.loadMoreEnd(); //完成所有加载
                                }
                            }
                        } else {
                            detailedListBean.clear();
                            pointsAccountAdapter = new PointsAccountAdapter(detailedListBean);
                            rvPointsDetails.setAdapter(pointsAccountAdapter);
                            pointsAccountAdapter.loadMoreEnd(); //完成所有加载
                            pointsAccountAdapter.setEmptyView(R.layout.null_data, rvPointsDetails);
                        }
                        break;
                    case 2:
                        tvTotalBalance.setText("¥ " + data.getData().getBalance() + "");

                        if (data.getTotal() > 0) {
                            if (pageNum == 1) {
                                detailedListBean.clear();
                                detailedListBean.addAll(data.getData().getDetailedList());
                                pointsAccountAdapter = new PointsAccountAdapter(detailedListBean);
                                rvBalanceDetails.setAdapter(pointsAccountAdapter);
                                pointsAccountAdapter.bindToRecyclerView(rvBalanceDetails);
                                pointsAccountAdapter.setEnableLoadMore(true);
                                pointsAccountAdapter.openLoadAnimation();
                                pointsAccountAdapter.disableLoadMoreIfNotFullPage();

                                //上拉加载
                                pointsAccountAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                                    @Override
                                    public void onLoadMoreRequested() {
                                        rvBalanceDetails.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                initData();
                                            }
                                        }, 1000);
                                    }
                                }, rvBalanceDetails);

                                if (data.getTotal() > 10) {
                                    pageNum += 1;
                                } else {
                                    pointsAccountAdapter.loadMoreEnd();
                                }
                            } else {
                                if ((data.getTotal() - pageNum * 10) > 0) {
                                    pageNum += 1;
                                    pointsAccountAdapter.addData(data.getData().getDetailedList());
                                    pointsAccountAdapter.loadMoreComplete(); //完成本次
                                } else {
                                    pointsAccountAdapter.addData(data.getData().getDetailedList());
                                    pointsAccountAdapter.loadMoreEnd(); //完成所有加载
                                }
                            }
                        } else {
                            detailedListBean.clear();
                            pointsAccountAdapter = new PointsAccountAdapter(detailedListBean);
                            rvBalanceDetails.setAdapter(pointsAccountAdapter);
                            pointsAccountAdapter.loadMoreEnd(); //完成所有加载
                            pointsAccountAdapter.setEmptyView(R.layout.null_data, rvBalanceDetails);
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
