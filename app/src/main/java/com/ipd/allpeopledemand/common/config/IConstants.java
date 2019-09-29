package com.ipd.allpeopledemand.common.config;

/**
 * Description ：公共配置类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface IConstants {
    /**
     * 包名
     */
    String PACKAGE_NAME = "com.ipd.allpeopledemand";

    /**
     * SharedPreferences
     * 共享参数
     */
    String FIRST_APP = "is_first"; //第一次进应用
    String IS_LOGIN = "is_login"; //已经登录
    String TOKEN = "is_token"; //token
    String USER_ID = "user_id"; //用户标识
    String NAME = "name"; //用户真实姓名
    String PHONE = "phone"; //用户手机号码
    String MEMBER = "phone"; //是否是会员 0：不是，1：是
    String ALL_PEOPLE = "all_people"; //全民号码
    String WECHAT_CODE = "wechat_code"; //微信号码
    String AVATAR = "avatar"; //头像
    String SEX = "sex"; //性别
    String AGE = "age"; //年龄
    String MARITAL_STATUS = "marital_status"; //婚姻状况
    String CITY = "city"; //城市
    String WECHAT_BT_TYPE = "wechat_bt_type"; //1:返回详情查看联系方式, 2:发布成功, 3:充值VIP成功
    String HOW_PAGE = "how_page"; //未登录时点击了首页的哪个Fragment，登录后就跳到哪个Fragment


    /**
     * requestCode
     * 请求码
     */
    int REQUEST_CODE_90 = 90;//支付结果
    int REQUEST_CODE_91 = 91;//更改昵称
    int REQUEST_CODE_92 = 92;//我的 to 个人资料
    int REQUEST_CODE_93 = 93;//我的发布-上架
    int REQUEST_CODE_94 = 94;//我的发布-下架
    int REQUEST_CODE_95 = 95;//我的发布详情
    int REQUEST_CODE_96 = 96;//我的 to 签到
    int REQUEST_CODE_97 = 97;//首页 to 资讯详情
    int REQUEST_CODE_98 = 98;//我的关注 to 资讯详情
    int REQUEST_CODE_99 = 99;//我的购买 to 资讯详情
    int REQUEST_CODE_100 = 100;//更改微信号
    int REQUEST_CODE_101 = 101;//开通会员回跳
    int REQUEST_CODE_102 = 102;
    int REQUEST_CODE_103 = 103;
    int REQUEST_CODE_104 = 104;
    int REQUEST_CODE_105 = 105;

    /**
     * resultCode
     * 返回码
     */
    int RESULT_CODE = 0;

    /**
     *  appid
     */
    //wx57313d36c4b4d0d7
//    wxbb948d62bc17b798
}
