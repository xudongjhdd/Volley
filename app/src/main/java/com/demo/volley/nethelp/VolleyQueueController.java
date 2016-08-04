package com.demo.volley.nethelp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.demo.volley.OkHttpStack;
import com.demo.volley.tool.VolleyUtil;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

public class VolleyQueueController {
	private volatile static RequestQueue requestQueue;

	/** 返回RequestQueue单例 **/
	public static RequestQueue getQueue(Context context) {
		if (requestQueue == null) {
			synchronized (VolleyUtil.class) {
				if (requestQueue == null) {
					requestQueue = Volley.newRequestQueue(context.getApplicationContext());
				}
			}
		}
		return requestQueue;
	}


	/**
	 * @return The Volley Request queue
	 * stetho调试Volley
	 * chrome 浏览器打开 chrome://inspect/#devices
	 */
	public static RequestQueue getStethoRequestQueue(Context context) {
		// lazy initialize the request queue, the queue instance will be
		// created when it is accessed for the first time
		if (requestQueue == null) {
			OkHttpClient client = new OkHttpClient();
			client.networkInterceptors().add(new StethoInterceptor());
			requestQueue = Volley.newRequestQueue(context, new
					OkHttpStack(client));
		}
		return requestQueue;
	}

}
