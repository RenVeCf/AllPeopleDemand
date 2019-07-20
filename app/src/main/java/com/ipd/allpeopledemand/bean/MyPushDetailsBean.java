package com.ipd.allpeopledemand.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MyPushDetailsBean implements Parcelable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"release":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":9,"releaseClassId":5,"title":"么哒","region":"上海市","contacts":"我的","contactNumber":"18502994087","picPath":"upload/2019/07/17/43e2c4261fd80fe7490de3a55e6dadc0.jpeg","details":"夜班","releaseTime":null,"browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":11,"keyword":"呢饿","isFollow":1,"userCall":"aaaajjjj","avatar":"默认头像","className":null,"followId":null}}
     */

    private String msg;
    private int code;
    private DataBean data;

    protected MyPushDetailsBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<MyPushDetailsBean> CREATOR = new Creator<MyPushDetailsBean>() {
        @Override
        public MyPushDetailsBean createFromParcel(Parcel in) {
            return new MyPushDetailsBean(in);
        }

        @Override
        public MyPushDetailsBean[] newArray(int size) {
            return new MyPushDetailsBean[size];
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

    public static class DataBean implements Parcelable {
        /**
         * release : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"releaseId":9,"releaseClassId":5,"title":"么哒","region":"上海市","contacts":"我的","contactNumber":"18502994087","picPath":"upload/2019/07/17/43e2c4261fd80fe7490de3a55e6dadc0.jpeg","details":"夜班","releaseTime":null,"browseNum":0,"purchaseNum":0,"isRecommend":null,"status":"1","userId":11,"keyword":"呢饿","isFollow":1,"userCall":"aaaajjjj","avatar":"默认头像","className":null,"followId":null}
         */

        private ReleaseBean release;

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

        public ReleaseBean getRelease() {
            return release;
        }

        public void setRelease(ReleaseBean release) {
            this.release = release;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class ReleaseBean implements Parcelable {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * releaseId : 9
             * releaseClassId : 5
             * title : 么哒
             * region : 上海市
             * contacts : 我的
             * contactNumber : 18502994087
             * picPath : upload/2019/07/17/43e2c4261fd80fe7490de3a55e6dadc0.jpeg
             * details : 夜班
             * releaseTime : null
             * browseNum : 0
             * purchaseNum : 0
             * isRecommend : null
             * status : 1
             * userId : 11
             * keyword : 呢饿
             * isFollow : 1
             * userCall : aaaajjjj
             * avatar : 默认头像
             * className : null
             * followId : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int releaseId;
            private int releaseClassId;
            private String title;
            private String region;
            private String contacts;
            private String contactNumber;
            private String picPath;
            private String details;
            private String releaseTime;
            private int browseNum;
            private int purchaseNum;
            private Object isRecommend;
            private String status;
            private int userId;
            private String keyword;
            private String isFollow;
            private String userCall;
            private String avatar;
            private Object className;
            private Object followId;

            public ReleaseBean() {
                super();
            }

            public ReleaseBean(Parcel in) {
                releaseId = in.readInt();
                releaseClassId = in.readInt();
                title = in.readString();
                region = in.readString();
                contacts = in.readString();
                contactNumber = in.readString();
                picPath = in.readString();
                details = in.readString();
                browseNum = in.readInt();
                purchaseNum = in.readInt();
                status = in.readString();
                userId = in.readInt();
                keyword = in.readString();
                isFollow = in.readString();
                userCall = in.readString();
                avatar = in.readString();
            }

            public static final Creator<ReleaseBean> CREATOR = new Creator<ReleaseBean>() {
                @Override
                public ReleaseBean createFromParcel(Parcel in) {
                    return new ReleaseBean(in);
                }

                @Override
                public ReleaseBean[] newArray(int size) {
                    return new ReleaseBean[size];
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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public int getReleaseId() {
                return releaseId;
            }

            public void setReleaseId(int releaseId) {
                this.releaseId = releaseId;
            }

            public int getReleaseClassId() {
                return releaseClassId;
            }

            public void setReleaseClassId(int releaseClassId) {
                this.releaseClassId = releaseClassId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getContacts() {
                return contacts;
            }

            public void setContacts(String contacts) {
                this.contacts = contacts;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public String getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(String releaseTime) {
                this.releaseTime = releaseTime;
            }

            public int getBrowseNum() {
                return browseNum;
            }

            public void setBrowseNum(int browseNum) {
                this.browseNum = browseNum;
            }

            public int getPurchaseNum() {
                return purchaseNum;
            }

            public void setPurchaseNum(int purchaseNum) {
                this.purchaseNum = purchaseNum;
            }

            public Object getIsRecommend() {
                return isRecommend;
            }

            public void setIsRecommend(Object isRecommend) {
                this.isRecommend = isRecommend;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(String isFollow) {
                this.isFollow = isFollow;
            }

            public String getUserCall() {
                return userCall;
            }

            public void setUserCall(String userCall) {
                this.userCall = userCall;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public Object getClassName() {
                return className;
            }

            public void setClassName(Object className) {
                this.className = className;
            }

            public Object getFollowId() {
                return followId;
            }

            public void setFollowId(Object followId) {
                this.followId = followId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(releaseId);
                parcel.writeInt(releaseClassId);
                parcel.writeString(title);
                parcel.writeString(region);
                parcel.writeString(contacts);
                parcel.writeString(contactNumber);
                parcel.writeString(picPath);
                parcel.writeString(details);
                parcel.writeInt(browseNum);
                parcel.writeInt(purchaseNum);
                parcel.writeString(status);
                parcel.writeInt(userId);
                parcel.writeString(keyword);
                parcel.writeString(isFollow);
                parcel.writeString(userCall);
                parcel.writeString(avatar);
            }

            public static class ParamsBean implements Parcelable {
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
    }
}
