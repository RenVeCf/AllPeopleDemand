package com.ipd.allpeopledemand.bean;

import java.util.List;

public class MyBuyDemandListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"demandList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":6,"userId":3,"payTime":"2019-07-23 12:22:11","payStatus":"2","payWay":"1","payMoney":10,"successTime":null,"releaseId":22,"orderNo":"190773110259","isFollow":"1","userCall":"佛系点赞","avatar":"upload/2019/07/22/f7045b343968b75395aacd8cad87ed02.jpeg","balance":null,"className":"二手","releaseTime":"2019-07-23 10:40:24","title":"漂亮的包包"}]}
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
        private List<DemandListBean> demandList;

        public List<DemandListBean> getDemandList() {
            return demandList;
        }

        public void setDemandList(List<DemandListBean> demandList) {
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
             * orderId : 6
             * userId : 3
             * payTime : 2019-07-23 12:22:11
             * payStatus : 2
             * payWay : 1
             * payMoney : 10.0
             * successTime : null
             * releaseId : 22
             * orderNo : 190773110259
             * isFollow : 1
             * userCall : 佛系点赞
             * avatar : upload/2019/07/22/f7045b343968b75395aacd8cad87ed02.jpeg
             * balance : null
             * className : 二手
             * releaseTime : 2019-07-23 10:40:24
             * title : 漂亮的包包
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int orderId;
            private int userId;
            private String payTime;
            private String payStatus;
            private String payWay;
            private double payMoney;
            private Object successTime;
            private int releaseId;
            private String orderNo;
            private String isFollow;
            private String userCall;
            private String avatar;
            private Object balance;
            private String className;
            private String releaseTime;
            private String title;

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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPayTime() {
                return payTime;
            }

            public void setPayTime(String payTime) {
                this.payTime = payTime;
            }

            public String getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public String getPayWay() {
                return payWay;
            }

            public void setPayWay(String payWay) {
                this.payWay = payWay;
            }

            public double getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public Object getSuccessTime() {
                return successTime;
            }

            public void setSuccessTime(Object successTime) {
                this.successTime = successTime;
            }

            public int getReleaseId() {
                return releaseId;
            }

            public void setReleaseId(int releaseId) {
                this.releaseId = releaseId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
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

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public static class ParamsBean {
            }
        }
    }
}
