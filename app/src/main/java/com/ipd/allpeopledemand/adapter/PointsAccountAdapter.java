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
public class PointsAccountAdapter extends BaseQuickAdapter<AccountBean.DataBean.BalanceListBean, BaseViewHolder> {
    private SuperTextView stvPointsDetails;

    public PointsAccountAdapter(@Nullable List<AccountBean.DataBean.BalanceListBean> data) {
        super(R.layout.adapter_points_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountBean.DataBean.BalanceListBean item) {
        stvPointsDetails = helper.getView(R.id.stv_points_details);
        stvPointsDetails
                .setLeftBottomString(item.getCreatetime());
        if (!"".equals(item.getTitle()))
            stvPointsDetails.setLeftString(item.getTitle());
        switch (item.getCategory()) {
            case 1:
                stvPointsDetails.setRightString("+" + item.getBalanceMoney())
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation_select));
                break;
            case 2:
                stvPointsDetails.setRightString("-" + item.getBalanceMoney())
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.black));
                break;
        }
    }
}
