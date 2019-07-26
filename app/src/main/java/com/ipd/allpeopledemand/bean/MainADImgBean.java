package com.ipd.allpeopledemand.bean;

public class MainADImgBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"upadvert":{"searchValue":null,"createBy":null,"createTime":"2019-07-23 17:11:42","updateBy":null,"updateTime":"2019-07-23 17:11:44","remark":null,"params":{},"upadvertId":1,"picPath":"upload/2019/07/25/1a18d0d18e501ecdc6c0deef1c077087.jpg","title":"标题","status":"1"}}
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
         * upadvert : {"searchValue":null,"createBy":null,"createTime":"2019-07-23 17:11:42","updateBy":null,"updateTime":"2019-07-23 17:11:44","remark":null,"params":{},"upadvertId":1,"picPath":"upload/2019/07/25/1a18d0d18e501ecdc6c0deef1c077087.jpg","title":"标题","status":"1"}
         */

        private UpadvertBean upadvert;

        public UpadvertBean getUpadvert() {
            return upadvert;
        }

        public void setUpadvert(UpadvertBean upadvert) {
            this.upadvert = upadvert;
        }

        public static class UpadvertBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-23 17:11:42
             * updateBy : null
             * updateTime : 2019-07-23 17:11:44
             * remark : null
             * params : {}
             * upadvertId : 1
             * picPath : upload/2019/07/25/1a18d0d18e501ecdc6c0deef1c077087.jpg
             * title : 标题
             * status : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsBean params;
            private int upadvertId;
            private String picPath;
            private String title;
            private String status;

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

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
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

            public int getUpadvertId() {
                return upadvertId;
            }

            public void setUpadvertId(int upadvertId) {
                this.upadvertId = upadvertId;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public static class ParamsBean {
            }
        }
    }
}
