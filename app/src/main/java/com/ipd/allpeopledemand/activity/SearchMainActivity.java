package com.ipd.allpeopledemand.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.MainPagerAdapter;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.IsMsgBean;
import com.ipd.allpeopledemand.bean.MainADImgBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.MainPagerContract;
import com.ipd.allpeopledemand.presenter.MainPagerPresenter;
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

import static com.ipd.allpeopledemand.common.config.IConstants.CITY;
import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.utils.StringUtils.isEmpty;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：首页搜索
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/21.
 */
public class SearchMainActivity extends BaseActivity<MainPagerContract.View, MainPagerContract.Presenter> implements MainPagerContract.View {

    @BindView(R.id.tv_search_main)
    TopView tvSearchMain;
    @BindView(R.id.et_top_long_search_ed)
    EditText etTopLongSearchEd;
    @BindView(R.id.rv_search_main)
    RecyclerView rvSearchMain;
    @BindView(R.id.srl_search_main)
    SwipeRefreshLayout srlSearchMain;

    private List<MainListBean.DataBean.ReleaseListBean> releaseListBean = new ArrayList<>();
    private MainPagerAdapter mainPagerAdapter;
    private int pageNum = 1;//页数
    private int releaseClassId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_main;
    }

    @Override
    public MainPagerContract.Presenter createPresenter() {
        return new MainPagerPresenter(this);
    }

    @Override
    public MainPagerContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSearchMain);

        releaseClassId = getIntent().getIntExtra("releaseClassId", 0);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSearchMain.setLayoutManager(layoutManager);
        rvSearchMain.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSearchMain.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlSearchMain.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        //下拉刷新
        srlSearchMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlSearchMain.setRefreshing(false);
            }
        });

        etTopLongSearchEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                    TreeMap<String, String> classRoomPagerMap = new TreeMap<>();
                    classRoomPagerMap.put("userId", isEmpty(SPUtil.get(SearchMainActivity.this, USER_ID, "") + "") ? "0" : SPUtil.get(SearchMainActivity.this, USER_ID, "") + "");
                    classRoomPagerMap.put("releaseClassId", releaseClassId + "");
                    classRoomPagerMap.put("orderByColumn", "");
                    classRoomPagerMap.put("isAsc", "");
                    classRoomPagerMap.put("pageNum", pageNum + "");
                    classRoomPagerMap.put("region", SPUtil.get(SearchMainActivity.this, CITY, "") + "");
                    classRoomPagerMap.put("title", etTopLongSearchEd.getText().toString().trim());
                    classRoomPagerMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomPagerMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                    getPresenter().getMainList(classRoomPagerMap, true, false);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void resultMainPager(ClassIficationBean data) {

    }

    @Override
    public void resultMainList(MainListBean data) {
        if (data.getCode() == 200) {
            if (data.getTotal() > 0) {
                for (int i = 0; i < data.getData().getReleaseList().size(); i++) {
                    data.getData().getReleaseList().get(i).setItemType(Integer.valueOf(data.getData().getReleaseList().get(i).getType()));
                }
                if (pageNum == 1) {
                    releaseListBean.clear();
                    releaseListBean.addAll(data.getData().getReleaseList());
                    mainPagerAdapter = new MainPagerAdapter(releaseListBean);
                    rvSearchMain.setAdapter(mainPagerAdapter);
                    mainPagerAdapter.bindToRecyclerView(rvSearchMain);
                    mainPagerAdapter.setEnableLoadMore(true);
                    mainPagerAdapter.openLoadAnimation();
                    mainPagerAdapter.disableLoadMoreIfNotFullPage();

                    mainPagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (isFastClick()) {
                                switch (adapter.getItemViewType(position)) {
                                    case 1:
                                        if ("".equals(SPUtil.get(SearchMainActivity.this, IS_LOGIN, "" + "")))
                                            startActivity(new Intent(SearchMainActivity.this, LoginActivity.class));
                                        else
                                            startActivityForResult(new Intent(SearchMainActivity.this, InformationDetailsActivity.class).putExtra("releaseId", releaseListBean.get(position).getReleaseId()).putExtra("activityType", 1), REQUEST_CODE_97);
                                        break;
                                    case 2:
                                        if ("".equals(SPUtil.get(SearchMainActivity.this, IS_LOGIN, "" + "")))
                                            startActivity(new Intent(SearchMainActivity.this, LoginActivity.class));
                                        else {
                                            switch (releaseListBean.get(position).getDetailType()) {
                                                case "1":
                                                    startActivity(new Intent(SearchMainActivity.this, WebViewActivity.class).putExtra("h5Type", 4).putExtra("h5_url", releaseListBean.get(position).getDetailUrl()));
                                                    break;
                                                case "2":
                                                    startActivityForResult(new Intent(SearchMainActivity.this, InformationDetailsActivity.class).putExtra("releaseId", releaseListBean.get(position).getReleaseId()).putExtra("activityType", 2), REQUEST_CODE_97);
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
                                        LocalBroadcastManager.getInstance(SearchMainActivity.this).sendBroadcast(intent);
                                        break;
//                                    case R.id.cb_collection: //收藏
//                                        if ("".equals(SPUtil.get(SearchMainActivity.this(), IS_LOGIN, "" + "")))
//                                            startActivity(new Intent(SearchMainActivity.this(), LoginActivity.class));
//                                        else {
//                                            CheckBox checkBox = (CheckBox) view;
//                                            TreeMap<String, String> attentionCollectionMap = new TreeMap<>();
//                                            attentionCollectionMap.put("userId", SPUtil.get(SearchMainActivity.this(), USER_ID, "") + "");
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
                            rvSearchMain.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                }
                            }, 1000);
                        }
                    }, rvSearchMain);

                    if (data.getTotal() > 10) {
                        pageNum += 1;
                    } else {
                        mainPagerAdapter.loadMoreEnd();
                    }
                } else {
                    if ((data.getTotal() - pageNum * 10) > 0) {
                        pageNum += 1;
                        mainPagerAdapter.addData(data.getData().getReleaseList());
                        mainPagerAdapter.loadMoreComplete(); //完成本次
                    } else {
                        mainPagerAdapter.addData(data.getData().getReleaseList());
                        mainPagerAdapter.loadMoreEnd(); //完成所有加载
                    }
                }
            } else {
                releaseListBean.clear();
                mainPagerAdapter = new MainPagerAdapter(releaseListBean);
                rvSearchMain.setAdapter(mainPagerAdapter);
                mainPagerAdapter.loadMoreEnd(); //完成所有加载
                mainPagerAdapter.setEmptyView(R.layout.null_data, rvSearchMain);
            }
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public void resultAttentionCollection(AttentionCollectionBean data) {

    }

    @Override
    public void resultMainADImg(MainADImgBean data) {

    }

    @Override
    public void resultIsMsg(IsMsgBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
