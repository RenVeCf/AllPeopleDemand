package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.MyBuyClassRoomListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MyBuyClassRoomAdapter extends BaseQuickAdapter<MyBuyClassRoomListBean.DataBean.RoomListBean, BaseViewHolder> {

    private String type;
    private int typeRes;

    public MyBuyClassRoomAdapter(@Nullable List<MyBuyClassRoomListBean.DataBean.RoomListBean> data) {
        super(R.layout.adapter_class_room, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyBuyClassRoomListBean.DataBean.RoomListBean item) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getThumbnail()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_class_room)).into((RadiusImageView) helper.getView(R.id.iv_class_room));

        switch (item.getType()) {
            case "1":
                type = "图文";
                typeRes = R.drawable.bg_class_room_text_label;
                helper.setGone(R.id.iv_class_room_play, false);
                break;
            case "2":
                type = "音频";
                typeRes = R.drawable.bg_class_room_audio_label;
                helper.setGone(R.id.iv_class_room_play, true)
                        .setText(R.id.tv_class_room_time, item.getVideoDate());
                break;
            case "3":
                type = "视频";
                typeRes = R.drawable.bg_class_room_video_label;
                helper.setGone(R.id.iv_class_room_play, true)
                        .setText(R.id.tv_class_room_time, item.getVideoDate());
                break;
        }
        helper.setText(R.id.tv_class_room_type, type)
                .setGone(R.id.tv_label, true)
                .setBackgroundRes(R.id.tv_class_room_type, typeRes)
                .setText(R.id.tv_class_room_title, item.getTitle())
                .setText(R.id.tv_class_room_pay_fee, item.getMoney() + "元+" + item.getIntegral() + "积分")
                .setText(R.id.tv_class_room_read_num, item.getPlayNum() + "观看");
    }
}
