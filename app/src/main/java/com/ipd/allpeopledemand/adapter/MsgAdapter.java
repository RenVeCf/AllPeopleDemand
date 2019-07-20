package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MsgBean;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MsgAdapter extends BaseQuickAdapter<MsgBean.DataBean.MessageListBean, BaseViewHolder> {
    private ExpandableTextView tvContent;

    public MsgAdapter(@Nullable List<MsgBean.DataBean.MessageListBean> data) {
        super(R.layout.adapter_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgBean.DataBean.MessageListBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_date, item.getCreateTime());
        tvContent = helper.getView(R.id.tv_content);
        tvContent.setText(item.getContent());
    }
}
