package com.ipd.allpeopledemand.bean;

public class MainDetailsBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"release":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":73,"releaseClassId":1,"title":"租房","region":"上海市","contacts":"张","contactNumber":"15021661149","picPath":"","details":"想着青浦区租房，一室一厅，小区环境优美","releaseTime":"2019-08-28 18:33:00","browseNum":7,"purchaseNum":0,"isRecommend":"","status":"1","userId":76,"keyword":"找房","isFollow":"1","type":"1","userCall":"新用户","avatar":"upload/default/ic_default_head.png","balance":20.5,"className":null,"followId":null,"orderId":null,"notPurchase":"1","detailType":null,"detailUrl":null,"wholeCountry":"1","systemOpen":"0","longitude":null,"latitude":null,"code":null,"systemStatus":"1","wechatNumber":"ijhggh","examineStatus":"1"}}
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
         * release : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":73,"releaseClassId":1,"title":"租房","region":"上海市","contacts":"张","contactNumber":"15021661149","picPath":"","details":"想着青浦区租房，一室一厅，小区环境优美","releaseTime":"2019-08-28 18:33:00","browseNum":7,"purchaseNum":0,"isRecommend":"","status":"1","userId":76,"keyword":"找房","isFollow":"1","type":"1","userCall":"新用户","avatar":"upload/default/ic_default_head.png","balance":20.5,"className":null,"followId":null,"orderId":null,"notPurchase":"1","detailType":null,"detailUrl":null,"wholeCountry":"1","systemOpen":"0","longitude":null,"latitude":null,"code":null,"systemStatus":"1","wechatNumber":"ijhggh","examineStatus":"1"}
         */

        private ReleaseBean release;

        public ReleaseBean getRelease() {
            return release;
        }

        public void setRelease(ReleaseBean release) {
            this.release = release;
        }

        public static class ReleaseBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * releaseId : 73
             * releaseClassId : 1
             * title : 租房
             * region : 上海市
             * contacts : 张
             * contactNumber : 15021661149
             * picPath :
             * details : 想着青浦区租房，一室一厅，小区环境优美
             * releaseTime : 2019-08-28 18:33:00
             * browseNum : 7
             * purchaseNum : 0
             * isRecommend :
             * status : 1
             * userId : 76
             * keyword : 找房
             * isFollow : 1
             * type : 1
             * userCall : 新用户
             * avatar : upload/default/ic_default_head.png
             * balance : 20.5
             * className : null
             * followId : null
             * orderId : null
             * notPurchase : 1
             * detailType : null
             * detailUrl : null
             * wholeCountry : 1
             * systemOpen : 0
             * longitude : null
             * latitude : null
             * code : null
             * systemStatus : 1
             * wechatNumber : ijhggh
             * examineStatus : 1
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int releaseId;
            private int releaseClassId;
            private String title;
            private String region;
            private String contacts;
            private String contactNumber;
            private String picPath;
            private String details;
            private String releaseTime;
            private int browseNum;
            private int purchaseNum;
            private String isRecommend;
            private String status;
            private int userId;
            private String keyword;
            private String isFollow;
            private String type;
            private String userCall;
            private String avatar;
            private double balance;
            private Object className;
            private Object followId;
            private Object orderId;
            private String notPurchase;
            private Object detailType;
            private Object detailUrl;
            private String wholeCountry;
            private String systemOpen;
            private Object longitude;
            private Object latitude;
            private Object code;
            private String systemStatus;
            private String wechatNumber;
            private String examineStatus;

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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public int getReleaseId() {
                return releaseId;
            }

            public void setReleaseId(int releaseId) {
                this.releaseId = releaseId;
            }

            public int getReleaseClassId() {
                return releaseClassId;
            }

            public void setReleaseClassId(int releaseClassId) {
                this.releaseClassId = releaseClassId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public int getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(int browseNum) {
                this.browseNum = browseNum;
            }

            public int getPurchaseNum() {
                return purchaseNum;
            }

            public void setPurchaseNum(int purchaseNum) {
                this.purchaseNum = purchaseNum;
            }

            public String getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(String isRecommend) {
                this.isRecommend = isRecommend;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(String isFollow) {
                this.isFollow = isFollow;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserCall() {
                return userCall;
            }

            public void setUserCall(String userCall) {
                this.userCall = userCall;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public Object getClassName() {
                return className;
            }

            public void setClassName(Object className) {
                this.className = className;
            }

            public Object getFollowId() {
                return followId;
            }

            public void setFollowId(Object followId) {
                this.followId = followId;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public String getNotPurchase() {
                return notPurchase;
            }

            public void setNotPurchase(String notPurchase) {
                this.notPurchase = notPurchase;
            }

            public Object getDetailType() {
                return detailType;
            }

            public void setDetailType(Object detailType) {
                this.detailType = detailType;
            }

            public Object getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(Object detailUrl) {
                this.detailUrl = detailUrl;
            }

            public String getWholeCountry() {
                return wholeCountry;
            }

            public void setWholeCountry(String wholeCountry) {
                this.wholeCountry = wholeCountry;
            }

            public String getSystemOpen() {
                return systemOpen;
            }

            public void setSystemOpen(String systemOpen) {
                this.systemOpen = systemOpen;
            }

            public Object getLongitude() {
                return longitude;
            }

            public void setLongitude(Object longitude) {
                this.longitude = longitude;
            }

            public Object getLatitude() {
                return latitude;
            }

            public void setLatitude(Object latitude) {
                this.latitude = latitude;
            }

            public Object getCode() {
                return code;
            }

            public void setCode(Object code) {
                this.code = code;
            }

            public String getSystemStatus() {
                return systemStatus;
            }

            public void setSystemStatus(String systemStatus) {
                this.systemStatus = systemStatus;
            }

            public String getWechatNumber() {
                return wechatNumber;
            }

            public void setWechatNumber(String wechatNumber) {
                this.wechatNumber = wechatNumber;
            }

            public String getExamineStatus() {
                return examineStatus;
            }

            public void setExamineStatus(String examineStatus) {
                this.examineStatus = examineStatus;
            }

            public static class ParamsBean {
            }
        }
    }
}
