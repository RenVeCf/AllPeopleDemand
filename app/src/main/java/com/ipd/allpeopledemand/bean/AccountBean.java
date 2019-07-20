package com.ipd.allpeopledemand.bean;

import java.util.List;

public class AccountBean {
    /**
     * total : 1
     * data : {"detailedList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-15 15:50:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":2,"userId":1,"money":0,"astype":"1","title":"测试积分数据","content":null,"type":"1","orderNo":null,"integralNum":100}],"national_num":100}
     * code : 200
     * msg : 操作成功
     */

    private int total;
    private DataBean data;
    private int code;
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * detailedList : [{"searchValue":null,"createBy":null,"createTime":"2019-07-15 15:50:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"detailedId":2,"userId":1,"money":0,"astype":"1","title":"测试积分数据","content":null,"type":"1","orderNo":null,"integralNum":100}]
         * national_num : 100
         */

        private int national_num;
        private double balance;
        private List<DetailedListBean> detailedList;

        public int getNational_num() {
            return national_num;
        }

        public void setNational_num(int national_num) {
            this.national_num = national_num;
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
             * createTime : 2019-07-15 15:50:12
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * detailedId : 2
             * userId : 1
             * money : 0
             * astype : 1
             * title : 测试积分数据
             * content : null
             * type : 1
             * orderNo : null
             * integralNum : 100
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
            private int money;
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

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
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
