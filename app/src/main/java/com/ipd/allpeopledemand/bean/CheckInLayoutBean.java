package com.ipd.allpeopledemand.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CheckInLayoutBean implements Parcelable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"signList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":1,"userId":16,"signDate":"2019-07-15 01:00:09"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":2,"userId":16,"signDate":"2019-07-14 11:28:43"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":3,"userId":16,"signDate":"2019-07-16 11:29:07"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":4,"userId":16,"signDate":"2019-07-17 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":5,"userId":16,"signDate":"2019-07-01 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":6,"userId":16,"signDate":"2019-07-02 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":7,"userId":16,"signDate":"2019-07-03 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":8,"userId":16,"signDate":"2019-07-04 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":9,"userId":16,"signDate":"2019-07-05 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":10,"userId":16,"signDate":"2019-07-06 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":11,"userId":16,"signDate":"2019-07-07 17:37:04"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":12,"userId":16,"signDate":"2019-07-08 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":13,"userId":16,"signDate":"2019-07-09 11:29:18"}],"continueDays":0,"isSign":true}
     */

    private String msg;
    private int code;
    private DataBean data;

    public CheckInLayoutBean() {
        super();
    }

    public CheckInLayoutBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<CheckInLayoutBean> CREATOR = new Creator<CheckInLayoutBean>() {
        @Override
        public CheckInLayoutBean createFromParcel(Parcel in) {
            return new CheckInLayoutBean(in);
        }

        @Override
        public CheckInLayoutBean[] newArray(int size) {
            return new CheckInLayoutBean[size];
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
         * signList : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":1,"userId":16,"signDate":"2019-07-15 01:00:09"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":2,"userId":16,"signDate":"2019-07-14 11:28:43"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":3,"userId":16,"signDate":"2019-07-16 11:29:07"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":4,"userId":16,"signDate":"2019-07-17 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":5,"userId":16,"signDate":"2019-07-01 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":6,"userId":16,"signDate":"2019-07-02 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":7,"userId":16,"signDate":"2019-07-03 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":8,"userId":16,"signDate":"2019-07-04 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":9,"userId":16,"signDate":"2019-07-05 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":10,"userId":16,"signDate":"2019-07-06 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":11,"userId":16,"signDate":"2019-07-07 17:37:04"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":12,"userId":16,"signDate":"2019-07-08 11:29:18"},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"signDetailId":13,"userId":16,"signDate":"2019-07-09 11:29:18"}]
         * continueDays : 0
         * isSign : true
         */

        private int continueDays;
        private String isSign;
        private List<SignListBean> signList;

        public DataBean(Parcel in) {
            continueDays = in.readInt();
            isSign = in.readString();
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

        public DataBean() {
            super();
        }

        public int getContinueDays() {
            return continueDays;
        }

        public void setContinueDays(int continueDays) {
            this.continueDays = continueDays;
        }

        public String isIsSign() {
            return isSign;
        }

        public void setIsSign(String isSign) {
            this.isSign = isSign;
        }

        public List<SignListBean> getSignList() {
            return signList;
        }

        public void setSignList(List<SignListBean> signList) {
            this.signList = signList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(continueDays);
            parcel.writeString(isSign);
        }

        public static class SignListBean implements Parcelable {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * signDetailId : 1
             * userId : 16
             * signDate : 2019-07-15 01:00:09
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int signDetailId;
            private int userId;
            private String signDate;

            protected SignListBean(Parcel in) {
                signDetailId = in.readInt();
                userId = in.readInt();
                signDate = in.readString();
            }

            public static final Creator<SignListBean> CREATOR = new Creator<SignListBean>() {
                @Override
                public SignListBean createFromParcel(Parcel in) {
                    return new SignListBean(in);
                }

                @Override
                public SignListBean[] newArray(int size) {
                    return new SignListBean[size];
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

            public int getSignDetailId() {
                return signDetailId;
            }

            public void setSignDetailId(int signDetailId) {
                this.signDetailId = signDetailId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getSignDate() {
                return signDate;
            }

            public void setSignDate(String signDate) {
                this.signDate = signDate;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(signDetailId);
                parcel.writeInt(userId);
                parcel.writeString(signDate);
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
