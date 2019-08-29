package com.ipd.allpeopledemand.bean;

public class AttentionDetailsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"release":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":16,"releaseClassId":5,"title":"123455455","region":"上海市","contacts":"","contactNumber":"","picPath":"upload/2019/07/22/74e0b58fd8ae1d754c17999ad61a32cb.jpg","details":"中央空调,家用空调,出售,设计,安装,保养,一条龙服务本公司在上海经营多年有丰富的工作经验本店常年超\u200c\u200c低价出售1P--50P二手空调挂机柜机吸顶机多联机都有品牌有大金，日立，夏普，格力，美的，海尔.....等名牌空调\r本店所有人员都经过专业的培训有专业的安装团队为您免费提供安装送货保修等一条龙服务有良好的技术完善的售后服务凡购买本店的空调可免费保修","releaseTime":"2019-07-22 17:30:39","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":1,"keyword":"125","isFollow":"2","type":"1","userCall":"谭维维","avatar":"upload/2019/07/22/8559787960ce75a2206cf015a7e45aeb.jpg","balance":10000,"className":null,"followId":null,"orderId":null},"price":{"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"},"IsPurchase":"2"}
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
         * release : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":16,"releaseClassId":5,"title":"123455455","region":"上海市","contacts":"","contactNumber":"","picPath":"upload/2019/07/22/74e0b58fd8ae1d754c17999ad61a32cb.jpg","details":"中央空调,家用空调,出售,设计,安装,保养,一条龙服务本公司在上海经营多年有丰富的工作经验本店常年超\u200c\u200c低价出售1P--50P二手空调挂机柜机吸顶机多联机都有品牌有大金，日立，夏普，格力，美的，海尔.....等名牌空调\r本店所有人员都经过专业的培训有专业的安装团队为您免费提供安装送货保修等一条龙服务有良好的技术完善的售后服务凡购买本店的空调可免费保修","releaseTime":"2019-07-22 17:30:39","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":1,"keyword":"125","isFollow":"2","type":"1","userCall":"谭维维","avatar":"upload/2019/07/22/8559787960ce75a2206cf015a7e45aeb.jpg","balance":10000,"className":null,"followId":null,"orderId":null}
         * price : {"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"}
         * IsPurchase : 2
         */

        private ReleaseBean release;
        private PriceBean price;
        private String IsPurchase;

        public ReleaseBean getRelease() {
            return release;
        }

        public void setRelease(ReleaseBean release) {
            this.release = release;
        }

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public String getIsPurchase() {
            return IsPurchase;
        }

        public void setIsPurchase(String IsPurchase) {
            this.IsPurchase = IsPurchase;
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
             * releaseId : 16
             * releaseClassId : 5
             * title : 123455455
             * region : 上海市
             * contacts :
             * contactNumber :
             * picPath : upload/2019/07/22/74e0b58fd8ae1d754c17999ad61a32cb.jpg
             * details : 中央空调,家用空调,出售,设计,安装,保养,一条龙服务本公司在上海经营多年有丰富的工作经验本店常年超‌‌低价出售1P--50P二手空调挂机柜机吸顶机多联机都有品牌有大金，日立，夏普，格力，美的，海尔.....等名牌空调本店所有人员都经过专业的培训有专业的安装团队为您免费提供安装送货保修等一条龙服务有良好的技术完善的售后服务凡购买本店的空调可免费保修
             * releaseTime : 2019-07-22 17:30:39
             * browseNum : 0
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 1
             * keyword : 125
             * isFollow : 2
             * type : 1
             * userCall : 谭维维
             * avatar : upload/2019/07/22/8559787960ce75a2206cf015a7e45aeb.jpg
             * balance : 10000.0
             * className : null
             * followId : null
             * orderId : null
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
            private Object orderId;
            private String wechatNumber;

            public String getWechatNumber() {
                return wechatNumber;
            }

            public void setWechatNumber(String wechatNumber) {
                this.wechatNumber = wechatNumber;
            }

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

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public static class ParamsBean {
            }
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
            private ParamsBeanX params;
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public static class ParamsBeanX {
            }
        }
    }
}
