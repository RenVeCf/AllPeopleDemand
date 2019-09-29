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

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.ClassRoomPagerAdapter;
import com.ipd.allpeopledemand.aliPay.AliPay;
import com.ipd.allpeopledemand.base.BaseActivity;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomBalancePayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.bean.ClassRoomWechatPayBean;
import com.ipd.allpeopledemand.common.view.BottomPayDialog;
import com.ipd.allpeopledemand.common.view.ClassRoomPayPromptDialog;
import com.ipd.allpeopledemand.common.view.NotIntegralDialog;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ClassRoomPagerContract;
import com.ipd.allpeopledemand.presenter.ClassRoomPagerPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;
import static com.ipd.allpeopledemand.common.config.IConstants.WECHAT_BT_TYPE;
import static com.ipd.allpeopledemand.utils.isClickUtil.isFastClick;

/**
 * Description ：课程搜索
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/21.
 */
public class SearchClassRoomActivity extends BaseActivity<ClassRoomPagerContract.View, ClassRoomPagerContract.Presenter> implements ClassRoomPagerContract.View {

    @BindView(R.id.tv_search_class_room)
    TopView tvSearchClassRoom;
    @BindView(R.id.et_top_long_search_ed)
    EditText etTopLongSearchEd;
    @BindView(R.id.rv_search_class_room)
    RecyclerView rvSearchClassRoom;
    @BindView(R.id.srl_search_class_room)
    SwipeRefreshLayout srlSearchClassRoom;

    private List<ClassRoomPagerBean.DataBean.RoomListBean> roomListBean = new ArrayList<>();
    private ClassRoomPagerAdapter classRoomPagerAdapter;
    private ClassRoomDetailsBean.DataBean.RoomDetailsBean roomDetailsBean = new ClassRoomDetailsBean.DataBean.RoomDetailsBean();
    private int classroomIdPosition;//支付时，取classroomId用的position
    private int classRoomBuyType;//1: 判断购买的状态, 2: 购买完成后将数据带进详情
    private int pageNum = 1;//页数
    private int roomClassId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_class_room;
    }

    @Override
    public ClassRoomPagerContract.Presenter createPresenter() {
        return new ClassRoomPagerPresenter(this);
    }

    @Override
    public ClassRoomPagerContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSearchClassRoom);

        roomClassId = getIntent().getIntExtra("roomClassId", 0);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSearchClassRoom.setLayoutManager(layoutManager);
        rvSearchClassRoom.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSearchClassRoom.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlSearchClassRoom.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        //下拉刷新
        srlSearchClassRoom.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlSearchClassRoom.setRefreshing(false);
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
                    classRoomPagerMap.put("roomClassId", roomClassId + "");
                    classRoomPagerMap.put("orderByColumn", "");
                    classRoomPagerMap.put("isAsc", "");
                    classRoomPagerMap.put("pageNum", pageNum + "");
                    classRoomPagerMap.put("title", etTopLongSearchEd.getText().toString().trim());
                    classRoomPagerMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomPagerMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                    getPresenter().getClassRoomPager(classRoomPagerMap, true, false);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void resultClassRoomPager(ClassRoomPagerBean data) {
        if (data.getCode() == 200) {
            if (data.getTotal() > 0) {
                if (pageNum == 1) {
                    roomListBean.clear();
                    roomListBean.addAll(data.getData().getRoomList());
                    classRoomPagerAdapter = new ClassRoomPagerAdapter(roomListBean);
                    rvSearchClassRoom.setAdapter(classRoomPagerAdapter);
                    classRoomPagerAdapter.bindToRecyclerView(rvSearchClassRoom);
                    classRoomPagerAdapter.setEnableLoadMore(true);
                    classRoomPagerAdapter.openLoadAnimation();
                    classRoomPagerAdapter.disableLoadMoreIfNotFullPage();

                    classRoomPagerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (isFastClick()) {
                                if ("".equals(SPUtil.get(SearchClassRoomActivity.this, IS_LOGIN, "" + "")))
                                    startActivity(new Intent(SearchClassRoomActivity.this, LoginActivity.class));
                                else {
                                    classRoomBuyType = 1;
                                    classroomIdPosition = position;
                                    TreeMap<String, String> classRoomDetailsMap = new TreeMap<>();
                                    classRoomDetailsMap.put("userId", SPUtil.get(SearchClassRoomActivity.this, USER_ID, "") + "");
                                    classRoomDetailsMap.put("classroomId", roomListBean.get(position).getClassroomId() + "");
                                    classRoomDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                                    getPresenter().getClassRoomDetails(classRoomDetailsMap, false, false);
                                }
                            }
                        }
                    });

                    //上拉加载
                    classRoomPagerAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                        @Override
                        public void onLoadMoreRequested() {
                            rvSearchClassRoom.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    initData();
                                }
                            }, 1000);
                        }
                    }, rvSearchClassRoom);

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
                rvSearchClassRoom.setAdapter(classRoomPagerAdapter);
                classRoomPagerAdapter.loadMoreEnd(); //完成所有加载
                classRoomPagerAdapter.setEmptyView(R.layout.null_data, rvSearchClassRoom);
            }
        } else
            ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public void resultClassRoomDetails(ClassRoomDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                switch (classRoomBuyType) {
                    case 1: //判断购买的状态
                        roomDetailsBean = data.getData().getRoomDetails();
                        switch (data.getData().getIsPurchase()) {
                            case "1":
                                startActivity(new Intent(SearchClassRoomActivity.this, ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean).putExtra("integral", roomListBean.get(classroomIdPosition).getIntegral()).putExtra("money", roomListBean.get(classroomIdPosition).getMoney()));
                                break;
                            case "2":
                                startActivity(new Intent(SearchClassRoomActivity.this, ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean).putExtra("integral", roomListBean.get(classroomIdPosition).getIntegral()).putExtra("money", roomListBean.get(classroomIdPosition).getMoney()));
                                break;
                            case "3":
                                new NotIntegralDialog(SearchClassRoomActivity.this) {
                                    @Override
                                    public void goPayIntrgral() {
                                        //积分不足跳积分规则
                                        startActivity(new Intent(SearchClassRoomActivity.this, WebViewActivity.class).putExtra("h5Type", 1));
                                    }
                                }.show();
                                break;
                            case "4":
                                new ClassRoomPayPromptDialog(SearchClassRoomActivity.this, roomListBean.get(classroomIdPosition).getMoney(), roomListBean.get(classroomIdPosition).getIntegral()) {
                                    @Override
                                    public void goPay() {
                                        new BottomPayDialog(SearchClassRoomActivity.this, data.getData().getBalance()) {
                                            @Override
                                            public void goPay(int payType) {
                                                if (data.getData().getBalance() >= roomListBean.get(classroomIdPosition).getMoney()) {
                                                    //余额直接支付
                                                    payType(3, roomListBean.get(classroomIdPosition).getClassroomId());
                                                } else {
                                                    switch (payType) {
                                                        case 1://支付宝
                                                            payType(1, roomListBean.get(classroomIdPosition).getClassroomId());
                                                            break;
                                                        case 2://微信
                                                            payType(2, roomListBean.get(classroomIdPosition).getClassroomId());
                                                            break;
                                                        default:
                                                            ToastUtil.showShortToast("余额不足，请选择支付方式！");
                                                            break;
                                                    }
                                                }
                                            }
                                        }.show();
                                    }
                                }.show();
                                break;
                        }
                        break;
                    case 2: //购买完成后将数据带进详情
                        roomDetailsBean = data.getData().getRoomDetails();
                        startActivity(new Intent(SearchClassRoomActivity.this, ClassRoomDetailsActivity.class).putExtra("roomDetailsBean", roomDetailsBean).putExtra("integral", roomListBean.get(classroomIdPosition).getIntegral()).putExtra("money", roomListBean.get(classroomIdPosition).getMoney()));
                        break;
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(SearchClassRoomActivity.this, LoginActivity.class));
                SearchClassRoomActivity.this.finish();
                break;
        }
    }

    //支付
    private void payType(int payType, int classroomId) {
        switch (payType) {
            case 1:
                TreeMap<String, String> aliPayMap = new TreeMap<>();
                aliPayMap.put("userId", SPUtil.get(SearchClassRoomActivity.this, USER_ID, "") + "");
                aliPayMap.put("classroomId", classroomId + "");
                aliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(aliPayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getClassRoomAliPay(aliPayMap, true, false);
                break;
            case 2:
                TreeMap<String, String> weixinPayMap = new TreeMap<>();
                weixinPayMap.put("userId", SPUtil.get(SearchClassRoomActivity.this, USER_ID, "") + "");
                weixinPayMap.put("classroomId", classroomId + "");
                weixinPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(weixinPayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getClassRoomWechatPay(weixinPayMap, true, false);
                break;
            case 3:
                TreeMap<String, String> balancePayMap = new TreeMap<>();
                balancePayMap.put("userId", SPUtil.get(SearchClassRoomActivity.this, USER_ID, "") + "");
                balancePayMap.put("classroomId", classroomId + "");
                balancePayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(balancePayMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getClassRoomBalancePay(balancePayMap, true, false);
                break;
        }
    }

    @Override
    public void resultClassRoomAliPay(ClassRoomAliPayBean data) {
        switch (data.getCode()) {
            case 200:
                new AliPay(SearchClassRoomActivity.this, data.getData().getSign(), 1);
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(SearchClassRoomActivity.this, LoginActivity.class));
                SearchClassRoomActivity.this.finish();
                break;
        }
    }

    @Override
    public void resultClassRoomWechatPay(ClassRoomWechatPayBean data) {
        switch (data.getCode()) {
            case 200:
                SPUtil.put(this, WECHAT_BT_TYPE, 1);

                IWXAPI api = WXAPIFactory.createWXAPI(SearchClassRoomActivity.this, null);
                api.registerApp("wx57313d36c4b4d0d7");
                PayReq req = new PayReq();
                req.appId = data.getData().getSign().getAppid();//你的微信appid
                req.partnerId = data.getData().getSign().getPartnerid();//商户号
                req.prepayId = data.getData().getSign().getPrepayid();//预支付交易会话ID
                req.nonceStr = data.getData().getSign().getNoncestr();//随机字符串
                req.timeStamp = data.getData().getSign().getTimestamp() + "";//时间戳
                req.packageValue = data.getData().getSign().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
                req.sign = data.getData().getSign().getSign();//签名
                //  req.extData         = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(SearchClassRoomActivity.this, LoginActivity.class));
                SearchClassRoomActivity.this.finish();
                break;
        }
    }

    @Override
    public void resultClassRoomBalancePay(ClassRoomBalancePayBean data) {
        ToastUtil.showLongToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                classRoomBuyType = 2;
                TreeMap<String, String> classRoomDetailsMap = new TreeMap<>();
                classRoomDetailsMap.put("userId", SPUtil.get(SearchClassRoomActivity.this, USER_ID, "") + "");
                classRoomDetailsMap.put("classroomId", roomListBean.get(classroomIdPosition).getClassroomId() + "");
                classRoomDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(classRoomDetailsMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
                getPresenter().getClassRoomDetails(classRoomDetailsMap, false, false);
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(SearchClassRoomActivity.this, LoginActivity.class));
                SearchClassRoomActivity.this.finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
