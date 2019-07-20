package com.ipd.allpeopledemand.bean;

import java.util.List;

public class MyPushListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"releaseList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":9,"releaseClassId":5,"title":"么哒","region":"上海市","contacts":"我的","contactNumber":"18502994087","picPath":"upload/2019/07/17/43e2c4261fd80fe7490de3a55e6dadc0.jpeg","details":"夜班","releaseTime":null,"browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":11,"keyword":"呢饿","isFollow":1,"userCall":"aaaajjjj","avatar":"默认头像","className":null,"followId":null}]}
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
        private List<ReleaseListBean> releaseList;

        public List<ReleaseListBean> getReleaseList() {
            return releaseList;
        }

        public void setReleaseList(List<ReleaseListBean> releaseList) {
            this.releaseList = releaseList;
        }

        public static class ReleaseListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * releaseId : 9
             * releaseClassId : 5
             * title : 么哒
             * region : 上海市
             * contacts : 我的
             * contactNumber : 18502994087
             * picPath : upload/2019/07/17/43e2c4261fd80fe7490de3a55e6dadc0.jpeg
             * details : 夜班
             * releaseTime : null
             * browseNum : 0
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 11
             * keyword : 呢饿
             * isFollow : 1
             * userCall : aaaajjjj
             * avatar : 默认头像
             * className : null
             * followId : null
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
            private String userCall;
            private String avatar;
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

            public static class ParamsBean {
            }
        }
    }
}
