package com.demo.volley.nethelp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.demo.volley.tool.VolleyUtil;

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
}
