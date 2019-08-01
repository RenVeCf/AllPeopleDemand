package com.ipd.allpeopledemand.bean;

public class IsMsgBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"ureads":1}
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
         * ureads : 1
         */

        private int ureads;

        public int getUreads() {
            return ureads;
        }

        public void setUreads(int ureads) {
            this.ureads = ureads;
        }
    }
}
