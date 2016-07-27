package com.demo.volley;


//xudong

/*不同的应用相应错误码不一样*/
public class ErrorCode {
//	0000	成功
//	0001	失败
//	1001	未知错误
//	1002	系统异常
//	1003	接口鉴权失败
//	2001	未知参数错误
//	2002	必填参数为空
//	2003	参数范围错误


	public static final String OK = "0000";//成功
	public static final String Fail = "0001";//失败
	public static final String UnknowError = "1001";//未知错误
	public static final String SystemError = "1002";//系统异常
	public static final String InterfaceAuthError = "1003";//接口鉴权失败
	public static final String UnknowParaError = "2001";//未知参数错误
	public static final String ParaIsEmpty = "2002";//必填参数为空
	public static final String ParaScopeError = "2003";//参数范围错误
	public static final String TokenTimeOutError = "3001";//您的用户信息已过期，请重新登录

  //自定义错误码
	public static final String VolleyError = "9000";//框架异常
	public static final String NoNetWork = "9001";//无网络
    //--------------------------------------------------------------------------------

}