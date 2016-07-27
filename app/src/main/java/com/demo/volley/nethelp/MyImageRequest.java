package com.demo.volley.nethelp;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudong.
 * Time：2016/6/8 14:10.
 * 重写此类因为HTTP请求中 head 加入Referer字段 以便防止盗链接
 */
public class MyImageRequest extends ImageRequest {

    public MyImageRequest(String url, Response.Listener<Bitmap> listener, int maxWidth, int maxHeight, Bitmap.Config decodeConfig, Response.ErrorListener errorListener) {
        //super(url, listener, maxWidth, maxHeight, decodeConfig, errorListener);
        super(url, listener, maxWidth, maxHeight,
                ImageView.ScaleType.CENTER_INSIDE, decodeConfig, errorListener);
    }



    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        //return super.getHeaders();
        //MyLog.i("addHead:","MyImageRequest");
        Map<String, String> headers = new HashMap<String, String>();
        //headers.put("Accept", "application/json");
        //headers.put("Content-Type", "application/json; charset=UTF-8");
        //headers = super.getHeaders();
        headers.putAll(super.getHeaders());
        headers.put("Referer", "yourcompany");//防止盗链接 此值与后台服务器协商决定 不是此值的图片请求被认为不合法请求

        return headers;

    }
}
