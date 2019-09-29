package com.ipd.allpeopledemand.bean;

import com.google.gson.annotations.SerializedName;

public class MyPushDemandTypeBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"sign":{"package":"Sign=WXPay","appid":"wx57313d36c4b4d0d7","sign":"5BA219D6E492A8A7CD017690B26918F0","partnerid":"1544550131","prepayid":"wx2910395101911151a649413f1005444200","noncestr":"1039493829","timestamp":1569724790}}
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
         * sign : partner="2088531607992767"&seller_id="454808413@qq.com"&out_trade_no="190937444651"&subject="全名需求"&body="支付宝付款"&total_fee="0.01"&notify_url="http://47.93.126.235:8080/qmxq/appUser/myRelease/alipayReturnUrl"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="btxKA3Mvws3bsR%2FHV9pS2qzJo%2BlvEoO3b3HeTVnUqxtqwFYBvxIpS7MzM0clLSlpZdNrFBchUnBBlvkfSssiGwInylQbbKpyM0lmpITJLGdgvZbY8QSjBwQ6vA%2FxcGAC%2B5v7ONwhMyykukBsdv3Q13AYruCCA9scTfv%2FitUabJQ%3D"&sign_type="RSA"
         * sign : {"package":"Sign=WXPay","appid":"wx57313d36c4b4d0d7","sign":"5BA219D6E492A8A7CD017690B26918F0","partnerid":"1544550131","prepayid":"wx2910395101911151a649413f1005444200","noncestr":"1039493829","timestamp":1569724790}
         */

        private SignBean sign1;
        private String sign2;

        public String getSign2() {
            return sign2;
        }

        public void setSign2(String sign2) {
            this.sign2 = sign2;
        }

        public SignBean getSign1() {
            return sign1;
        }

        public void setSign1(SignBean sign1) {
            this.sign1 = sign1;
        }

        public static class SignBean {
            /**
             * package : Sign=WXPay
             * appid : wx57313d36c4b4d0d7
             * sign : 5BA219D6E492A8A7CD017690B26918F0
             * partnerid : 1544550131
             * prepayid : wx2910395101911151a649413f1005444200
             * noncestr : 1039493829
             * timestamp : 1569724790
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private int timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
