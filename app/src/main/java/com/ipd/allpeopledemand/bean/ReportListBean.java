package com.ipd.allpeopledemand.bean;

import java.util.List;

public class ReportListBean {
    /**
     * msg : 操作成功
     * total : 8
     * code : 200
     * data : {"reportList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-15 23:32:15","updateBy":null,"updateTime":"2019-07-15 23:32:16","remark":null,"params":{},"reportId":1,"title":"违法违规","sort":"1"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":2,"title":"色情低俗","sort":"2"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":3,"title":"垃圾广告、卖假冒伪劣产品内容不适合孩子看","sort":"3"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":4,"title":"未成年不适当行为","sort":"4"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":5,"title":"标题党、封面党、骗关注","sort":"5"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":6,"title":"盗用作品","sort":"6"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":7,"title":"泄露我的隐私","sort":"7"},{"searchValue":null,"createBy":null,"createTime":"2019-02-01 00:00:00","updateBy":null,"updateTime":null,"remark":null,"params":{},"reportId":8,"title":"其他","sort":"8"}]}
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
        private List<ReportListBeans> reportList;

        public List<ReportListBeans> getReportList() {
            return reportList;
        }

        public void setReportList(List<ReportListBeans> reportList) {
            this.reportList = reportList;
        }

        public static class ReportListBeans {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-15 23:32:15
             * updateBy : null
             * updateTime : 2019-07-15 23:32:16
             * remark : null
             * params : {}
             * reportId : 1
             * title : 违法违规
             * sort : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsBean params;
            private int reportId;
            private String title;
            private String sort;

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

            public int getReportId() {
                return reportId;
            }

            public void setReportId(int reportId) {
                this.reportId = reportId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public static class ParamsBean {
            }
        }
    }
}
