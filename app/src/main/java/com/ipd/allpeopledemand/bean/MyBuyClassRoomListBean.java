package com.ipd.allpeopledemand.bean;

import java.util.List;

public class MyBuyClassRoomListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"price":{"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"},"roomList":[{"classroomId":6,"roomClassId":1,"title":"1111","playNum":0,"type":"3","content":"<p>111<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/4a062a291ce89984a20eae94163dfe34.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:57:08","updateTime":null,"watchNum":4,"videoUrl":"1111","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null}]}
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
         * roomList : [{"classroomId":6,"roomClassId":1,"title":"1111","playNum":0,"type":"3","content":"<p>111<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/4a062a291ce89984a20eae94163dfe34.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:57:08","updateTime":null,"watchNum":4,"videoUrl":"1111","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null}]
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
             * classroomId : 6
             * roomClassId : 1
             * title : 1111
             * playNum : 0
             * type : 3
             * content : <p>111</p>
             * purchaseNum : 0
             * thumbnail : upload/2019/07/20/4a062a291ce89984a20eae94163dfe34.jpg
             * status : 1
             * videoDate :
             * createTime : 2019-07-20 16:57:08
             * updateTime : null
             * watchNum : 4
             * videoUrl : 1111
             * videoFile :
             * audioFile :
             * videoType : 2
             * audioUrl :
             * audioType :
             * orderId : null
             * payStatus : null
             * userId : null
             */

            private int classroomId;
            private int roomClassId;
            private String title;
            private int playNum;
            private String type;
            private String content;
            private int purchaseNum;
            private String thumbnail;
            private String status;
            private String videoDate;
            private String createTime;
            private Object updateTime;
            private int watchNum;
            private String videoUrl;
            private String videoFile;
            private String audioFile;
            private String videoType;
            private String audioUrl;
            private String audioType;
            private Object orderId;
            private Object payStatus;
            private Object userId;
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

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
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

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getWatchNum() {
                return watchNum;
            }

            public void setWatchNum(int watchNum) {
                this.watchNum = watchNum;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public String getVideoFile() {
                return videoFile;
            }

            public void setVideoFile(String videoFile) {
                this.videoFile = videoFile;
            }

            public String getAudioFile() {
                return audioFile;
            }

            public void setAudioFile(String audioFile) {
                this.audioFile = audioFile;
            }

            public String getVideoType() {
                return videoType;
            }

            public void setVideoType(String videoType) {
                this.videoType = videoType;
            }

            public String getAudioUrl() {
                return audioUrl;
            }

            public void setAudioUrl(String audioUrl) {
                this.audioUrl = audioUrl;
            }

            public String getAudioType() {
                return audioType;
            }

            public void setAudioType(String audioType) {
                this.audioType = audioType;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public Object getPayStatus() {
                return payStatus;
            }

            public void setPayStatus(Object payStatus) {
                this.payStatus = payStatus;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }
        }
    }
}
