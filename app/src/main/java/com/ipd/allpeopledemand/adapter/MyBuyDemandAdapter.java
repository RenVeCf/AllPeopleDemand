package com.ipd.allpeopledemand.adapter;

import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MyBuyDemandListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MyBuyDemandAdapter extends BaseQuickAdapter<MyBuyDemandListBean.DataBean.DemandListBean, BaseViewHolder> {

    public MyBuyDemandAdapter(@Nullable List<MyBuyDemandListBean.DataBean.DemandListBean> data) {
        super(R.layout.adapter_my_buy_demand, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBuyDemandListBean.DataBean.DemandListBean item) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into((RadiusImageView) helper.getView(R.id.riv_head));
        CheckBox cbCollection = helper.getView(R.id.cb_collection);
        switch (item.getIsFollow()) {
            case "1":
                cbCollection.setChecked(false);
                break;
            case "2":
                cbCollection.setChecked(true);
                break;
        }
        helper.setText(R.id.tv_content, item.getTitle())
                .setText(R.id.tv_name, item.getUserCall())
                .setText(R.id.tv_time, FormatCurrentData.getTimeRange(item.getReleaseTime()))
                .setText(R.id.tv_label1, "已购买")
                .setText(R.id.tv_label2, item.getClassName())
                .addOnClickListener(R.id.cb_collection);
    }
}
