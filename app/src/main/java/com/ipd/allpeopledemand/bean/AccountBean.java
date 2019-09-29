package com.ipd.allpeopledemand.bean;

import java.util.List;

public class AccountBean {
    /**
     * msg : 操作成功
     * total : 3
     * code : 200
     * data : {"balance":294,"balanceList":[{"balanceId":24,"userId":4,"category":2,"title":"提现","balanceMoney":200,"status":2,"withdrawId":1,"createtime":"2019-09-29 14:34:45"},{"balanceId":23,"userId":4,"category":2,"title":"购买置顶服务","balanceMoney":3,"status":0,"withdrawId":0,"createtime":"2019-09-29 11:57:00"},{"balanceId":22,"userId":4,"category":2,"title":"购买置顶服务","balanceMoney":3,"status":0,"withdrawId":0,"createtime":"2019-09-29 11:55:45"}]}
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
         * balance : 294.0
         * balanceList : [{"balanceId":24,"userId":4,"category":2,"title":"提现","balanceMoney":200,"status":2,"withdrawId":1,"createtime":"2019-09-29 14:34:45"},{"balanceId":23,"userId":4,"category":2,"title":"购买置顶服务","balanceMoney":3,"status":0,"withdrawId":0,"createtime":"2019-09-29 11:57:00"},{"balanceId":22,"userId":4,"category":2,"title":"购买置顶服务","balanceMoney":3,"status":0,"withdrawId":0,"createtime":"2019-09-29 11:55:45"}]
         */

        private double balance;
        private List<BalanceListBean> balanceList;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public List<BalanceListBean> getBalanceList() {
            return balanceList;
        }

        public void setBalanceList(List<BalanceListBean> balanceList) {
            this.balanceList = balanceList;
        }

        public static class BalanceListBean {
            /**
             * balanceId : 24
             * userId : 4
             * category : 2
             * title : 提现
             * balanceMoney : 200.0
             * status : 2
             * withdrawId : 1
             * createtime : 2019-09-29 14:34:45
             */

            private int balanceId;
            private int userId;
            private int category;
            private String title;
            private double balanceMoney;
            private int status;
            private int withdrawId;
            private String createtime;

            public int getBalanceId() {
                return balanceId;
            }

            public void setBalanceId(int balanceId) {
                this.balanceId = balanceId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public double getBalanceMoney() {
                return balanceMoney;
            }

            public void setBalanceMoney(double balanceMoney) {
                this.balanceMoney = balanceMoney;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getWithdrawId() {
                return withdrawId;
            }

            public void setWithdrawId(int withdrawId) {
                this.withdrawId = withdrawId;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }
        }
    }
}
