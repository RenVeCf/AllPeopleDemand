package com.ipd.allpeopledemand.bean;

import com.google.gson.annotations.SerializedName;

public class MainWechatPayBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"sign":{"package":"Sign=WXPay","appid":"wx57313d36c4b4d0d7","sign":"3CBD63B0A5B5049070E5003B446EB233","partnerid":"1544550131","prepayid":"wx23183128168604869af99b731895435700","noncestr":"1831271874","timestamp":1563877888}}
     */

    private String msg;
    private int code;
    private ClassRoomWechatPayBean.DataBean data;

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

    public ClassRoomWechatPayBean.DataBean getData() {
        return data;
    }

    public void setData(ClassRoomWechatPayBean.DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sign : {"package":"Sign=WXPay","appid":"wx57313d36c4b4d0d7","sign":"3CBD63B0A5B5049070E5003B446EB233","partnerid":"1544550131","prepayid":"wx23183128168604869af99b731895435700","noncestr":"1831271874","timestamp":1563877888}
         */

        private ClassRoomWechatPayBean.DataBean.SignBean sign;

        public ClassRoomWechatPayBean.DataBean.SignBean getSign() {
            return sign;
        }

        public void setSign(ClassRoomWechatPayBean.DataBean.SignBean sign) {
            this.sign = sign;
        }

        public static class SignBean {
            /**
             * package : Sign=WXPay
             * appid : wx57313d36c4b4d0d7
             * sign : 3CBD63B0A5B5049070E5003B446EB233
             * partnerid : 1544550131
             * prepayid : wx23183128168604869af99b731895435700
             * noncestr : 1831271874
             * timestamp : 1563877888
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
