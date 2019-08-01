package com.ipd.allpeopledemand.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class AttentionListBean {
    /**
     * msg : 操作成功
     * total : 2
     * code : 200
     * data : {"followList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":11,"releaseClassId":3,"title":"帅哥一枚求小姐姐带走\u2026\u2026","region":null,"contacts":null,"contactNumber":null,"picPath":"","details":null,"releaseTime":"2019-07-24 18:13:34","browseNum":null,"purchaseNum":null,"isRecommend":null,"status":"1","userId":41,"keyword":null,"isFollow":"2","type":"1","userCall":"Zero","avatar":"upload/2019/07/27/d27dcd667af0f59659b39e1bfe88ed0b.jpg","balance":null,"className":"交友","followId":7,"orderId":null,"notPurchase":"2","detailType":null,"detailUrl":null,"wholeCountry":null,"systemOpen":null,"longitude":null,"latitude":null,"code":null,"systemStatus":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":7,"releaseClassId":2,"title":"OPPO Reno系列，全新拍照手机，10倍混合光变","region":null,"contacts":null,"contactNumber":null,"picPath":"upload/2019/07/24/a72ad5a0e7a3a3519cec7cbb12545362.jpg","details":null,"releaseTime":"2019-07-24 15:12:25","browseNum":null,"purchaseNum":null,"isRecommend":null,"status":"1","userId":null,"keyword":null,"isFollow":"1","type":"2","userCall":null,"avatar":null,"balance":null,"className":null,"followId":54,"orderId":null,"notPurchase":"1","detailType":"2","detailUrl":null,"wholeCountry":null,"systemOpen":null,"longitude":null,"latitude":null,"code":null,"systemStatus":null}]}
     */

    private String msg;
    private int total;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        private List<FollowListBean> followList;

        public List<FollowListBean> getFollowList() {
            return followList;
        }

        public void setFollowList(List<FollowListBean> followList) {
            this.followList = followList;
        }

        public static class FollowListBean implements MultiItemEntity {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * releaseId : 11
             * releaseClassId : 3
             * title : 帅哥一枚求小姐姐带走……
             * region : null
             * contacts : null
             * contactNumber : null
             * picPath :
             * details : null
             * releaseTime : 2019-07-24 18:13:34
             * browseNum : null
             * purchaseNum : null
             * isRecommend : null
             * status : 1
             * userId : 41
             * keyword : null
             * isFollow : 2
             * type : 1
             * userCall : Zero
             * avatar : upload/2019/07/27/d27dcd667af0f59659b39e1bfe88ed0b.jpg
             * balance : null
             * className : 交友
             * followId : 7
             * orderId : null
             * notPurchase : 2
             * detailType : null
             * detailUrl : null
             * wholeCountry : null
             * systemOpen : null
             * longitude : null
             * latitude : null
             * code : null
             * systemStatus : null
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
            private Object region;
            private Object contacts;
            private Object contactNumber;
            private String picPath;
            private Object details;
            private String releaseTime;
            private Object browseNum;
            private Object purchaseNum;
            private Object isRecommend;
            private String status;
            private int userId;
            private Object keyword;
            private String isFollow;
            private String type;
            private String userCall;
            private String avatar;
            private Object balance;
            private String className;
            private int followId;
            private Object orderId;
            private String notPurchase;
            private String detailType;
            private String detailUrl;
            private Object wholeCountry;
            private Object systemOpen;
            private Object longitude;
            private Object latitude;
            private Object code;
            private Object systemStatus;
            private int itemType;

            public void setItemType(int itemType) {
                this.itemType = itemType;
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

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getContacts() {
                return contacts;
            }

            public void setContacts(Object contacts) {
                this.contacts = contacts;
            }

            public Object getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(Object contactNumber) {
                this.contactNumber = contactNumber;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public Object getDetails() {
                return details;
            }

            public void setDetails(Object details) {
                this.details = details;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public Object getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(Object browseNum) {
                this.browseNum = browseNum;
            }

            public Object getPurchaseNum() {
                return purchaseNum;
            }

            public void setPurchaseNum(Object purchaseNum) {
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

            public Object getKeyword() {
                return keyword;
            }

            public void setKeyword(Object keyword) {
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

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public int getFollowId() {
                return followId;
            }

            public void setFollowId(int followId) {
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

            public String getDetailType() {
                return detailType;
            }

            public void setDetailType(String detailType) {
                this.detailType = detailType;
            }

            public String getDetailUrl() {
                return detailUrl;
            }

            public void setDetailUrl(String detailUrl) {
                this.detailUrl = detailUrl;
            }

            public Object getWholeCountry() {
                return wholeCountry;
            }

            public void setWholeCountry(Object wholeCountry) {
                this.wholeCountry = wholeCountry;
            }

            public Object getSystemOpen() {
                return systemOpen;
            }

            public void setSystemOpen(Object systemOpen) {
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

            public Object getSystemStatus() {
                return systemStatus;
            }

            public void setSystemStatus(Object systemStatus) {
                this.systemStatus = systemStatus;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public static class ParamsBean {
            }
        }
    }
}
