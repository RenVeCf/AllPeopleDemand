package com.ipd.allpeopledemand.bean;

public class UploadImgBean {
    /**
     * msg : 操作成功
     * fileName : upload/2019/07/16/5d3f4e36bbaf6c9a3659e6ee78161fdb.jpeg
     * code : 200
     */

    private String msg;
    private String fileName;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
