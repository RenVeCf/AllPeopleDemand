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
     * total : 3
     * code : 200
     * data : {"roomList":[{"classroomId":3,"roomClassId":2,"title":"掌握了这25个叠衣服和整理衣柜的生活窍门，你的妈妈一定会夸你","playNum":0,"type":"3","content":"<p>掌握了这25个叠衣服和整理衣柜的生活窍门，你的妈妈一定会夸你<\/p>","purchaseNum":0,"thumbnail":"upload/2019/08/01/10cb466e19f686c5c7a5399d7eacd7b4.png","status":"1","videoDate":"","createTime":"2019-08-01 14:08:55","updateTime":null,"watchNum":35,"videoUrl":"http://player.youku.com/embed/XNDEzODgyODc4OA==","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null,"money":10,"integral":10},{"classroomId":2,"roomClassId":2,"title":"爆笑恶作剧，创意DIY食用的生活窍门，海绵小妙招你肯定不知道","playNum":0,"type":"3","content":"<p>爆笑恶作剧，创意DIY食用的生活窍门，海绵小妙招你肯定不知道<\/p>","purchaseNum":0,"thumbnail":"upload/2019/08/01/4774325b8d38c88fb38098d1cdb28d7f.png","status":"1","videoDate":"","createTime":"2019-08-01 14:01:05","updateTime":null,"watchNum":58,"videoUrl":"http://player.youku.com/embed/XNDI3NjI3MDUwNA==","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null,"money":10,"integral":10},{"classroomId":1,"roomClassId":2,"title":"十分钟快速发面的窍门","playNum":0,"type":"3","content":"<p>十分钟快速发面的窍门<\/p>","purchaseNum":0,"thumbnail":"upload/2019/08/01/edf2cb2c01f789a1906ff978e0009a79.png","status":"1","videoDate":"","createTime":"2019-08-01 11:43:44","updateTime":"2019-08-01 13:42:15","watchNum":69,"videoUrl":"http://player.youku.com/embed/XNDI2MDk3ODgwNA==","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null,"money":10,"integral":10}]}
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
        private List<RoomListBean> roomList;

        public List<RoomListBean> getRoomList() {
            return roomList;
        }

        public void setRoomList(List<RoomListBean> roomList) {
            this.roomList = roomList;
        }

        public static class RoomListBean {
            /**
             * classroomId : 3
             * roomClassId : 2
             * title : 掌握了这25个叠衣服和整理衣柜的生活窍门，你的妈妈一定会夸你
             * playNum : 0
             * type : 3
             * content : <p>掌握了这25个叠衣服和整理衣柜的生活窍门，你的妈妈一定会夸你</p>
             * purchaseNum : 0
             * thumbnail : upload/2019/08/01/10cb466e19f686c5c7a5399d7eacd7b4.png
             * status : 1
             * videoDate :
             * createTime : 2019-08-01 14:08:55
             * updateTime : null
             * watchNum : 35
             * videoUrl : http://player.youku.com/embed/XNDEzODgyODc4OA==
             * videoFile :
             * audioFile :
             * videoType : 2
             * audioUrl :
             * audioType :
             * orderId : null
             * payStatus : null
             * userId : null
             * money : 10.0
             * integral : 10
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
            private double money;
            private int integral;

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

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }
        }
    }
}
