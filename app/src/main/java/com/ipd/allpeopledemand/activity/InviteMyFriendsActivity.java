package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.InviteMyFriendsAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ShareListBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ShareListContract;
import com.ipd.allpeopledemand.presenter.ShareListPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：我邀请的好友
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class InviteMyFriendsActivity extends BaseActivity<ShareListContract.View, ShareListContract.Presenter> implements ShareListContract.View {

    @BindView(R.id.tv_invite_my_friends)
    TopView tvInviteMyFriends;
    @BindView(R.id.tv_top_invite)
    TextView tvTopInvite;
    @BindView(R.id.rv_invite_my_friends)
    RecyclerView rvInviteMyFriends;
    @BindView(R.id.srl_invite_my_friends)
    SwipeRefreshLayout srlInviteMyFriends;

    private List<ShareListBean.DataBean.UserBean> userBean = new ArrayList<>();
    private InviteMyFriendsAdapter inviteMyFriendsAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_my_friends;
    }

    @Override
    public ShareListContract.Presenter createPresenter() {
        return new ShareListPresenter(this);
    }

    @Override
    public ShareListContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvInviteMyFriends);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvInviteMyFriends.setLayoutManager(layoutManager);
        rvInviteMyFriends.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvInviteMyFriends.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlInviteMyFriends.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> shareListMap = new TreeMap<>();
        shareListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        shareListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(shareListMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getShareList(shareListMap, true, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlInviteMyFriends.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlInviteMyFriends.setRefreshing(false);
            }
        });
    }

    @Override
    public void resultShareList(ShareListBean data) {
        switch (data.getCode()) {
            case 200:
                tvTopInvite.setText(data.getTotal() + "人");

                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        userBean.clear();
                        userBean.addAll(data.getData().getUser());
                        inviteMyFriendsAdapter = new InviteMyFriendsAdapter(userBean);
                        rvInviteMyFriends.setAdapter(inviteMyFriendsAdapter);
                        inviteMyFriendsAdapter.bindToRecyclerView(rvInviteMyFriends);
                        inviteMyFriendsAdapter.setEnableLoadMore(true);
                        inviteMyFriendsAdapter.openLoadAnimation();
                        inviteMyFriendsAdapter.disableLoadMoreIfNotFullPage();

                        //上拉加载
                        inviteMyFriendsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvInviteMyFriends.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvInviteMyFriends);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            inviteMyFriendsAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            inviteMyFriendsAdapter.addData(data.getData().getUser());
                            inviteMyFriendsAdapter.loadMoreComplete(); //完成本次
                        } else {
                            inviteMyFriendsAdapter.addData(data.getData().getUser());
                            inviteMyFriendsAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    inviteMyFriendsAdapter = new InviteMyFriendsAdapter(userBean);
                    rvInviteMyFriends.setAdapter(inviteMyFriendsAdapter);
                    inviteMyFriendsAdapter.loadMoreEnd(); //完成所有加载
                    inviteMyFriendsAdapter.setEmptyView(R.layout.null_data, rvInviteMyFriends);
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
