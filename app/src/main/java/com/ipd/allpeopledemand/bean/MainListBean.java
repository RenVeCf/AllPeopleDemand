package com.ipd.allpeopledemand.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MainListBean {

    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"noticeList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"noticeId":1,"noticeTitle":"上线通知！","noticeContent":"全民需要","status":null,"sort":null}],"releaseList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":73,"releaseClassId":1,"title":"租房","region":null,"contacts":null,"contactNumber":null,"picPath":null,"details":null,"releaseTime":null,"browseNum":null,"purchaseNum":null,"isRecommend":null,"status":null,"userId":null,"keyword":null,"isFollow":"1","type":"1","userCall":null,"avatar":null,"balance":null,"className":null,"followId":null,"orderId":null,"notPurchase":"1","detailType":null,"detailUrl":null,"wholeCountry":null,"systemOpen":null,"longitude":null,"latitude":null,"code":null,"systemStatus":null,"wechatNumber":null,"examineStatus":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":74,"releaseClassId":1,"title":null,"region":null,"contacts":null,"contactNumber":null,"picPath":"upload/2019/08/28/41008c88d06d75b927b09c5897e6dd52.jpg","details":null,"releaseTime":null,"browseNum":null,"purchaseNum":null,"isRecommend":null,"status":null,"userId":null,"keyword":null,"isFollow":"1","type":"2","userCall":null,"avatar":null,"balance":null,"className":null,"followId":null,"orderId":null,"notPurchase":"1","detailType":"2","detailUrl":null,"wholeCountry":null,"systemOpen":null,"longitude":null,"latitude":null,"code":null,"systemStatus":null,"wechatNumber":null,"examineStatus":null}]}
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
        private List<NoticeListBean> noticeList;
        private List<ReleaseListBean> releaseList;

        public List<NoticeListBean> getNoticeList() {
            return noticeList;
        }

        public void setNoticeList(List<NoticeListBean> noticeList) {
            this.noticeList = noticeList;
        }

        public List<ReleaseListBean> getReleaseList() {
            return releaseList;
        }

        public void setReleaseList(List<ReleaseListBean> releaseList) {
            this.releaseList = releaseList;
        }

        public static class NoticeListBean implements MultiItemEntity {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * noticeId : 1
             * noticeTitle : 上线通知！
             * noticeContent : 全民需要
             * status : null
             * sort : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int noticeId;
            private String noticeTitle;
            private String noticeContent;
            private Object status;
            private Object sort;
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

            public int getNoticeId() {
                return noticeId;
            }

            public void setNoticeId(int noticeId) {
                this.noticeId = noticeId;
            }

            public String getNoticeTitle() {
                return noticeTitle;
            }

            public void setNoticeTitle(String noticeTitle) {
                this.noticeTitle = noticeTitle;
            }

            public String getNoticeContent() {
                return noticeContent;
            }

            public void setNoticeContent(String noticeContent) {
                this.noticeContent = noticeContent;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public static class ParamsBean {
            }
        }

        public static class ReleaseListBean implements MultiItemEntity{
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
             * region : null
             * contacts : null
             * contactNumber : null
             * picPath : null
             * details : null
             * releaseTime : null
             * browseNum : null
             * purchaseNum : null
             * isRecommend : null
             * status : null
             * userId : null
             * keyword : null
             * isFollow : 1
             * type : 1
             * userCall : null
             * avatar : null
             * balance : null
             * className : null
             * followId : null
             * orderId : null
             * notPurchase : 1
             * detailType : null
             * detailUrl : null
             * wholeCountry : null
             * systemOpen : null
             * longitude : null
             * latitude : null
             * code : null
             * systemStatus : null
             * wechatNumber : null
             * examineStatus : null
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
            private Object region;
            private Object contacts;
            private Object contactNumber;
            private Object picPath;
            private Object details;
            private Object releaseTime;
            private Object browseNum;
            private Object purchaseNum;
            private Object isRecommend;
            private Object status;
            private Object userId;
            private Object keyword;
            private String isFollow;
            private String type;
            private Object userCall;
            private Object avatar;
            private Object balance;
            private Object className;
            private Object followId;
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
            private Object wechatNumber;
            private Object examineStatus;
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

            public Object getPicPath() {
                return picPath;
            }

            public void setPicPath(Object picPath) {
                this.picPath = picPath;
            }

            public Object getDetails() {
                return details;
            }

            public void setDetails(Object details) {
                this.details = details;
            }

            public Object getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(Object releaseTime) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
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

            public Object getUserCall() {
                return userCall;
            }

            public void setUserCall(Object userCall) {
                this.userCall = userCall;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
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

            public Object getWechatNumber() {
                return wechatNumber;
            }

            public void setWechatNumber(Object wechatNumber) {
                this.wechatNumber = wechatNumber;
            }

            public Object getExamineStatus() {
                return examineStatus;
            }

            public void setExamineStatus(Object examineStatus) {
                this.examineStatus = examineStatus;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public static class ParamsBeanX {
            }
        }
    }
}
