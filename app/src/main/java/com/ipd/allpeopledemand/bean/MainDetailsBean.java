package com.ipd.allpeopledemand.bean;

public class MainDetailsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"price":{"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"},"release":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":17,"releaseClassId":2,"title":"1234567890kjhfffffffffffffffffffff","region":"北京市","contacts":"","contactNumber":"","picPath":"upload/2019/07/17/3f3ca340017c4f45ad0895385a5356f3.jpg","details":"12345678909876gggvvfffrrddcvbnmkiijhfcsswwwssxcc","releaseTime":"2019-07-17 14:51:34","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":10,"keyword":"123","isFollow":"2","type":"1","userCall":"全民329d","avatar":"upload/2019/07/18/e532bcdb5a30838e321a8ae724ef788a.jpeg","balance":1000,"className":null,"followId":null},"IsPurchase":"2"}
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
         * price : {"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"}
         * release : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":17,"releaseClassId":2,"title":"1234567890kjhfffffffffffffffffffff","region":"北京市","contacts":"","contactNumber":"","picPath":"upload/2019/07/17/3f3ca340017c4f45ad0895385a5356f3.jpg","details":"12345678909876gggvvfffrrddcvbnmkiijhfcsswwwssxcc","releaseTime":"2019-07-17 14:51:34","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":10,"keyword":"123","isFollow":"2","type":"1","userCall":"全民329d","avatar":"upload/2019/07/18/e532bcdb5a30838e321a8ae724ef788a.jpeg","balance":1000,"className":null,"followId":null}
         * IsPurchase : 2
         */

        private PriceBean price;
        private ReleaseBean release;
        private String IsPurchase;

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public ReleaseBean getRelease() {
            return release;
        }

        public void setRelease(ReleaseBean release) {
            this.release = release;
        }

        public String getIsPurchase() {
            return IsPurchase;
        }

        public void setIsPurchase(String IsPurchase) {
            this.IsPurchase = IsPurchase;
        }

        public static class PriceBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-14 13:53:06
             * updateBy : null
             * updateTime : 2019-07-14 13:53:02
             * remark : null
             * params : {}
             * priceId : 1
             * title : null
             * integral : 18
             * money : 10.0
             * type : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsBean params;
            private int priceId;
            private Object title;
            private int integral;
            private double money;
            private String type;

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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
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

            public int getPriceId() {
                return priceId;
            }

            public void setPriceId(int priceId) {
                this.priceId = priceId;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class ParamsBean {
            }
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
             * releaseId : 17
             * releaseClassId : 2
             * title : 1234567890kjhfffffffffffffffffffff
             * region : 北京市
             * contacts :
             * contactNumber :
             * picPath : upload/2019/07/17/3f3ca340017c4f45ad0895385a5356f3.jpg
             * details : 12345678909876gggvvfffrrddcvbnmkiijhfcsswwwssxcc
             * releaseTime : 2019-07-17 14:51:34
             * browseNum : 0
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 10
             * keyword : 123
             * isFollow : 2
             * type : 1
             * userCall : 全民329d
             * avatar : upload/2019/07/18/e532bcdb5a30838e321a8ae724ef788a.jpeg
             * balance : 1000.0
             * className : null
             * followId : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
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
            private Object isRecommend;
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public Object getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(Object isRecommend) {
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

            public static class ParamsBeanX {
            }
        }
    }
}
