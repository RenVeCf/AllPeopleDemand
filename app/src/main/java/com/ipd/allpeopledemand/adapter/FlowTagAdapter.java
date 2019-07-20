package com.ipd.allpeopledemand.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ipd.allpeopledemand.R;
import com.xuexiang.xui.widget.flowlayout.BaseTagAdapter;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/25.
 */
public class FlowTagAdapter extends BaseTagAdapter<String, TextView> {

    public FlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        return (TextView) convertView.findViewById(R.id.tv_tag);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.adapter_item_tag;
    }

    @Override
    protected void convert(TextView textView, String item, int position) {
        textView.setText(item);
    }
}
