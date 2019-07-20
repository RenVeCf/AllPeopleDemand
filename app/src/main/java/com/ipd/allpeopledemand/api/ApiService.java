package com.ipd.allpeopledemand.api;

import com.ipd.allpeopledemand.bean.AccountBean;
import com.ipd.allpeopledemand.bean.AttentionCollectionBean;
import com.ipd.allpeopledemand.bean.AttentionDetailsBean;
import com.ipd.allpeopledemand.bean.AttentionListBean;
import com.ipd.allpeopledemand.bean.CheckInBean;
import com.ipd.allpeopledemand.bean.CheckInLayoutBean;
import com.ipd.allpeopledemand.bean.ClassIficationBean;
import com.ipd.allpeopledemand.bean.ClassRoomAliPayBean;
import com.ipd.allpeopledemand.bean.ClassRoomDetailsBean;
import com.ipd.allpeopledemand.bean.ClassRoomInicationBean;
import com.ipd.allpeopledemand.bean.ClassRoomPagerBean;
import com.ipd.allpeopledemand.bean.FeedBackBean;
import com.ipd.allpeopledemand.bean.ForgetPwdBean;
import com.ipd.allpeopledemand.bean.InformationBean;
import com.ipd.allpeopledemand.bean.MainDetailsBean;
import com.ipd.allpeopledemand.bean.MainListBean;
import com.ipd.allpeopledemand.bean.MsgBean;
import com.ipd.allpeopledemand.bean.MyPushCollectionBean;
import com.ipd.allpeopledemand.bean.MyPushDemandTypeBean;
import com.ipd.allpeopledemand.bean.MyPushDetailsBean;
import com.ipd.allpeopledemand.bean.MyPushEditBean;
import com.ipd.allpeopledemand.bean.MyPushListBean;
import com.ipd.allpeopledemand.bean.PushBean;
import com.ipd.allpeopledemand.bean.RegisterBean;
import com.ipd.allpeopledemand.bean.ReportBean;
import com.ipd.allpeopledemand.bean.ReportListBean;
import com.ipd.allpeopledemand.bean.ShareBean;
import com.ipd.allpeopledemand.bean.ShareListBean;
import com.ipd.allpeopledemand.bean.SmsBean;
import com.ipd.allpeopledemand.bean.UploadImgBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

import static com.ipd.allpeopledemand.common.config.UrlConfig.ACCOUNT;
import static com.ipd.allpeopledemand.common.config.UrlConfig.ATTENTION_COLLECTION;
import static com.ipd.allpeopledemand.common.config.UrlConfig.ATTENTION_DETAILS;
import static com.ipd.allpeopledemand.common.config.UrlConfig.ATTENTION_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CHECK_IN;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CHECK_IN_LAYOUT;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CLASS_INICATION;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CLASS_ROOM_ALI_PAY;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CLASS_ROOM_DETAILS;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CLASS_ROOM_INICATION;
import static com.ipd.allpeopledemand.common.config.UrlConfig.CLASS_ROOM_PAGER_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.FEED_BACK;
import static com.ipd.allpeopledemand.common.config.UrlConfig.FORGET_PWD;
import static com.ipd.allpeopledemand.common.config.UrlConfig.INFORMATION;
import static com.ipd.allpeopledemand.common.config.UrlConfig.LOGIN;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MAIN_DETAILS;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MAIN_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MSG;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MY_PUSH_COLLECTION;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MY_PUSH_DEMAND_TYPE;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MY_PUSH_DETAILS;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MY_PUSH_EDIT;
import static com.ipd.allpeopledemand.common.config.UrlConfig.MY_PUSH_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.PUSH;
import static com.ipd.allpeopledemand.common.config.UrlConfig.REGISTER;
import static com.ipd.allpeopledemand.common.config.UrlConfig.REPORT;
import static com.ipd.allpeopledemand.common.config.UrlConfig.REPORT_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.SHARE;
import static com.ipd.allpeopledemand.common.config.UrlConfig.SHARE_LIST;
import static com.ipd.allpeopledemand.common.config.UrlConfig.SMS;
import static com.ipd.allpeopledemand.common.config.UrlConfig.UPLOAD_IMG;

/**
 * Description ：请求配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */
public interface ApiService {

    //课堂分类列表
    @POST(CLASS_ROOM_INICATION)
    Observable<ClassRoomInicationBean> getClassRoomInication();

    //课程列表
    @FormUrlEncoded
    @POST(CLASS_ROOM_PAGER_LIST)
    Observable<ClassRoomPagerBean> getClassRoomPager(@FieldMap Map<String, String> map);

    //课堂详情
    @FormUrlEncoded
    @POST(CLASS_ROOM_DETAILS)
    Observable<ClassRoomDetailsBean> getClassRoomDetails(@FieldMap Map<String, String> map);

    //课堂支付宝-支付
    @FormUrlEncoded
    @POST(CLASS_ROOM_ALI_PAY)
    Observable<ClassRoomAliPayBean> getClassRoomAliPay(@FieldMap Map<String, String> map);

    //点击注册
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegisterBean> getRegister(@FieldMap Map<String, String> map);

    //获取短信
    @FormUrlEncoded
    @POST(SMS)
    Observable<SmsBean> getSms(@FieldMap Map<String, String> map);

    //点击登陆
    @FormUrlEncoded
    @POST(LOGIN)
    Observable<RegisterBean> getLogin(@FieldMap Map<String, String> map);

    //点击忘记密码
    @FormUrlEncoded
    @POST(FORGET_PWD)
    Observable<ForgetPwdBean> getForgetPwd(@FieldMap Map<String, String> map);

    //上传图片
    @Multipart
    @POST(UPLOAD_IMG)
    Observable<UploadImgBean> getUploadImg(@PartMap Map<String, RequestBody> map);

    //我的-个人资料修改
    @FormUrlEncoded
    @POST(INFORMATION)
    Observable<InformationBean> getInformation(@FieldMap Map<String, String> map);

    //我的账户列表
    @FormUrlEncoded
    @POST(ACCOUNT)
    Observable<AccountBean> getAccount(@FieldMap Map<String, String> map);

    //反馈-点击提交
    @FormUrlEncoded
    @POST(FEED_BACK)
    Observable<FeedBackBean> getFeedBack(@FieldMap Map<String, String> map);

    //首页-右上角消息列表
    @FormUrlEncoded
    @POST(MSG)
    Observable<MsgBean> getMsg(@FieldMap Map<String, String> map);

    //首页分类列表
    @POST(CLASS_INICATION)
    Observable<ClassIficationBean> getClassIfication();

    //点击发布提交
    @FormUrlEncoded
    @POST(PUSH)
    Observable<PushBean> getPush(@FieldMap Map<String, String> map);

    //我的发布列表
    @FormUrlEncoded
    @POST(MY_PUSH_LIST)
    Observable<MyPushListBean> getMyPushList(@FieldMap Map<String, String> map);

    //我的发布列表-详情
    @FormUrlEncoded
    @POST(MY_PUSH_DETAILS)
    Observable<MyPushDetailsBean> getMyPushDetails(@FieldMap Map<String, String> map);

    //我的发布列表-详情-点击收藏
    @FormUrlEncoded
    @POST(MY_PUSH_COLLECTION)
    Observable<MyPushCollectionBean> getMyPushCollection(@FieldMap Map<String, String> map);

    //我的发布列表-详情-下架重新上架
    @FormUrlEncoded
    @POST(MY_PUSH_DEMAND_TYPE)
    Observable<MyPushDemandTypeBean> getMyPushDemandType(@FieldMap Map<String, String> map);

    //我的发布列表-详情-编辑
    @FormUrlEncoded
    @POST(MY_PUSH_EDIT)
    Observable<MyPushEditBean> getMyPushEdit(@FieldMap Map<String, String> map);

    //签到接口-页面数据
    @FormUrlEncoded
    @POST(CHECK_IN_LAYOUT)
    Observable<CheckInLayoutBean> getCheckInLayout(@FieldMap Map<String, String> map);

    //点击签到
    @FormUrlEncoded
    @POST(CHECK_IN)
    Observable<CheckInBean> getCheckIn(@FieldMap Map<String, String> map);

    //我的-分享好友-页面数据
    @FormUrlEncoded
    @POST(SHARE)
    Observable<ShareBean> getShare(@FieldMap Map<String, String> map);

    //我的-分享好友-我邀请好友列表
    @FormUrlEncoded
    @POST(SHARE_LIST)
    Observable<ShareListBean> getShareList(@FieldMap Map<String, String> map);

    //我的关注列表
    @FormUrlEncoded
    @POST(ATTENTION_LIST)
    Observable<AttentionListBean> getAttentionList(@FieldMap Map<String, String> map);

    //我的关注列表-详情
    @FormUrlEncoded
    @POST(ATTENTION_DETAILS)
    Observable<AttentionDetailsBean> getAttentionDetails(@FieldMap Map<String, String> map);

    //我的关注列表-详情-点击收藏
    @FormUrlEncoded
    @POST(ATTENTION_COLLECTION)
    Observable<AttentionCollectionBean> getAttentionCollection(@FieldMap Map<String, String> map);

    //我的关注列表-详情-举报列表数据
    @FormUrlEncoded
    @POST(REPORT_LIST)
    Observable<ReportListBean> getReportList(@FieldMap Map<String, String> map);

    //我的关注列表-详情-提交举报
    @FormUrlEncoded
    @POST(REPORT)
    Observable<ReportBean> getReport(@FieldMap Map<String, String> map);

    //首页列表数据
    @FormUrlEncoded
    @POST(MAIN_LIST)
    Observable<MainListBean> getMainList(@FieldMap Map<String, String> map);

    //我的关注列表-详情-提交举报
    @FormUrlEncoded
    @POST(MAIN_DETAILS)
    Observable<MainDetailsBean> getMainDetails(@FieldMap Map<String, String> map);
}
