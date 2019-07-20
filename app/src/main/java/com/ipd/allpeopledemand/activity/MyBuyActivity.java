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
import com.ipd.allpeopledemand.adapter.MyBuyAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.base.BasePresenter;
import com.ipd.allpeopledemand.base.BaseView;
import com.ipd.allpeopledemand.bean.TestMultiItemEntityBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.L;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_99;

/**
 * Description ：我的购买
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/26.
 */
public class MyBuyActivity extends BaseActivity {

    @BindView(R.id.tv_my_buy)
    TopView tvMyBuy;
    @BindView(R.id.rb_point)
    RadioButton rbPoint;
    @BindView(R.id.rb_reward)
    RadioButton rbReward;
    @BindView(R.id.rv_my_buy)
    RecyclerView rvMyBuy;
    @BindView(R.id.srl_my_buy)
    SwipeRefreshLayout srlMyBuy;

    private List<TestMultiItemEntityBean> list;
    private MyBuyAdapter myBuyAdapter;
    private int buyType = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_buy;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMyBuy);

        rbPoint.setText("需求咨询");
        rbReward.setText("课堂教程");

        // 积分账户设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMyBuy.setLayoutManager(layoutManager);
        rvMyBuy.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMyBuy.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMyBuy.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            testData.setItemType(0);
            list.add(testData);
        }
        myBuyAdapter = new MyBuyAdapter(list);
        rvMyBuy.setAdapter(myBuyAdapter);
        myBuyAdapter.bindToRecyclerView(rvMyBuy);
        myBuyAdapter.setEnableLoadMore(true);
        myBuyAdapter.openLoadAnimation();
        myBuyAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void initData() {
        switch (buyType) {
            case 1:
                list.clear();
                for (int i = 0; i < 10; i++) {
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    testData.setItemType(0);
                    list.add(testData);
                }
                myBuyAdapter.setNewData(list);
                break;
            case 2:
                list.clear();
                for (int i = 0; i < 10; i++) {
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    testData.setItemType(1);
                    list.add(testData);
                }
                myBuyAdapter.setNewData(list);
                break;
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMyBuy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlMyBuy.setRefreshing(false);
            }
        });

        //上拉加载
        myBuyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
            }
        }, rvMyBuy);

        myBuyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (adapter.getItemViewType(position)) {
                    case 0:
                        startActivityForResult(new Intent(MyBuyActivity.this, InformationDetailsActivity.class), REQUEST_CODE_99);
                        break;
                    case 1:
                        startActivity(new Intent(MyBuyActivity.this, ClassRoomDetailsActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_99:
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
                initData();
                break;
            case R.id.rb_reward:
                buyType = 2;
                initData();
                break;
        }
    }
}
