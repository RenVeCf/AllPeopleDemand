package com.ipd.allpeopledemand.bean;

import java.util.List;

public class ClassIficationBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"classList":[{"releaseClassId":1,"className":"推荐","sort":1,"status":null,"isNot":null,"createTime":"2019-07-14 22:53:07","updateTime":null},{"releaseClassId":2,"className":"附近","sort":2,"status":"1","isNot":null,"createTime":"2019-07-14 22:58:16","updateTime":null},{"releaseClassId":3,"className":"招聘","sort":3,"status":"1","isNot":null,"createTime":"2019-07-14 22:58:27","updateTime":null},{"releaseClassId":4,"className":"交友","sort":4,"status":"1","isNot":null,"createTime":"2019-07-14 22:58:34","updateTime":null},{"releaseClassId":5,"className":"二手","sort":5,"status":"1","isNot":null,"createTime":"2019-07-14 22:58:44","updateTime":null},{"releaseClassId":6,"className":"租房","sort":6,"status":"1","isNot":null,"createTime":"2019-07-14 22:58:48","updateTime":"2019-07-14 23:02:09"},{"releaseClassId":7,"className":"其他","sort":7,"status":"1","isNot":null,"createTime":"2019-07-14 23:02:30","updateTime":"2019-07-14 23:02:33"}]}
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
        private List<ClassListBean> classList;

        public List<ClassListBean> getClassList() {
            return classList;
        }

        public void setClassList(List<ClassListBean> classList) {
            this.classList = classList;
        }

        public static class ClassListBean {
            /**
             * releaseClassId : 1
             * className : 推荐
             * sort : 1
             * status : null
             * isNot : "2"
             * createTime : 2019-07-14 22:53:07
             * updateTime : null
             */

            private int releaseClassId;
            private String className;
            private int sort;
            private Object status;
            private String isNot;
            private String createTime;
            private Object updateTime;

            public int getReleaseClassId() {
                return releaseClassId;
            }

            public void setReleaseClassId(int releaseClassId) {
                this.releaseClassId = releaseClassId;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getIsNot() {
                return isNot;
            }

            public void setIsNot(String isNot) {
                this.isNot = isNot;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
