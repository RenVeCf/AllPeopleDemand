package com.ipd.allpeopledemand.bean;

public class ShareBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"shareUrl":"地址现在没有","invAverage":23,"user":{"searchValue":null,"createBy":null,"createTime":"2019-07-17 15:59:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":16,"avatar":"upload/2019/07/17/01bcdc336e1ffef61e83978b258d806b.jpeg","telPhone":"18502994087","password":"74e4e50701161c4d481d41ca837f1864","userCall":"全民433","parentId":null,"balance":100,"nationalNum":"20190717423","sex":"0","age":0,"maritalStatus":"0","userType":"1","integral":0,"twoCode":"upload/QRCode/20190717423.png"}}
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
         * shareUrl : 地址现在没有
         * invAverage : 23
         * user : {"searchValue":null,"createBy":null,"createTime":"2019-07-17 15:59:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":16,"avatar":"upload/2019/07/17/01bcdc336e1ffef61e83978b258d806b.jpeg","telPhone":"18502994087","password":"74e4e50701161c4d481d41ca837f1864","userCall":"全民433","parentId":null,"balance":100,"nationalNum":"20190717423","sex":"0","age":0,"maritalStatus":"0","userType":"1","integral":0,"twoCode":"upload/QRCode/20190717423.png"}
         */

        private String shareUrl;
        private int invAverage;
        private UserBean user;

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public int getInvAverage() {
            return invAverage;
        }

        public void setInvAverage(int invAverage) {
            this.invAverage = invAverage;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-17 15:59:12
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * userId : 16
             * avatar : upload/2019/07/17/01bcdc336e1ffef61e83978b258d806b.jpeg
             * telPhone : 18502994087
             * password : 74e4e50701161c4d481d41ca837f1864
             * userCall : 全民433
             * parentId : null
             * balance : 100.0
             * nationalNum : 20190717423
             * sex : 0
             * age : 0
             * maritalStatus : 0
             * userType : 1
             * integral : 0
             * twoCode : upload/QRCode/20190717423.png
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int userId;
            private String avatar;
            private String telPhone;
            private String password;
            private String userCall;
            private Object parentId;
            private double balance;
            private String nationalNum;
            private String sex;
            private int age;
            private String maritalStatus;
            private String userType;
            private int integral;
            private String twoCode;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTelPhone() {
                return telPhone;
            }

            public void setTelPhone(String telPhone) {
                this.telPhone = telPhone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getUserCall() {
                return userCall;
            }

            public void setUserCall(String userCall) {
                this.userCall = userCall;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public String getNationalNum() {
                return nationalNum;
            }

            public void setNationalNum(String nationalNum) {
                this.nationalNum = nationalNum;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getTwoCode() {
                return twoCode;
            }

            public void setTwoCode(String twoCode) {
                this.twoCode = twoCode;
            }

            public static class ParamsBean {
            }
        }
    }
}
