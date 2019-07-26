package com.ipd.allpeopledemand.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MsgBean;
import com.xuexiang.xui.widget.textview.ExpandableTextView;

import java.util.List;

import static android.text.TextUtils.TruncateAt.END;

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
        tvContent.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                if (!isExpanded) {
                    textView.setLines(1);
                    textView.setEllipsize(END);
                }
            }
        });
    }
}
