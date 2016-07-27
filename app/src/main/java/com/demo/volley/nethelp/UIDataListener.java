package com.demo.volley.nethelp;

import org.json.JSONObject;

/**
 * Created by dong.xu on 2016/5/11.
 */
public interface UIDataListener
{
    //public void onDataChanged(JSONObject data, String tag);
    //public void onErrorHappened(ErrorBean bean, String tag);

    //public void onDataChanged(T data);
    public void onDataChanged(JSONObject data,String tag);
    public void onErrorHappened(String errorCode, String errorMessage,String tag);

    public void onTokenTimeOutError(String code);
}

