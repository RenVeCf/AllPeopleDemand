package com.ipd.allpeopledemand.bean;

import java.util.List;

public class AccountBean {

    /**
     * msg : 操作成功
     * total : 6
     * code : 200
     * data : {"detailedList":[{"searchValue":null,"createBy":null,"createTime":"2019-08-21 17:19:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":309,"userId":4,"money":1,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-08-09 12:26:26","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":203,"userId":4,"money":13.5,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-08-02 17:13:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":192,"userId":4,"money":20,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-29 18:48:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":125,"userId":4,"money":30,"astype":"1","title":"系统赠送奖励余额","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-24 15:25:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":5,"userId":4,"money":0,"astype":"1","title":"发帖赠送积分","content":"","type":"2","orderNo":null,"integralNum":10},{"searchValue":null,"createBy":null,"createTime":"2019-07-24 14:18:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":4,"userId":4,"money":0,"astype":"1","title":"新用户注册赠送积分","content":"","type":"2","orderNo":null,"integralNum":10}],"balance":20.5}
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
        /**
         * detailedList : [{"searchValue":null,"createBy":null,"createTime":"2019-08-21 17:19:37","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":309,"userId":4,"money":1,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-08-09 12:26:26","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":203,"userId":4,"money":13.5,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-08-02 17:13:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":192,"userId":4,"money":20,"astype":"2","title":"购买课堂","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-29 18:48:52","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":125,"userId":4,"money":30,"astype":"1","title":"系统赠送奖励余额","content":"","type":"2","orderNo":null,"integralNum":0},{"searchValue":null,"createBy":null,"createTime":"2019-07-24 15:25:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":5,"userId":4,"money":0,"astype":"1","title":"发帖赠送积分","content":"","type":"2","orderNo":null,"integralNum":10},{"searchValue":null,"createBy":null,"createTime":"2019-07-24 14:18:48","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":4,"userId":4,"money":0,"astype":"1","title":"新用户注册赠送积分","content":"","type":"2","orderNo":null,"integralNum":10}]
         * balance : 20.5
         */

        private double balance;
        private int integral;
        private List<DetailedListBean> detailedList;

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public List<DetailedListBean> getDetailedList() {
            return detailedList;
        }

        public void setDetailedList(List<DetailedListBean> detailedList) {
            this.detailedList = detailedList;
        }

        public static class DetailedListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-08-21 17:19:37
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * detailedId : 309
             * userId : 4
             * money : 1.0
             * astype : 2
             * title : 购买课堂
             * content :
             * type : 2
             * orderNo : null
             * integralNum : 0
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int detailedId;
            private int userId;
            private double money;
            private String astype;
            private String title;
            private String content;
            private String type;
            private Object orderNo;
            private int integralNum;

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

            public int getDetailedId() {
                return detailedId;
            }

            public void setDetailedId(int detailedId) {
                this.detailedId = detailedId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getAstype() {
                return astype;
            }

            public void setAstype(String astype) {
                this.astype = astype;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(Object orderNo) {
                this.orderNo = orderNo;
            }

            public int getIntegralNum() {
                return integralNum;
            }

            public void setIntegralNum(int integralNum) {
                this.integralNum = integralNum;
            }

            public static class ParamsBean {
            }
        }
    }
}
