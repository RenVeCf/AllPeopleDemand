package com.ipd.allpeopledemand.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MainListBean {
    /**
     * msg : 操作成功
     * total : 5
     * code : 200
     * data : {"noticeList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:39:19","updateBy":null,"updateTime":null,"remark":null,"params":{},"noticeId":1,"noticeTitle":"上线通知！","noticeContent":"全名需求2019年8月份正式上线！","status":"1","sort":1},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:40:06","updateBy":null,"updateTime":null,"remark":null,"params":{},"noticeId":2,"noticeTitle":"内测中!","noticeContent":"内测版本正在测试中，敬请期代，8月份","status":"1","sort":2}],"releaseList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":17,"releaseClassId":2,"title":"1234567890kjhfffffffffffffffffffff","region":"北京市","contacts":"Tan","contactNumber":"15021661148","picPath":"upload/2019/07/17/3f3ca340017c4f45ad0895385a5356f3.jpg","details":"12345678909876gggvvfffrrddcvbnmkiijhfcsswwwssxcc","releaseTime":"2019-07-17 14:51:34","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":10,"keyword":"123","isFollow":"1","type":"1","userCall":"Tan","avatar":"upload/2019/07/18/22346863a25c66556450d86bfbf10659.jpg","balance":null,"className":null,"followId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":21,"releaseClassId":2,"title":"Fujinjin","region":"上海市","contacts":"Liao","contactNumber":"15021661148","picPath":"upload/2019/07/18/d68d1e8a2afb2f6de6f2fbf64ba88756.jpg","details":"Fujinjin1234","releaseTime":"2019-07-18 12:31:26","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":10,"keyword":"Fujinjinji","isFollow":"1","type":"1","userCall":"Tan","avatar":"upload/2019/07/18/22346863a25c66556450d86bfbf10659.jpg","balance":null,"className":null,"followId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":25,"releaseClassId":2,"title":"附近信息","region":"上海市","contacts":"翟先生","contactNumber":"13472548777","picPath":"upload/2019/07/18/e3c84250b8baa8fc1ab72b5c3fdd6063.jpeg","details":"附近信息附近信息附近信息附近信息附近信息附近信息","releaseTime":"2019-07-18 16:07:18","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":11,"keyword":"附近,游戏","isFollow":"1","type":"1","userCall":"全民848","avatar":"upload/2019/07/18/04ccc81849ab67e0b4b185d71ac63138.jpeg","balance":null,"className":null,"followId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":27,"releaseClassId":2,"title":"咯拒绝","region":"上海","contacts":"得到","contactNumber":"13472548777","picPath":"upload/2019/07/18/fd030277a0cc113d90bbc0133f710779.jpeg","details":"离我来咯墨迹","releaseTime":"2019-07-18 16:10:19","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":11,"keyword":"罢了,看","isFollow":"1","type":"1","userCall":"全民848","avatar":"upload/2019/07/18/04ccc81849ab67e0b4b185d71ac63138.jpeg","balance":null,"className":null,"followId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":33,"releaseClassId":2,"title":"科罗拉","region":"上海市","contacts":"粑粑","contactNumber":"18502994087","picPath":"upload/2019/07/18/09f053ab947d6f38e9bdcc191f8c1ead.jpeg","details":"你撸啦撸啦","releaseTime":"2019-07-18 16:23:58","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":9,"keyword":"额额,额额,额额","isFollow":"2","type":"1","userCall":"全民329d","avatar":"upload/2019/07/18/e532bcdb5a30838e321a8ae724ef788a.jpeg","balance":null,"className":null,"followId":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":35,"releaseClassId":5,"title":"么的","region":"上海市","contacts":"麽","contactNumber":"18502994087","picPath":"upload/2019/07/18/1b05ba8f4ba6a24ce41653f1a4fd944c.jpeg","details":"need","releaseTime":"2019-07-18 16:28:30","browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":9,"keyword":"拉吧,额额,呃呃","isFollow":"1","type":"2","userCall":null,"avatar":null,"balance":null,"className":null,"followId":null}]}
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
             * createTime : 2019-07-14 23:39:19
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * noticeId : 1
             * noticeTitle : 上线通知！
             * noticeContent : 全名需求2019年8月份正式上线！
             * status : 1
             * sort : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int noticeId;
            private String noticeTitle;
            private String noticeContent;
            private String status;
            private int sort;
            private int itemType;

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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            @Override
            public int getItemType() {
                return itemType;
            }

            public static class ParamsBean {
            }
        }

        public static class ReleaseListBean implements MultiItemEntity {
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
             * contacts : Tan
             * contactNumber : 15021661148
             * picPath : upload/2019/07/17/3f3ca340017c4f45ad0895385a5356f3.jpg
             * details : 12345678909876gggvvfffrrddcvbnmkiijhfcsswwwssxcc
             * releaseTime : 2019-07-17 14:51:34
             * browseNum : 0
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 10
             * keyword : 123
             * isFollow : 1
             * type : 1
             * userCall : Tan
             * avatar : upload/2019/07/18/22346863a25c66556450d86bfbf10659.jpg
             * balance : null
             * className : null
             * followId : null
             * detailType : null
             * detailUrl : null
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
            private Object balance;
            private String className;
            private Object followId;
            private String detailType;
            private String detailUrl;
            private int itemType;

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

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public Object getFollowId() {
                return followId;
            }

            public void setFollowId(Object followId) {
                this.followId = followId;
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
