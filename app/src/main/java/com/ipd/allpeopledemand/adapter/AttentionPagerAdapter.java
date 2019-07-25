package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.AttentionListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.label.LabelImageView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class AttentionPagerAdapter extends BaseMultiItemQuickAdapter<AttentionListBean.DataBean.FollowListBean, BaseViewHolder> {

    public AttentionPagerAdapter(@Nullable List<AttentionListBean.DataBean.FollowListBean> data) {
        super(data);
        addItemType(1, R.layout.adapter_attention);
        addItemType(2, R.layout.adapter_ad);
    }

    @Override
    protected void convert(BaseViewHolder helper, AttentionListBean.DataBean.FollowListBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into((RadiusImageView) helper.getView(R.id.riv_head));
                helper.setText(R.id.tv_content, item.getTitle())
                        .setText(R.id.tv_name, item.getUserCall())
                        .setText(R.id.tv_time, FormatCurrentData.getTimeRange(item.getReleaseTime()))
                        .setText(R.id.tv_label1, "1".equals(item.getNotPurchase()) ? "未购买" : "已购买")
                        .setText(R.id.tv_label2, item.getClassName())
                        .addOnClickListener(R.id.cb_collection);
                break;
            case 2:
                Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getPicPath()).apply(new RequestOptions()).into((LabelImageView) helper.getView(R.id.iv_label));
                break;
        }
    }
}
