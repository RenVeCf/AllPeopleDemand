package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.MsgAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.MsgBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MsgContract;
import com.ipd.allpeopledemand.presenter.MsgPresenter;
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
 * Description ：消息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class MsgActivity extends BaseActivity<MsgContract.View, MsgContract.Presenter> implements MsgContract.View {

    @BindView(R.id.tv_msg)
    TopView tvMsg;
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.srl_msg)
    SwipeRefreshLayout srlMsg;

    private List<MsgBean.DataBean.MessageListBean> msgBean = new ArrayList<>();
    private MsgAdapter msgAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public MsgContract.Presenter createPresenter() {
        return new MsgPresenter(this);
    }

    @Override
    public MsgContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMsg);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMsg.setLayoutManager(layoutManager);
        rvMsg.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMsg.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMsg.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> msgMap = new TreeMap<>();
        msgMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        msgMap.put("pageNum", pageNum + "");
        msgMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(msgMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getMsg(msgMap, false, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMsg.setRefreshing(false);
            }
        });
    }

    @Override
    public void resultMsg(MsgBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        msgBean.clear();
                        msgBean.addAll(data.getData().getMessageList());
                        msgAdapter = new MsgAdapter(msgBean);
                        rvMsg.setAdapter(msgAdapter);
                        msgAdapter.bindToRecyclerView(rvMsg);
                        msgAdapter.setEnableLoadMore(true);
                        msgAdapter.openLoadAnimation();
                        msgAdapter.disableLoadMoreIfNotFullPage();

                        //上拉加载
                        msgAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvMsg.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvMsg);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            msgAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            msgAdapter.addData(data.getData().getMessageList());
                            msgAdapter.loadMoreComplete(); //完成本次
                        } else {
                            msgAdapter.addData(data.getData().getMessageList());
                            msgAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    msgBean.clear();
                    msgAdapter = new MsgAdapter(msgBean);
                    rvMsg.setAdapter(msgAdapter);
                    msgAdapter.loadMoreEnd(); //完成所有加载
                    msgAdapter.setEmptyView(R.layout.null_data, rvMsg);
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
