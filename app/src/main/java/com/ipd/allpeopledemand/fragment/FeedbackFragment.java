package com.ipd.allpeopledemand.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.activity.LoginActivity;
import com.ipd.allpeopledemand.activity.MainActivity;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.FeedBackBean;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.FeedBackContract;
import com.ipd.allpeopledemand.presenter.FeedBackPresenter;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.MD5Utils;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.ipd.allpeopledemand.utils.StringUtils;
import com.ipd.allpeopledemand.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.button.RippleView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.allpeopledemand.common.config.IConstants.USER_ID;

/**
 * Description ：反馈
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class FeedbackFragment extends BaseFragment<FeedBackContract.View, FeedBackContract.Presenter> implements FeedBackContract.View {

    @BindView(R.id.tv_feedback)
    TopView tvFeedback;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.rv_feedback)
    RippleView rvFeedback;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feedback;
    }

    @Override
    public FeedBackContract.Presenter createPresenter() {
        return new FeedBackPresenter(mContext);
    }

    @Override
    public FeedBackContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        ImmersionBar.setTitleBar(this, tvFeedback);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.rv_feedback)
    public void onViewClicked() {
        if (!"".equals(etTitle.getText().toString().trim()) && !"".equals(etContent.getText().toString().trim())) {
            TreeMap<String, String> feedBackMap = new TreeMap<>();
            feedBackMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
            feedBackMap.put("title", etTitle.getText().toString().trim());
            feedBackMap.put("content", etContent.getText().toString().trim());
            feedBackMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(feedBackMap.toString().replaceAll(" ", "") + "F9A75BB045D75998E1509B75ED3A5225")));
            getPresenter().getFeedBack(feedBackMap, true, false);
        }
    }

    @Override
    public void resultFeedBack(FeedBackBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                etTitle.setText("");
                etContent.setText("");
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
