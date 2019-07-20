package com.ipd.allpeopledemand.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.adapter.ViewPagerAdapter;
import com.ipd.allpeopledemand.base.BaseFragment;
import com.ipd.allpeopledemand.bean.ClassRoomInicationBean;
import com.ipd.allpeopledemand.common.view.NavitationFollowScrollLayoutText;
import com.ipd.allpeopledemand.common.view.TopView;
import com.ipd.allpeopledemand.contract.ClassRoomInicationContract;
import com.ipd.allpeopledemand.presenter.ClassRoomInicationPresenter;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

/**
 * Description ：全民课堂-顶部滑动导航栏
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/6/21.
 */
public class ClassRoomFragment extends BaseFragment<ClassRoomInicationContract.View, ClassRoomInicationContract.Presenter> implements ClassRoomInicationContract.View {

    @BindView(R.id.tv_class_room)
    TopView tvClassRoom;
    @BindView(R.id.et_top_long_search)
    EditText etTopLongSearch;
    @BindView(R.id.nfsl_fragment_class_room)
    NavitationFollowScrollLayoutText nfslFragmentClassRoom;
    @BindView(R.id.vp_fragment_class_room)
    ViewPager vpFragmentClassRoom;

    private String[] titles;
    private List<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private ClassRoomPagerFragment fm;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_class_room;
    }

    @Override
    public ClassRoomInicationContract.Presenter createPresenter() {
        return new ClassRoomInicationPresenter(mContext);
    }

    @Override
    public ClassRoomInicationContract.View createView() {
        return this;
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(getActivity(), tvClassRoom);
    }

    @Override
    public void initListener() {
        etTopLongSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    // 隐藏软键盘
                    imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    fm.sort("", "", "1", etTopLongSearch.getText().toString().trim());
                    etTopLongSearch.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {
        getPresenter().getClassRoomInication(false, false);
    }

    @Override
    public void resultClassRoomInication(ClassRoomInicationBean data) {
        titles = new String[data.getData().getClassList().size() + 1];
        for (int i = 0; i < data.getData().getClassList().size() + 1; i++) {
            if (i == 0)
                titles[0] = "全部";
            else
                titles[i] = data.getData().getClassList().get(i - 1).getClassName();
        }
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fm = new ClassRoomPagerFragment();
            Bundle args = new Bundle();
            args.putString("roomClassId", i == 0 ? "0" : data.getData().getClassList().get(i - 1).getRoomClassId() + "");
            fm.setArguments(args);
            fragments.add(fm);
        }
        viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        vpFragmentClassRoom.setAdapter(viewPagerAdapter);
        vpFragmentClassRoom.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslFragmentClassRoom.setViewPager(getContext(), titles, vpFragmentClassRoom, R.color.tx_bottom_navigation, R.color.black, 14, 14, 24, true, R.color.black, 0, 0, 0, 80);
        nfslFragmentClassRoom.setBgLine(getContext(), 1, R.color.whitesmoke);
        nfslFragmentClassRoom.setNavLine(getActivity(), 3, R.color.colorAccent);
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
