package com.ipd.allpeopledemand.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class LoadingBean implements Parcelable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"guidePageList":[{"guidePageId":9,"title":"第一张图","picPath":"upload/2019/07/25/133c4b0b45fc11d2bb2fca35f750814e.jpg","status":"1","sort":9,"createTime":"2019-07-25 13:27:26","updateTime":null},{"guidePageId":10,"title":"第二张图","picPath":"upload/2019/07/25/1a18d0d18e501ecdc6c0deef1c077087.jpg","status":"1","sort":10,"createTime":"2019-07-25 13:27:39","updateTime":null},{"guidePageId":11,"title":"第三张","picPath":"upload/2019/07/25/ba673f8c8261e2cb40166a74742a5013.jpg","status":"1","sort":11,"createTime":"2019-07-25 13:27:52","updateTime":null}]}
     */

    private String msg;
    private int code;
    private DataBean data;

    protected LoadingBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<LoadingBean> CREATOR = new Creator<LoadingBean>() {
        @Override
        public LoadingBean createFromParcel(Parcel in) {
            return new LoadingBean(in);
        }

        @Override
        public LoadingBean[] newArray(int size) {
            return new LoadingBean[size];
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
        private List<GuidePageListBean> guidePageList;

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

        public List<GuidePageListBean> getGuidePageList() {
            return guidePageList;
        }

        public void setGuidePageList(List<GuidePageListBean> guidePageList) {
            this.guidePageList = guidePageList;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class GuidePageListBean implements Parcelable{
            /**
             * guidePageId : 9
             * title : 第一张图
             * picPath : upload/2019/07/25/133c4b0b45fc11d2bb2fca35f750814e.jpg
             * status : 1
             * sort : 9
             * createTime : 2019-07-25 13:27:26
             * updateTime : null
             */

            private int guidePageId;
            private String title;
            private String picPath;
            private String status;
            private int sort;
            private String createTime;
            private Object updateTime;

            protected GuidePageListBean(Parcel in) {
                guidePageId = in.readInt();
                title = in.readString();
                picPath = in.readString();
                status = in.readString();
                sort = in.readInt();
                createTime = in.readString();
            }

            public static final Creator<GuidePageListBean> CREATOR = new Creator<GuidePageListBean>() {
                @Override
                public GuidePageListBean createFromParcel(Parcel in) {
                    return new GuidePageListBean(in);
                }

                @Override
                public GuidePageListBean[] newArray(int size) {
                    return new GuidePageListBean[size];
                }
            };

            public int getGuidePageId() {
                return guidePageId;
            }

            public void setGuidePageId(int guidePageId) {
                this.guidePageId = guidePageId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(guidePageId);
                parcel.writeString(title);
                parcel.writeString(picPath);
                parcel.writeString(status);
                parcel.writeInt(sort);
                parcel.writeString(createTime);
            }
        }
    }
}
