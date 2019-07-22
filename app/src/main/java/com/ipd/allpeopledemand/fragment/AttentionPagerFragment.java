package com.ipd.allpeopledemand.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.InformationDetailsActivity;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.adapter.AttentionPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionListBean;
import com.ipd.allpeopledemand.contract.AttentionListContract;
import com.ipd.allpeopledemand.presenter.AttentionListPresenter;
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
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_98;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：我的关注
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class AttentionPagerFragment extends BaseFragment<AttentionListContract.View, AttentionListContract.Presenter> implements AttentionListContract.View {
    @BindView(R.id.rv_attention)
    RecyclerView rvAttention;
    @BindView(R.id.srl_attention)
    SwipeRefreshLayout srlAttention;

    private List<AttentionListBean.DataBean.FollowListBean> followListBean = new ArrayList<>();
    private AttentionPagerAdapter attentionPagerAdapter;
    private int pageNum = 1;//页数
    private String releaseClassId = "";
    private int removePosition;//点收藏删除的position

    @Override
    public int getLayoutId() {
        return R.layout.fragment_attention_pager;
    }

    @Override
    public AttentionListContract.Presenter createPresenter() {
        return new AttentionListPresenter(mContext);
    }

    @Override
    public AttentionListContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            releaseClassId = args.getString("releaseClassId");
        }

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvAttention.setLayoutManager(layoutManager);
        rvAttention.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvAttention.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlAttention.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlAttention.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlAttention.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> attentionListMap = new TreeMap<>();
        attentionListMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        attentionListMap.put("releaseClassId", releaseClassId);
        attentionListMap.put("pageNum", pageNum + "");
        attentionListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionListMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
        getPresenter().getAttentionList(attentionListMap, false, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_98:
                    pageNum = 1;
                    initData();
                    break;
            }
        }
    }

    @Override
    public void resultAttentionList(AttentionListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        followListBean.clear();
                        followListBean.addAll(data.getData().getFollowList());
                        attentionPagerAdapter = new AttentionPagerAdapter(followListBean);
                        rvAttention.setAdapter(attentionPagerAdapter);
                        attentionPagerAdapter.bindToRecyclerView(rvAttention);
                        attentionPagerAdapter.setEnableLoadMore(true);
                        attentionPagerAdapter.openLoadAnimation();
                        attentionPagerAdapter.disableLoadMoreIfNotFullPage();

                        attentionPagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                startActivityForResult(new Intent(getContext(), InformationDetailsActivity.class).putExtra("releaseId", followListBean.get(position).getReleaseId()).putExtra("activityType", 3), REQUEST_CODE_98);
                            }
                        });

                        attentionPagerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.cb_collection:
                                        removePosition = position;
                                        CheckBox checkBox = (CheckBox) view;
                                        TreeMap<String, String> attentionCollectionMap = new TreeMap<>();
                                        attentionCollectionMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                        attentionCollectionMap.put("releaseId", followListBean.get(position).getReleaseId() + "");
                                        attentionCollectionMap.put("isFollow", checkBox.isChecked() ? "2" : "1");
                                        attentionCollectionMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(attentionCollectionMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                        getPresenter().getAttentionCollection(attentionCollectionMap, false, false);
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        attentionPagerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvAttention.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvAttention);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            attentionPagerAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            attentionPagerAdapter.addData(data.getData().getFollowList());
                            attentionPagerAdapter.loadMoreComplete(); //完成本次
                        } else {
                            attentionPagerAdapter.addData(data.getData().getFollowList());
                            attentionPagerAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    followListBean.clear();
                    attentionPagerAdapter = new AttentionPagerAdapter(followListBean);
                    rvAttention.setAdapter(attentionPagerAdapter);
                    attentionPagerAdapter.loadMoreEnd(); //完成所有加载
                    attentionPagerAdapter.setEmptyView(R.layout.null_data, rvAttention);
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
    public void resultAttentionCollection(AttentionCollectionBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                followListBean.remove(removePosition);
                attentionPagerAdapter.notifyDataSetChanged();
                attentionPagerAdapter.setEmptyView(R.layout.null_data, rvAttention);
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
