# Volley
Volley二次封装 及利用volley下载图片 并加入公司Referer防止盗链

加入 Volley库与GOSON库
app  build.gradle中加入

  //导入volley 网络请求框架
  compile 'com.mcxiaoke.volley:library:1.0.19'
  
  //导入Gson库
  compile 'com.google.code.gson:gson:2.3.1'

####################################################################################
1.加入下载图片HTTP请求中的Referer
重写Volley自带的ImageLoader->MyImageLoader 目的是为了调用重写的MyImageRequest
protected Request<Bitmap> makeImageRequest(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, final String cacheKey) {
		//return super.makeImageRequest(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);

		@Override
    	protected Request<Bitmap> makeImageRequest(String requestUrl, int maxWidth, int maxHeight, ImageView.ScaleType scaleType, final String cacheKey) {
    		//return super.makeImageRequest(requestUrl, maxWidth, maxHeight, scaleType, cacheKey);
    
    		return new MyImageRequest(requestUrl, new Response.Listener<Bitmap>() {
    			@Override
    			public void onResponse(Bitmap response) {
    				onGetImageSuccess(cacheKey, response);
    			}
    		}, maxWidth, maxHeight, Bitmap.Config.RGB_565, new Response.ErrorListener() {
    			@Override
    			public void onErrorResponse(VolleyError error) {
    				onGetImageError(cacheKey, error);
    			}
    		});
    
    	}
重写ImageRequest的目的是在getHeaders()函数中加入http Referer
headers.put("Referer", "yourcompany");//防止盗链接 此值与后台服务器协商决定 不是此值的图片请求被认为不合法请求

2.Volley网络请求

NetworkHelper networkHelper = new NetworkHelper(this);
//Get方法调用
networkHelper.sendGETRequest(url, null,MainActivity.this,"requestTag",true);
第一个参数 url:这个就是你用于网络请求的url地址
第二个参数 null:不需要传递
第三个参数 MainActivity.this 是接口传入以便网络请求的回调
因为 MainActivity已经实现回掉方法 MainActivity extends AppCompatActivity implements UIDataListener
所以网络请求数据在以下三个函数实现数据回调


		@Override public void onDataChanged(JSONObject data, String tag) {
		//正确返回的服务器数据
		Log.i("onDataChanged", data.toString());
		//返回data字段数据  ：{"uid":9999999,"userName":"18550009543","nickName":null,"ico":null,"accessToken":"123_test","regMsg":null}
		Gson gson = new Gson();
		LoginBean status = gson.fromJson(data.toString(), LoginBean.class);
		Log.i("onDataChanged", status.getUserName());
	}
	
	@Override public void onErrorHappened(String errorCode, String errorMessage, String tag) {
		//正确返回的服务器数据出错
	}
	
	@Override public void onTokenTimeOutError(String code) {
		//服务器Token失效
	}

第四个参数 "requestTag" 传入以便区分是哪个网络请求
onDataChanged(JSONObject data, String tag)
onErrorHappened(String errorCode, String errorMessage, String tag)

这两个函数中的tag 意义区分是哪个网络请求的回调，这样就会便用不同的请求不同的处理方式

第五个参数 true 是在请求中加入了LoadingDialog 这样你不需要关于网络请求中何时打开与关闭LoadingDialog
想在网络请求中显示 LoadingDialog，传入true即可。


讲完了，希望可以在你用到volley框架中用所帮助，讲的不好请提建议，谢谢！

有什么不明白可以联系我 99799543@qq.com
