package com.ipd.allpeopledemand.bean;

public class RegisterBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"user":{"searchValue":null,"createBy":null,"createTime":"2019-07-24 14:18:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":4,"avatar":"upload/2019/08/30/65ea1ed5da2c56230483916831b48071.jpeg","telPhone":"18502994087","password":"8cd53fc68f2dfba49e3c7871fac0443a","userCall":"ooooo","parentId":0,"balance":494,"nationalNum":"20190724522","sex":"1","age":16,"maritalStatus":"1","userType":"1","integral":1,"twoCode":"upload/QRCode/20190724522.png","invitationCode":"ao44dkem","labelIds":null,"userSource":null,"num":null,"visitTime":"2019-08-30 16:57:00","wechatNumber":"ijhggh","notRelease":"1","userNum":null,"member":0,"stoptime":null},"token":"1201909291003472928508665423aorzjpsa"}
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
         * user : {"searchValue":null,"createBy":null,"createTime":"2019-07-24 14:18:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":4,"avatar":"upload/2019/08/30/65ea1ed5da2c56230483916831b48071.jpeg","telPhone":"18502994087","password":"8cd53fc68f2dfba49e3c7871fac0443a","userCall":"ooooo","parentId":0,"balance":494,"nationalNum":"20190724522","sex":"1","age":16,"maritalStatus":"1","userType":"1","integral":1,"twoCode":"upload/QRCode/20190724522.png","invitationCode":"ao44dkem","labelIds":null,"userSource":null,"num":null,"visitTime":"2019-08-30 16:57:00","wechatNumber":"ijhggh","notRelease":"1","userNum":null,"member":0,"stoptime":null}
         * token : 1201909291003472928508665423aorzjpsa
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-24 14:18:48
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * userId : 4
             * avatar : upload/2019/08/30/65ea1ed5da2c56230483916831b48071.jpeg
             * telPhone : 18502994087
             * password : 8cd53fc68f2dfba49e3c7871fac0443a
             * userCall : ooooo
             * parentId : 0
             * balance : 494.0
             * nationalNum : 20190724522
             * sex : 1
             * age : 16
             * maritalStatus : 1
             * userType : 1
             * integral : 1
             * twoCode : upload/QRCode/20190724522.png
             * invitationCode : ao44dkem
             * labelIds : null
             * userSource : null
             * num : null
             * visitTime : 2019-08-30 16:57:00
             * wechatNumber : ijhggh
             * notRelease : 1
             * userNum : null
             * member : 0
             * stoptime : null
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
            private int parentId;
            private double balance;
            private String nationalNum;
            private String sex;
            private int age;
            private String maritalStatus;
            private String userType;
            private int integral;
            private String twoCode;
            private String invitationCode;
            private Object labelIds;
            private Object userSource;
            private Object num;
            private String visitTime;
            private String wechatNumber;
            private String notRelease;
            private Object userNum;
            private int member;
            private Object stoptime;

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

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
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

            public String getInvitationCode() {
                return invitationCode;
            }

            public void setInvitationCode(String invitationCode) {
                this.invitationCode = invitationCode;
            }

            public Object getLabelIds() {
                return labelIds;
            }

            public void setLabelIds(Object labelIds) {
                this.labelIds = labelIds;
            }

            public Object getUserSource() {
                return userSource;
            }

            public void setUserSource(Object userSource) {
                this.userSource = userSource;
            }

            public Object getNum() {
                return num;
            }

            public void setNum(Object num) {
                this.num = num;
            }

            public String getVisitTime() {
                return visitTime;
            }

            public void setVisitTime(String visitTime) {
                this.visitTime = visitTime;
            }

            public String getWechatNumber() {
                return wechatNumber;
            }

            public void setWechatNumber(String wechatNumber) {
                this.wechatNumber = wechatNumber;
            }

            public String getNotRelease() {
                return notRelease;
            }

            public void setNotRelease(String notRelease) {
                this.notRelease = notRelease;
            }

            public Object getUserNum() {
                return userNum;
            }

            public void setUserNum(Object userNum) {
                this.userNum = userNum;
            }

            public int getMember() {
                return member;
            }

            public void setMember(int member) {
                this.member = member;
            }

            public Object getStoptime() {
                return stoptime;
            }

            public void setStoptime(Object stoptime) {
                this.stoptime = stoptime;
            }

            public static class ParamsBean {
            }
        }
    }
}
