package com.demo.volley;

import android.app.Application;
import android.content.Context;

/**
 * 整个应用程序Applicaiton
 *
 * @author xudong
 * @version 1.0
 */
public class MApplication extends Application{

	/**
	 * 对外提供整个应用生命周期的Context
	 **/
	private static Context instance;



	/**
	 * 日志输出标志
	 **/
	protected final String TAG = this.getClass().getSimpleName();
	
	/**
	 * 对外提供Application Context
	 *
	 * @return
	 */
	public static Context gainContext() {
		return instance;
	}

	public void onCreate() {
		super.onCreate();

		instance = this;


	}


}
