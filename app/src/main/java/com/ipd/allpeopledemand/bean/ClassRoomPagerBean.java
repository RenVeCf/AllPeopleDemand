package com.ipd.allpeopledemand.bean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class ClassRoomPagerBean {
    /**
     * msg : 操作成功
     * total : 2
     * code : 200
     * data : {"price":{"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"},"roomList":[{"classroomId":1,"roomClassId":2,"title":null,"playNum":1,"type":"1","content":"1","purchaseNum":1,"thumbnail":null,"status":null,"videoDate":null,"createTime":"2019-07-12 11:17:04","updateTime":"2019-07-12 11:17:05","watch_num":null,"video_url":null,"video_file":null,"audio_file":null,"video_type":null},{"classroomId":2,"roomClassId":2,"title":null,"playNum":2,"type":"1","content":"1","purchaseNum":1,"thumbnail":null,"status":null,"videoDate":null,"createTime":"2019-07-12 11:22:51","updateTime":null,"watch_num":null,"video_url":null,"video_file":null,"audio_file":null,"video_type":null}]}
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
         * price : {"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"}
         * roomList : [{"classroomId":1,"roomClassId":2,"title":null,"playNum":1,"type":"1","content":"1","purchaseNum":1,"thumbnail":null,"status":null,"videoDate":null,"createTime":"2019-07-12 11:17:04","updateTime":"2019-07-12 11:17:05","watch_num":null,"video_url":null,"video_file":null,"audio_file":null,"video_type":null},{"classroomId":2,"roomClassId":2,"title":null,"playNum":2,"type":"1","content":"1","purchaseNum":1,"thumbnail":null,"status":null,"videoDate":null,"createTime":"2019-07-12 11:22:51","updateTime":null,"watch_num":null,"video_url":null,"video_file":null,"audio_file":null,"video_type":null}]
         */

        private PriceBean price;
        private List<RoomListBean> roomList;

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
        }

        public List<RoomListBean> getRoomList() {
            return roomList;
        }

        public void setRoomList(List<RoomListBean> roomList) {
            this.roomList = roomList;
        }

        public static class PriceBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-07-14 13:53:06
             * updateBy : null
             * updateTime : 2019-07-14 13:53:02
             * remark : null
             * params : {}
             * priceId : 1
             * title : null
             * integral : 18
             * money : 10.0
             * type : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private String updateTime;
            private Object remark;
            private ParamsBean params;
            private int priceId;
            private Object title;
            private int integral;
            private double money;
            private String type;

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

            public int getPriceId() {
                return priceId;
            }

            public void setPriceId(int priceId) {
                this.priceId = priceId;
            }

            public Object getTitle() {
                return title;
            }

            public void setTitle(Object title) {
                this.title = title;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class ParamsBean {
            }
        }

        public static class RoomListBean {
            /**
             * classroomId : 1
             * roomClassId : 2
             * title : null
             * playNum : 1
             * type : 1
             * content : 1
             * purchaseNum : 1
             * thumbnail : null
             * status : null
             * videoDate : null
             * createTime : 2019-07-12 11:17:04
             * updateTime : 2019-07-12 11:17:05
             * watch_num : null
             * video_url : null
             * video_file : null
             * audio_file : null
             * video_type : null
             * integral : 10
             * money : 18.0
             */

            private int classroomId;
            private int roomClassId;
            private String title;
            private int playNum;
            private String type;
            private String content;
            private int purchaseNum;
            private Object thumbnail;
            private Object status;
            private String videoDate;
            private String createTime;
            private String updateTime;
            private int watchNum;
            private Object video_url;
            private Object video_file;
            private Object audio_file;
            private Object video_type;
            private int integral;
            private double money;

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getClassroomId() {
                return classroomId;
            }

            public void setClassroomId(int classroomId) {
                this.classroomId = classroomId;
            }

            public int getRoomClassId() {
                return roomClassId;
            }

            public void setRoomClassId(int roomClassId) {
                this.roomClassId = roomClassId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getPlayNum() {
                return playNum;
            }

            public void setPlayNum(int playNum) {
                this.playNum = playNum;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPurchaseNum() {
                return purchaseNum;
            }

            public void setPurchaseNum(int purchaseNum) {
                this.purchaseNum = purchaseNum;
            }

            public Object getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Object thumbnail) {
                this.thumbnail = thumbnail;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public String getVideoDate() {
                return videoDate;
            }

            public void setVideoDate(String videoDate) {
                this.videoDate = videoDate;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getWatchNum() {
                return watchNum;
            }

            public void setWatchNum(int watchNum) {
                this.watchNum = watchNum;
            }

            public Object getVideo_url() {
                return video_url;
            }

            public void setVideo_url(Object video_url) {
                this.video_url = video_url;
            }

            public Object getVideo_file() {
                return video_file;
            }

            public void setVideo_file(Object video_file) {
                this.video_file = video_file;
            }

            public Object getAudio_file() {
                return audio_file;
            }

            public void setAudio_file(Object audio_file) {
                this.audio_file = audio_file;
            }

            public Object getVideo_type() {
                return video_type;
            }

            public void setVideo_type(Object video_type) {
                this.video_type = video_type;
            }
        }
    }
}
