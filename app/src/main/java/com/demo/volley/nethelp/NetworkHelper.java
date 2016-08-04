package com.demo.volley.nethelp;

/**
 * Created by dong.xu on 2016/5/11.
 */

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.demo.volley.ErrorCode;
import com.demo.volley.bean.BaseResponseBean;
import com.demo.volley.tool.ToolNetwork;
import com.demo.volley.view.LoadingDialog;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class NetworkHelper implements Response.Listener<JSONObject>, ErrorListener {
	private Context context;
	private UIDataListener uiDataListener;
	private String tag;// 标识区分哪个请求，以便调用处理

	private LoadingDialog mLoadingDialog;

	//    private static NetworkHelper instance;

	public NetworkHelper(Context context) {
		this.context = context;

		if (this.context instanceof Activity) {
			mLoadingDialog = new LoadingDialog();
		}
	}

	//    public static NetworkHelper getInstance(Context context){
	//        if(instance == null){
	//            instance = new NetworkHelper(context);
	//        }
	//        return instance;
	//    }

	protected Context getContext() {
		return context;
	}

	protected NetworkRequest getRequestForGet(String url, List<NameValuePair> params) {
		if (params == null) {
			return new NetworkRequest(url, this, this);
		} else {
			return new NetworkRequest(url, params, this, this);
		}
	}

	protected NetworkRequest getRequestForPost(String url, Map<String, String> params) {
		return new NetworkRequest(Method.POST, url, params, this, this);
	}

	public void sendGETRequest(String url, List<NameValuePair> params,
			UIDataListener uiDataListener, String tag, boolean isShowLoadingDialog) {
		Log.i("发送请求：", url);

		this.uiDataListener = uiDataListener;
		this.tag = tag;


			if (mLoadingDialog != null && isShowLoadingDialog) {
				Activity activity = (Activity) context;
				mLoadingDialog.show(activity.getFragmentManager(), "http");
			}

			//VolleyQueueController.getQueue(context).add(getRequestForGet(url, params));
		    VolleyQueueController.getStethoRequestQueue(context).add(getRequestForGet(url, params));

	}

	public void sendPostRequest(String url, Map<String, String> params,
			UIDataListener uiDataListener, String tag) {
		VolleyQueueController.getQueue(context).add(getRequestForPost(url, params));
		//VolleyQueueController.getStethoRequestQueue(context).add(getRequestForPost(url, params));

		this.uiDataListener = uiDataListener;
		this.tag = tag;
	}

	@Override public void onErrorResponse(VolleyError error) {
		if (mLoadingDialog != null && mLoadingDialog.getShowsDialog()) {
			mLoadingDialog.dismiss();
		}

		//Log.d("Amuro", error.getMessage());
		//MyLog.i("onErrorResponse", "onErrorResponse:" + error.toString());
		disposeVolleyError(error);
	}

	//protected abstract void disposeVolleyError(VolleyError error);
	protected void disposeVolleyError(VolleyError error) {
		if (mLoadingDialog != null && mLoadingDialog.getShowsDialog()) {
			mLoadingDialog.dismiss();
		}

		Log.i("VolleyError", "VolleyError:" + error.toString());
		//ToolAlert.toastShort(error.toString());
		notifyErrorHappened(ErrorCode.VolleyError, error.getMessage());//框架出错
	}

	@Override public void onResponse(JSONObject response) {
		if (mLoadingDialog != null && mLoadingDialog.getShowsDialog() && mLoadingDialog.isAdded()) {
			mLoadingDialog.dismiss();
		}

		Log.i("onResponse", response.toString() + " " + this.tag);
		try {
			disposeResponse(response);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//protected abstract void disposeResponse(JSONObject response);
	protected void disposeResponse(JSONObject response) throws JSONException {

		if (response != null) {

			if (response.has("code") && response.getString("code")
					.equals(ErrorCode.TokenTimeOutError))
			{
				uiDataListener.onTokenTimeOutError(response.getString("code"));
				return;
			}

			//公司接口数据
			if (response.has("code") && response.has("msg") /*&& response.has("data")*/) {
				BaseResponseBean res = new BaseResponseBean(response);
				if (res.getCode().equals(ErrorCode.OK))//正常 请求成功
				{

					if (!TextUtils.isEmpty(res.getData())
							&& !res.getData().equals("null")
							&& !res.getData().equals("[]")) {

						//notifyDataChanged(new JSONObject(res.getData()));
						Object json = new JSONTokener(res.getData()).nextValue();
						if (json instanceof JSONObject) {//data 内容是JSONObject
							JSONObject jsonObject = (JSONObject) json;

							notifyDataChanged(jsonObject);
						} else if (json instanceof JSONArray) {//data 内容是JSONArrary

							notifyDataChanged(response);//全部数据传回去
						}
					} else {

						String jsonNull = "{\n" +
								"  \"data\": \"null\"\n" +
								"}";
						notifyDataChanged(new JSONObject(jsonNull));
					}
				} else {
					notifyErrorHappened(res.getCode(), res.getMsg());//业务参数出错
				}
				//此处要做出错情况处理
				//notifyDataChanged(response);
			} else//
			{
				notifyDataChanged(response);
			}
		}
	}


	public void setUiDataListener(UIDataListener uiDataListener) {
		this.uiDataListener = uiDataListener;
	}

	protected void notifyDataChanged(JSONObject data) {
		if (uiDataListener != null) {
			uiDataListener.onDataChanged(data, tag);
		}
	}

	protected void notifyErrorHappened(String errorCode, String errorMessage) {
		if (uiDataListener != null) {
			uiDataListener.onErrorHappened(errorCode, errorMessage, tag);
		}
	}
}
