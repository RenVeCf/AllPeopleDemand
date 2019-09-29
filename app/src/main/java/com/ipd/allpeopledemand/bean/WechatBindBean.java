package com.ipd.allpeopledemand.bean;

public class WechatBindBean {
    /**
     * access_token : 25_IDdGeGzTLAYERLW0hbwuQVhodn2qPxv55T8jITbjD4DuHNcj9KzqUpekgLXErq5UlisFe03bBz7_J7tcVz2md3pQpYfP8_hQp75T7WIRUok
     * expires_in : 7200
     * refresh_token : 25_Zs_9qQQ2m9vB2Cz1PFjKOksr5O5TvtLrN70f1_TRo2WDS_uhBXeWsUaFWSlvXnONY2FtikohWhytVZ0BhfP2yjyOlFhM76QHcAa8BcPzZHc
     * openid : oxl5twq8EuYxlI6d8Iuqj0Na52Ow
     * scope : snsapi_userinfo
     * unionid : orJ6A1eJrxwTK2ry87wUX5N0-Kpo
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
