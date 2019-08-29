package com.ipd.allpeopledemand.bean;

public class MyBuyDemandDetailsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"demandList":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":22,"releaseClassId":4,"title":"漂亮的包包","region":"上海市","contacts":"Mary","contactNumber":"15021661148","picPath":"upload/2019/07/23/d58c47158bc49892095dd3df30cbc073.jpg","details":"8成新包包，喜欢的来看看","releaseTime":"2019-07-23 10:40:24","browseNum":17,"purchaseNum":0,"isRecommend":null,"status":"1","userId":3,"keyword":"旧包包","isFollow":"1","type":"1","userCall":"佛系点赞","avatar":"upload/2019/07/22/f7045b343968b75395aacd8cad87ed02.jpeg","balance":null,"className":null,"followId":null,"orderId":6,"notPurchase":"1"}}
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
         * demandList : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":22,"releaseClassId":4,"title":"漂亮的包包","region":"上海市","contacts":"Mary","contactNumber":"15021661148","picPath":"upload/2019/07/23/d58c47158bc49892095dd3df30cbc073.jpg","details":"8成新包包，喜欢的来看看","releaseTime":"2019-07-23 10:40:24","browseNum":17,"purchaseNum":0,"isRecommend":null,"status":"1","userId":3,"keyword":"旧包包","isFollow":"1","type":"1","userCall":"佛系点赞","avatar":"upload/2019/07/22/f7045b343968b75395aacd8cad87ed02.jpeg","balance":null,"className":null,"followId":null,"orderId":6,"notPurchase":"1"}
         */

        private DemandListBean demandList;

        public DemandListBean getDemandList() {
            return demandList;
        }

        public void setDemandList(DemandListBean demandList) {
            this.demandList = demandList;
        }

        public static class DemandListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * releaseId : 22
             * releaseClassId : 4
             * title : 漂亮的包包
             * region : 上海市
             * contacts : Mary
             * contactNumber : 15021661148
             * picPath : upload/2019/07/23/d58c47158bc49892095dd3df30cbc073.jpg
             * details : 8成新包包，喜欢的来看看
             * releaseTime : 2019-07-23 10:40:24
             * browseNum : 17
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 3
             * keyword : 旧包包
             * isFollow : 1
             * type : 1
             * userCall : 佛系点赞
             * avatar : upload/2019/07/22/f7045b343968b75395aacd8cad87ed02.jpeg
             * balance : null
             * className : null
             * followId : null
             * orderId : 6
             * notPurchase : 1
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
            private Object balance;
            private Object className;
            private Object followId;
            private int orderId;
            private String notPurchase;
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

            public Object getBalance() {
                return balance;
            }

            public void setBalance(Object balance) {
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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getNotPurchase() {
                return notPurchase;
            }

            public void setNotPurchase(String notPurchase) {
                this.notPurchase = notPurchase;
            }

            public static class ParamsBean {
            }
        }
    }
}
