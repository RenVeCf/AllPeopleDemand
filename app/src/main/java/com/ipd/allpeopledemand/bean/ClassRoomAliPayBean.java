package com.ipd.allpeopledemand.bean;

public class ClassRoomAliPayBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"sign":"partner=\"2088331790344842\"&seller_id=\"454808413@qq.com\"&out_trade_no=\"190740348601\"&subject=\"全名需求\"&body=\"支付宝付款\"&total_fee=\"0.1\"&notify_url=\"http://47.93.126.235:8080/qmxq/appUser/classroomPay/alipayReturnUrl\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"&sign=\"LU7cuzpVXGKU5hgOYojFEzbe%2FEv5%2BqlGbju5vI77scDmd%2BImihRhf%2BvLWmCvWx7aKj0vI%2FtSN5kDdMPtat%2Bun8idOQoPGfCqncE7LJy6LPe9IQYb82CJvLSDJF3888i0JpbEE54tcnfqaKeVTzPrroinfhSUeZ%2Ba%2B4XUzvTL%2BmY%3D\"&sign_type=\"RSA\""}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sign : partner="2088331790344842"&seller_id="454808413@qq.com"&out_trade_no="190740348601"&subject="全名需求"&body="支付宝付款"&total_fee="0.1"&notify_url="http://47.93.126.235:8080/qmxq/appUser/classroomPay/alipayReturnUrl"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="LU7cuzpVXGKU5hgOYojFEzbe%2FEv5%2BqlGbju5vI77scDmd%2BImihRhf%2BvLWmCvWx7aKj0vI%2FtSN5kDdMPtat%2Bun8idOQoPGfCqncE7LJy6LPe9IQYb82CJvLSDJF3888i0JpbEE54tcnfqaKeVTzPrroinfhSUeZ%2Ba%2B4XUzvTL%2BmY%3D"&sign_type="RSA"
         */

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
