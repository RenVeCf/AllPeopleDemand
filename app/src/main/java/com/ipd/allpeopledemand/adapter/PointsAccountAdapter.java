package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.AccountBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class PointsAccountAdapter extends BaseQuickAdapter<AccountBean.DataBean.DetailedListBean, BaseViewHolder> {
    private SuperTextView stvPointsDetails;

    public PointsAccountAdapter(@Nullable List<AccountBean.DataBean.DetailedListBean> data) {
        super(R.layout.adapter_points_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountBean.DataBean.DetailedListBean item) {
        stvPointsDetails = helper.getView(R.id.stv_points_details);
        stvPointsDetails.setLeftTopString(item.getTitle())
                .setLeftBottomString(item.getCreateTime());
        if (!"".equals(item.getContent()))
            stvPointsDetails.setLeftString(item.getContent());
        switch (item.getAstype()) {
            case "1":
                stvPointsDetails.setRightString("+" + item.getAstype())
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation_select));
                break;
            case "2":
                stvPointsDetails.setRightString("-" + item.getAstype())
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.black));
                break;
        }
    }
}
