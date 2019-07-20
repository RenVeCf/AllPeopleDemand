package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MyPushListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.ipd.allpeopledemand.utils.FormatCurrentData;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MyPushAdapter extends BaseQuickAdapter<MyPushListBean.DataBean.ReleaseListBean, BaseViewHolder> {
    private SuperTextView stvBottomContent;

    public MyPushAdapter(@Nullable List<MyPushListBean.DataBean.ReleaseListBean> data) {
        super(R.layout.adapter_my_push, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPushListBean.DataBean.ReleaseListBean item) {
        stvBottomContent = helper.getView(R.id.stv_bottom_content);
        stvBottomContent.setLeftTopString(item.getUserCall())
                .setLeftBottomString(FormatCurrentData.getTimeRange(item.getReleaseTime()))
                .setRightString(item.getBrowseNum() + "");
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(stvBottomContent.getLeftIconIV());
        helper.setText(R.id.tv_content, item.getTitle());
        helper.addOnClickListener(R.id.stv_bottom_content);
    }
}
