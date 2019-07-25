package com.ipd.allpeopledemand.bean;

public class CheckVersionBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"version":{"searchValue":null,"createBy":null,"createTime":"2019-06-25 11:53:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"versionId":1,"versionNo":"1.0","type":"1","intro":"安卓第一个版本\n","isNews":"1"}}
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
         * version : {"searchValue":null,"createBy":null,"createTime":"2019-06-25 11:53:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"versionId":1,"versionNo":"1.0","type":"1","intro":"安卓第一个版本\n","isNews":"1"}
         */

        private VersionBean version;

        public VersionBean getVersion() {
            return version;
        }

        public void setVersion(VersionBean version) {
            this.version = version;
        }

        public static class VersionBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-06-25 11:53:24
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * versionId : 1
             * versionNo : 1.0
             * type : 1
             * intro : 安卓第一个版本
             * isNews : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int versionId;
            private String versionNo;
            private String type;
            private String intro;
            private String isNews;

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

            public int getVersionId() {
                return versionId;
            }

            public void setVersionId(int versionId) {
                this.versionId = versionId;
            }

            public String getVersionNo() {
                return versionNo;
            }

            public void setVersionNo(String versionNo) {
                this.versionNo = versionNo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getIsNews() {
                return isNews;
            }

            public void setIsNews(String isNews) {
                this.isNews = isNews;
            }

            public static class ParamsBean {
            }
        }
    }
}
