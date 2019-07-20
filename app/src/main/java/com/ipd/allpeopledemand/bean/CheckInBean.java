package com.ipd.allpeopledemand.bean;

public class CheckInBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"continueDays":1}
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
         * continueDays : 1
         */

        private int continueDays;

        public int getContinueDays() {
            return continueDays;
        }

        public void setContinueDays(int continueDays) {
            this.continueDays = continueDays;
        }
    }
}
