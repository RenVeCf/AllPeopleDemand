package com.ipd.allpeopledemand.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ClassRoomDetailsBean implements Parcelable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"IsPurchase":"2","roomDetails":{"classroomId":3,"roomClassId":1,"title":"测试音频文件","playNum":0,"type":"2","content":"<p>这是音频文件文件<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/acad9ed033e4e3f8bbe0fc6450662b53.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:10:47","updateTime":null,"watchNum":1,"videoUrl":"","videoFile":"","audioFile":"upload/2019/07/20/6c14abebfbd841c27f9f809f8e6fafa9.mp4","videoType":"","audioUrl":"","audioType":"1"}}
     */

    private String msg;
    private int code;
    private DataBean data;

    protected ClassRoomDetailsBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<ClassRoomDetailsBean> CREATOR = new Creator<ClassRoomDetailsBean>() {
        @Override
        public ClassRoomDetailsBean createFromParcel(Parcel in) {
            return new ClassRoomDetailsBean(in);
        }

        @Override
        public ClassRoomDetailsBean[] newArray(int size) {
            return new ClassRoomDetailsBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeInt(code);
    }

    public static class DataBean implements Parcelable{
        /**
         * IsPurchase : 2
         * roomDetails : {"classroomId":3,"roomClassId":1,"title":"测试音频文件","playNum":0,"type":"2","content":"<p>这是音频文件文件<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/acad9ed033e4e3f8bbe0fc6450662b53.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:10:47","updateTime":null,"watchNum":1,"videoUrl":"","videoFile":"","audioFile":"upload/2019/07/20/6c14abebfbd841c27f9f809f8e6fafa9.mp4","videoType":"","audioUrl":"","audioType":"1"}
         */

        private String IsPurchase;
        private double balance;
        private RoomDetailsBean roomDetails;

        protected DataBean(Parcel in) {
            IsPurchase = in.readString();
            balance = in.readDouble();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getIsPurchase() {
            return IsPurchase;
        }

        public void setIsPurchase(String IsPurchase) {
            this.IsPurchase = IsPurchase;
        }

        public RoomDetailsBean getRoomDetails() {
            return roomDetails;
        }

        public void setRoomDetails(RoomDetailsBean roomDetails) {
            this.roomDetails = roomDetails;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(IsPurchase);
            parcel.writeDouble(balance);
        }

        public static class RoomDetailsBean implements Parcelable{
            /**
             * classroomId : 3
             * roomClassId : 1
             * title : 测试音频文件
             * playNum : 0
             * type : 2
             * content : <p>这是音频文件文件</p>
             * purchaseNum : 0
             * thumbnail : upload/2019/07/20/acad9ed033e4e3f8bbe0fc6450662b53.jpg
             * status : 1
             * videoDate :
             * createTime : 2019-07-20 16:10:47
             * updateTime : null
             * watchNum : 1
             * videoUrl :
             * videoFile :
             * audioFile : upload/2019/07/20/6c14abebfbd841c27f9f809f8e6fafa9.mp4
             * videoType :
             * audioUrl :
             * audioType : 1
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

            public RoomDetailsBean() {
                super();
            }

            public RoomDetailsBean(Parcel in) {
                classroomId = in.readInt();
                roomClassId = in.readInt();
                title = in.readString();
                playNum = in.readInt();
                type = in.readString();
                content = in.readString();
                purchaseNum = in.readInt();
                thumbnail = in.readString();
                status = in.readString();
                videoDate = in.readString();
                createTime = in.readString();
                watchNum = in.readInt();
                videoUrl = in.readString();
                videoFile = in.readString();
                audioFile = in.readString();
                videoType = in.readString();
                audioUrl = in.readString();
                audioType = in.readString();
            }

            public static final Creator<RoomDetailsBean> CREATOR = new Creator<RoomDetailsBean>() {
                @Override
                public RoomDetailsBean createFromParcel(Parcel in) {
                    return new RoomDetailsBean(in);
                }

                @Override
                public RoomDetailsBean[] newArray(int size) {
                    return new RoomDetailsBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(classroomId);
                parcel.writeInt(roomClassId);
                parcel.writeString(title);
                parcel.writeInt(playNum);
                parcel.writeString(type);
                parcel.writeString(content);
                parcel.writeInt(purchaseNum);
                parcel.writeString(thumbnail);
                parcel.writeString(status);
                parcel.writeString(videoDate);
                parcel.writeString(createTime);
                parcel.writeInt(watchNum);
                parcel.writeString(videoUrl);
                parcel.writeString(videoFile);
                parcel.writeString(audioFile);
                parcel.writeString(videoType);
                parcel.writeString(audioUrl);
                parcel.writeString(audioType);
            }
        }
    }
}
