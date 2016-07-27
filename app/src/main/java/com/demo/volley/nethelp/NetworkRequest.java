package com.demo.volley.nethelp;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class NetworkRequest extends JsonRequest<JSONObject>
{
    private Priority mPriority = Priority.HIGH;

    public NetworkRequest(int method, String url,
                          Map<String, String> postParams, Listener<JSONObject> listener,
                          ErrorListener errorListener)
    {
        super(method, url, paramstoString(postParams), listener, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public NetworkRequest(String url, List<NameValuePair> params,
                          Listener<JSONObject> listener, ErrorListener errorListener)
    {
        this(Method.GET, urlBuilder(url, params), null, listener, errorListener);
    }

    public NetworkRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener)
    {
        this(Method.GET, url, null, listener, errorListener);
    }

    private static String paramstoString(Map<String, String> params)
    {
        if (params != null && params.size() > 0)
        {
            String paramsEncoding = "UTF-8";
            StringBuilder encodedParams = new StringBuilder();
            try
            {
                for (Map.Entry<String, String> entry : params.entrySet())
                {
                    encodedParams.append(URLEncoder.encode(entry.getKey(),
                            paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder.encode(entry.getValue(),
                            paramsEncoding));
                    encodedParams.append('&');

                }
                return encodedParams.toString();
            }
            catch (UnsupportedEncodingException uee)
            {
                throw new RuntimeException("Encoding not supported: "
                        + paramsEncoding, uee);
            }
        }
        return null;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
    {

        try
        {

            JSONObject jsonObject = new JSONObject(new String(response.data, "UTF-8"));

            return Response.success(jsonObject,
                    HttpHeaderParser.parseCacheHeaders(response));

        }
        catch (Exception e)
        {

            return Response.error(new ParseError(e));

        }
    }

    @Override
    public Priority getPriority()
    {
        return mPriority;
    }

    public void setPriority(Priority priority)
    {
        mPriority = priority;
    }

    private static String urlBuilder(String url, List<NameValuePair> params)
    {
        return url + "?" + URLEncodedUtils.format(params, "UTF-8");
    }

 //-----xudong------------

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }
}