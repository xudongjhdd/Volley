package com.demo.volley.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xudong on 2016/5/21 0021.
 */
public class LoginBean implements Parcelable {

    /**
     * uid : 2
     * ico : null
     * userName : null
     * accessToken : f1fa31c940f4abea00ded136fb4c0ca1
     * nickName : null
     */

    public static final String UID = "uid";
    public static final String ICO = "ico";
    public static final String USERNAME = "userName";
    public static final String NICKNAME = "nickName";
    public static final String ACCESSTOKEN = "accessToken";
    public static final String BINDACTION = "bindAction";
    public static final String ACCOUNT = "account";//用户登录的帐号


    private long uid;
    private String ico;
    private String userName;
    private String accessToken;
    private String nickName;
    private int bindAction;//绑定账号类型
    private String regMsg;//送积分提示

    public String getRegMsg() {
        return regMsg;
    }

    public void setRegMsg(String regMsg) {
        this.regMsg = regMsg;
    }



    public int getBindAction() {
        return bindAction;
    }

    public void setBindAction(int bindAction) {
        this.bindAction = bindAction;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public LoginBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.uid);
        dest.writeString(this.ico);
        dest.writeString(this.userName);
        dest.writeString(this.accessToken);
        dest.writeString(this.nickName);
        dest.writeInt(this.bindAction);
    }

    protected LoginBean(Parcel in) {
        this.uid = in.readLong();
        this.ico = in.readString();
        this.userName = in.readString();
        this.accessToken = in.readString();
        this.nickName = in.readString();
        this.bindAction = in.readInt();
    }

    public static final Creator<LoginBean> CREATOR = new Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
