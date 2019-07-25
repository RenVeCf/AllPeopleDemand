package com.ipd.allpeopledemand.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class AttentionListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"followList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":1,"releaseClassId":1,"title":"测试发布内容","region":"上海市","contacts":"付宗乐","contactNumber":"15937016361","picPath":"图片地址","details":"详情内容内容内容","releaseTime":null,"browseNum":null,"purchaseNum":null,"isRecommend":null,"status":"1","userId":1,"keyword":"上海,北京,北京","isFollow":true,"userCall":"默认呢称","avatar":"默认头像","className":"推荐"}]}
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
             * releaseId : 1
             * releaseClassId : 1
             * title : 测试发布内容
             * region : 上海市
             * contacts : 付宗乐
             * contactNumber : 15937016361
             * picPath : 图片地址
             * details : 详情内容内容内容
             * releaseTime : null
             * browseNum : null
             * purchaseNum : null
             * isRecommend : null
             * status : 1
             * userId : 1
             * keyword : 上海,北京,北京
             * isFollow : true
             * userCall : 默认呢称
             * avatar : 默认头像
             * className : 推荐
             * notPurchase : "1"
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
            private Object browseNum;
            private Object purchaseNum;
            private Object isRecommend;
            private String status;
            private int userId;
            private String type;
            private String keyword;
            private String isFollow;
            private String userCall;
            private String avatar;
            private String className;
            private String notPurchase;
            private int itemType;

            public String getNotPurchase() {
                return notPurchase;
            }

            public void setNotPurchase(String notPurchase) {
                this.notPurchase = notPurchase;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

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

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String isIsFollow() {
                return isFollow;
            }

            public void setIsFollow(String isFollow) {
                this.isFollow = isFollow;
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

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
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
