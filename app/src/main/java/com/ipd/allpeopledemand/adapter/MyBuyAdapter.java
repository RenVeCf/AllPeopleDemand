package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.TestMultiItemEntityBean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MyBuyAdapter extends BaseMultiItemQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public MyBuyAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(data);
        addItemType(0, R.layout.adapter_my_buy_demand);
        addItemType(1, R.layout.adapter_class_room);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        switch (helper.getItemViewType()) {
            case 0:
                helper.setImageResource(R.id.riv_head, R.mipmap.ic_default_head)
                        .setText(R.id.tv_content, "找人代买一套两室一厅的房子，要求坐北朝南，靠近地铁的学区房")
                        .setText(R.id.tv_name, "月岛tsuki")
                        .setText(R.id.tv_time, "1小时前")
                        .setText(R.id.tv_label1, "已购买")
                        .setText(R.id.tv_label2, "招聘");
                break;
            case 1:
                helper.setImageResource(R.id.iv_class_room, R.mipmap.bg_test_class_room);
                helper.setText(R.id.tv_class_room_type, "视频")
                        .setBackgroundRes(R.id.tv_class_room_type, R.drawable.bg_class_room_video_label)
                        .setText(R.id.tv_class_room_time, "5:06")
                        .setText(R.id.tv_class_room_title, "全球加密通证量化基金行业研究报告")
                        .setText(R.id.tv_class_room_pay_fee, "10元+18积分")
                        .setText(R.id.tv_class_room_read_num, "1.3w观看")
                        .setGone(R.id.iv_class_room_play, true);
                break;
        }
    }
}
