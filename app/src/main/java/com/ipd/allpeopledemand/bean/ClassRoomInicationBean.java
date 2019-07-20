package com.ipd.allpeopledemand.bean;

import java.util.List;

public class ClassRoomInicationBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"classList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:07:03","updateBy":null,"updateTime":null,"remark":null,"params":{},"roomClassId":2,"className":"免费","sort":2,"status":null,"isNot":"1"},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:07:31","updateBy":null,"updateTime":null,"remark":null,"params":{},"roomClassId":3,"className":"实用","sort":3,"status":null,"isNot":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:07:39","updateBy":null,"updateTime":null,"remark":null,"params":{},"roomClassId":4,"className":"知识","sort":4,"status":null,"isNot":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:07:44","updateBy":null,"updateTime":null,"remark":null,"params":{},"roomClassId":5,"className":"生活","sort":5,"status":null,"isNot":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:07:58","updateBy":null,"updateTime":null,"remark":null,"params":{},"roomClassId":6,"className":"娱乐","sort":6,"status":null,"isNot":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-07-14 23:08:05","updateBy":null,"updateTime":"2019-07-14 23:10:25","remark":null,"params":{},"roomClassId":7,"className":"其他","sort":7,"status":null,"isNot":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-07-18 16:19:01","updateBy":null,"updateTime":"2019-07-18 16:19:03","remark":null,"params":{},"roomClassId":8,"className":"推荐","sort":8,"status":null,"isNot":"1"}]}
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
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-14 23:07:03
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * roomClassId : 2
             * className : 免费
             * sort : 2
             * status : null
             * isNot : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int roomClassId;
            private String className;
            private int sort;
            private Object status;
            private String isNot;

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

            public int getRoomClassId() {
                return roomClassId;
            }

            public void setRoomClassId(int roomClassId) {
                this.roomClassId = roomClassId;
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

            public static class ParamsBean {
            }
        }
    }
}
