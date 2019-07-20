package com.ipd.allpeopledemand.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.allpeopledemand.R;
import com.ipd.allpeopledemand.bean.ShareListBean;
import com.ipd.allpeopledemand.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

import static com.ipd.allpeopledemand.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class InviteMyFriendsAdapter extends BaseQuickAdapter<ShareListBean.DataBean.UserBean, BaseViewHolder> {
    private SuperTextView stvContent;

    public InviteMyFriendsAdapter(@Nullable List<ShareListBean.DataBean.UserBean> data) {
        super(R.layout.adapter_invite_my_friends, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShareListBean.DataBean.UserBean item) {
        stvContent = helper.getView(R.id.stv_content);
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + item.getAvatar()).apply(new RequestOptions().placeholder(R.mipmap.ic_default_head)).into(stvContent.getLeftIconIV());
        stvContent.setLeftTopString(item.getUserCall());
        stvContent.setCenterString(item.getTelPhone());
    }
}
