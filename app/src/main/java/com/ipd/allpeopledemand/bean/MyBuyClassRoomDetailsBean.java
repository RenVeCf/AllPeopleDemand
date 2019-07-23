package com.ipd.allpeopledemand.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MyBuyClassRoomDetailsBean implements Parcelable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"price":{"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"},"roomDetails":{"classroomId":6,"roomClassId":1,"title":"1111","playNum":0,"type":"3","content":"<p>111<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/4a062a291ce89984a20eae94163dfe34.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:57:08","updateTime":null,"watchNum":5,"videoUrl":"1111","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null}}
     */

    private String msg;
    private int code;
    private DataBean data;

    protected MyBuyClassRoomDetailsBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<MyBuyClassRoomDetailsBean> CREATOR = new Creator<MyBuyClassRoomDetailsBean>() {
        @Override
        public MyBuyClassRoomDetailsBean createFromParcel(Parcel in) {
            return new MyBuyClassRoomDetailsBean(in);
        }

        @Override
        public MyBuyClassRoomDetailsBean[] newArray(int size) {
            return new MyBuyClassRoomDetailsBean[size];
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
         * price : {"searchValue":null,"createBy":null,"createTime":"2019-07-14 13:53:06","updateBy":null,"updateTime":"2019-07-14 13:53:02","remark":null,"params":{},"priceId":1,"title":null,"integral":18,"money":10,"type":"1"}
         * roomDetails : {"classroomId":6,"roomClassId":1,"title":"1111","playNum":0,"type":"3","content":"<p>111<\/p>","purchaseNum":0,"thumbnail":"upload/2019/07/20/4a062a291ce89984a20eae94163dfe34.jpg","status":"1","videoDate":"","createTime":"2019-07-20 16:57:08","updateTime":null,"watchNum":5,"videoUrl":"1111","videoFile":"","audioFile":"","videoType":"2","audioUrl":"","audioType":"","orderId":null,"payStatus":null,"userId":null}
         */

        private PriceBean price;
        private RoomDetailsBean roomDetails;

        protected DataBean(Parcel in) {
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

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
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
        }

        public static class PriceBean implements Parcelable{
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

            protected PriceBean(Parcel in) {
                createTime = in.readString();
                updateTime = in.readString();
                priceId = in.readInt();
                integral = in.readInt();
                money = in.readDouble();
                type = in.readString();
            }

            public static final Creator<PriceBean> CREATOR = new Creator<PriceBean>() {
                @Override
                public PriceBean createFromParcel(Parcel in) {
                    return new PriceBean(in);
                }

                @Override
                public PriceBean[] newArray(int size) {
                    return new PriceBean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(createTime);
                parcel.writeString(updateTime);
                parcel.writeInt(priceId);
                parcel.writeInt(integral);
                parcel.writeDouble(money);
                parcel.writeString(type);
            }

            public static class ParamsBean implements Parcelable{
                protected ParamsBean(Parcel in) {
                }

                public static final Creator<ParamsBean> CREATOR = new Creator<ParamsBean>() {
                    @Override
                    public ParamsBean createFromParcel(Parcel in) {
                        return new ParamsBean(in);
                    }

                    @Override
                    public ParamsBean[] newArray(int size) {
                        return new ParamsBean[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                }
            }
        }

        public static class RoomDetailsBean implements Parcelable{
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
             * watchNum : 5
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

            protected RoomDetailsBean(Parcel in) {
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
