package com.ipd.allpeopledemand.adapter;

import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.ipd.allpeopledemand.utils.SPUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.label.LabelImageView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.IConstants.IS_LOGIN;
import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MainPagerAdapter extends BaseMultiItemQuickAdapter<MainListBean.DataBean.ReleaseListBean, BaseViewHolder> {

    public MainPagerAdapter(@Nullable List<MainListBean.DataBean.ReleaseListBean> data) {
        super(data);
        addItemType(1, R.layout.adapter_user_demand);
        addItemType(2, R.layout.adapter_ad);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainListBean.DataBean.ReleaseListBean item) {
        switch (helper.getItemViewType()) {
            case 1:
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
                if ("".equals(SPUtil.get(ApplicationUtil.getContext(), IS_LOGIN, "") + ""))
                    cbCollection.setEnabled(false);
                else
                    cbCollection.setEnabled(true);
                helper.setText(R.id.tv_content, item.getTitle())
                        .setText(R.id.tv_name, item.getUserCall())
                        .setText(R.id.tv_time, FormatCurrentData.getTimeRange(item.getReleaseTime()))
                        .setText(R.id.tv_label, item.getClassName())
                        .addOnClickListener(R.id.cb_collection);
                break;
            case 2:
                Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getPicPath()).apply(new RequestOptions()).into((LabelImageView) helper.getView(R.id.iv_label));
                break;
        }
    }
}
