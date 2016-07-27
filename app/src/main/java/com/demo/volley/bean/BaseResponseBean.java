package com.demo.volley.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dong.xu on 2016/5/19.
 */
// 服务器返回数据值 ，不同的应用 需要做相应的修改
//        字段	类型	说明	备注
//        code	String	请求结果编码	 0000成功,详见附件
//        msg	String	请求处理信息
//        data	String	具体返回值	JSON
//        注:接口说明中返回值data均指的是上表中字段” data”内的JSON原对象

public class BaseResponseBean {

    String code;
    String msg;
    String data;


    public BaseResponseBean(JSONObject obj) throws JSONException {

        if (obj.has("code")) {
            this.code = obj.getString("code");
        }


        if(obj.has("msg")){
            this.msg = obj.getString("msg");
        }

        if(obj.has("data")){
            this.data = obj.getString("data");
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
