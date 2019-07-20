package com.ipd.allpeopledemand.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {
    /**
     * 域名
     */
    String BASE_URL = "http://47.93.126.235:8080/qmxq/";
    String BASE_LOCAL_URL = "http://47.93.126.235:8080/";

    /**
     * 登陆
     */
    String REGISTER = "appUser/login/regists"; //点击注册
    String LOGIN = "appUser/login/login"; //点击登陆
    String FORGET_PWD = "appUser/login/forgetPass"; //点击忘记密码
    String SMS = "appUser/util/getSms"; //获取短信

    /**
     * 首页
     */
    String MSG = "appUser/index/userMessage"; //首页-右上角消息列表
    String CLASS_INICATION = "appUser/index/classList"; //首页分类列表
    String MAIN_LIST = "appUser/index/index"; //首页列表数据
    String MAIN_DETAILS = "appUser/index/releaseDetails"; //首页列表数据-查看详情


    /**
     * 课程
     */
    String CLASS_ROOM_INICATION = "appUser/classroom/classList"; //课堂分类列表
    String CLASS_ROOM_PAGER_LIST = "appUser/classroom/list"; //课程列表
    String CLASS_ROOM_DETAILS = "appUser/classroom/details"; //课堂详情
    String CLASS_ROOM_ALI_PAY = "appUser/classroomPay/alipay"; //课堂支付宝-支付


    /**
     * 发布
     */
    String PUSH = "appUser/myRelease/publish";//点击发布提交

    /**
     * 反馈
     */
    String FEED_BACK = "appUser/feedback/userFeedback"; //反馈-点击提交


    /**
     * 我的
     */
    String UPLOAD_IMG = "appUser/util/upload"; //上传图片
    String INFORMATION = "appUser/User/updateUser"; //我的-个人资料修改
    String ACCOUNT = "appUser/myAccount/accountlist"; //我的账户列表
    String MY_PUSH_LIST = "appUser/myRelease/list"; //我的发布列表
    String MY_PUSH_DETAILS = "appUser/myRelease/details"; //我的发布列表-详情
    String MY_PUSH_COLLECTION = "appUser/myRelease/collection"; //我的发布列表-详情-点击收藏
    String MY_PUSH_DEMAND_TYPE = "appUser/myRelease/lower"; //我的发布列表-详情-下架重新上架
    String MY_PUSH_EDIT = "appUser/myRelease/editRelease"; //我的发布列表-详情-编辑
    String CHECK_IN_LAYOUT = "appUser/sign/signPage"; //签到接口-页面数据
    String CHECK_IN = "appUser/sign/clickSign"; //点击签到
    String SHARE = "appUser/share/pages"; //我的-分享好友-页面数据
    String SHARE_LIST = "appUser/share/shareList"; //我的-分享好友-我邀请好友列表
    String ATTENTION_LIST = "appUser/myFollow/followList"; //我的关注列表
    String ATTENTION_DETAILS = "appUser/myFollow/details"; //我的关注列表-详情
    String ATTENTION_COLLECTION = "appUser/myFollow/collection"; //我的关注列表-详情-点击收藏
    String REPORT_LIST = "appUser/report/reportList"; //我的关注列表-详情-举报列表数据
    String REPORT = "appUser/report/ConfirmReport"; //我的关注列表-详情-提交举报
}
