package com.lhjl.yygh.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.boc.bocop.sdk.api.bean.ResponseBean;
import com.boc.bocop.sdk.api.bean.oauth.BOCOPOAuthInfo;
import com.boc.bocop.sdk.api.bean.useinfo.UserInfoSearch;
import com.boc.bocop.sdk.api.event.ResponseListener;
import com.boc.bocop.sdk.api.exception.ResponseError;
import com.boc.bocop.sdk.http.BeanUtils;
import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpClient;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.bocsoft.ofa.http.asynchttpclient.BaseJsonHttpResponseHandler;
import com.bocsoft.ofa.http.asynchttpclient.expand.JsonRequestParams;
import com.lhjl.yygh.domain.CardInfo;

public class ConstantsSet {
	static String cardno = null;
	static String userid, token;
	static String data;
	public static final String Type = "02";

	/*
	 * appkey566
	 */
	public static final String CONSUMER_KEY = "340";

	/*
	 * appSecret 002d82951f56aa1fea1b81749fdd7b32b9cdd354bb4d5abe26
	 */

	public static final String CONSUMER_SECRET = "f9cbdd2a8ed55dc15733929d37b8c0935ab9a0aecb74d02861";

	/*
	 * bocopSDK�?��设置的URL
	 */
	public static String CONSUMER_URL = "https://openapi.boc.cn";
	// public static String CONSUMER_URL = "http://22.188.12.161";
	/*
	 * bocopSDK�?��设置的port
	 */
	public static int CONSUMER_PORT = 443;
	// public static int CONSUMER_PORT = 8080;
	// http://22.188.12.105
	public final static String APPMESG = "https://open.boc.cn"
			+ "/interFace/getAppUpdate.php";

	// public final static String ZHUCE =
	// "https://open.boc.cn/wap/register.php?";
	// http://open.boc.cn/wap/register.php?themeid=***&devicetype=***&clientid
	// =***
	public final static String ZHUCE = "http://open.boc.cn/wap/register.php?themeid=1&devicetype=1&clientid =340";
	public final static String CardList = CONSUMER_URL + ":" + CONSUMER_PORT
			+ "/app/querydebitcardbycusid";
	public final static String LOGIN_MCIS = CONSUMER_URL + ":" + CONSUMER_PORT
			+ "/bocop/mcis";
	public final static String LOGIN_MCISCSP = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/bocop/mciscsp";
	public final static String UNLOGIN_MCIS = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/bocop/unlogin/mcis";
	// http://openapi.boc.cn/unlogin/mciscsp
	public final static String UNLOGIN_MCISCSP = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/bocop/unlogin/mciscsp";
	public final static String UPDATE_URL = CONSUMER_URL + ":" + CONSUMER_PORT
			+ "/appserver/aps/getAppListByFavorite.php?";
	// 牌价
	public final static String RATE_SEARCH_URL = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/rate/search";
	// 查询用户卡资�?
	public final static String RATE_All_URL = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/bocop/appfindusrinfo";
	// 完整卡号
	public final static String RATE_CARDNO_URL = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/app/getcardno";
	// 附加用户信息
	public final static String RATE_useridquery_URL = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/app/useridquery";
	// 余额 借记�?
	public final static String RATE_SEARCH_YUE = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/app/debitbalsearch";
	// 交易ETOKEN
	public final static String RATE_ETOKEN = CONSUMER_URL + ":" + CONSUMER_PORT
			+ "/banking/paysendchit";
	// 验证短信验证�?
	public final static String TATE_DUANXINYANZHENG = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/app/checkinfocode";
	// 对私客户信息查询
	public final static String RATE_Querycardindcus = CONSUMER_URL + ":"
			+ CONSUMER_PORT + "/unlogin/querycardindcusinfo";

	/*
	 * 调用 bocopSDK中的URL和端口号设置接口
	 */
	public static void initUrlSet(Context context) {
		BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
				CONSUMER_KEY, CONSUMER_SECRET);
		bocopSDKApi.initURLIPPort(context, CONSUMER_URL, CONSUMER_PORT, false,
				null);
		// bocopSDKApi.initURLIPPort(context, CONSUMER_URL, CONSUMER_PORT);
	}

	/**
	 * 报文固定数据
	 * 
	 * @param code
	 *            交易�?
	 * @param date
	 *            日期
	 * @param time
	 *            时间
	 * @param body
	 *            报文�?
	 * @return
	 */

	public static String xmlhead(String code, String date, String time, String cusid,
			String body) {
		String mcis = "00E1091500010400021               382013110102023899999990                                                                         00471GXJYTA<?xml version='1.0' encoding= 'GBK' ?><UTILITY_PAYMENT><CONST_HEAD><REQUEST_TYPE>0240</REQUEST_TYPE><REQUEST_MERCH>BOCAPP00</REQUEST_MERCH>    <AGENT_CODE>1123123</AGENT_CODE><TRN_CODE>"
				+ code
				+ "</TRN_CODE><CSPS_TRACENO>123</CSPS_TRACENO><FRONT_DATE>"
				+ date
				+ "</FRONT_DATE><FRONT_TIME>"
				+ time
				+ "</FRONT_TIME></CONST_HEAD><DATA_AREA>"
				+ "<customerId>" + cusid + "</customerId>"
				+ "<channelFlag>02</channelFlag>"
				+ body
				+ "</DATA_AREA></UTILITY_PAYMENT>";
		return mcis;
	}

	public static String BDGX = "N"; // 绑定关系
	public static String FC_ACT_NO = "FC_ACT_NO";// 银行卡号
	public static final String FC_NAME = "FC_NAME";// 姓名
	public static final String FC_ID_NO = "FC_ID_NO";// 证件号码
	public static final String FC_CBET_ACCOUNT = "FC_CBET_ACCOUNT";// 用户投注账号
	public static final String CUSTOMERID = "CUSTOMERID";// 核心客户�?
	public static final String FC_MOBILE = "FC_MOBILE";// 手机�?
	public static final String FC_TR_ADDRESS_ID = "FC_TR_ADDRESS_ID";// 手机�?

	/**
	 * 获取日期字符串�?
	 * 
	 * <pre>
	 *  日期字符串格式： yyyyMMdd
	 *  其中�?
	 *      yyyy   表示4位年�?
	 *      MM     表示2位月�?
	 *      dd     表示2位日�?
	 *      
	 *  时间字符串格式：HHmmss
	 * </pre>
	 * 
	 * @return String "yyyyMMdd"格式的日期字符串�?
	 **/
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		return formatter.format(new Date());
	}

	public static String getTimeStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");

		return formatter.format(new Date());
	}

	public static void authlogin(final Context context) {
		BOCOPPayApi bocopSDKApi = null;
		final SharedPreferences sp;
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		bocopSDKApi = BOCOPPayApi.getInstance(context,
				ConstantsSet.CONSUMER_KEY, ConstantsSet.CONSUMER_SECRET);
		bocopSDKApi.initURLIPPort(context, ConstantsSet.CONSUMER_URL,
				ConstantsSet.CONSUMER_PORT, true, ConstantsSet.ZHUCE);
		bocopSDKApi.authorize(context, new ResponseListener() {
			private BOCOPOAuthInfo info;

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(Error arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComplete(ResponseBean response) {
				// TODO Auto-generated method stub

				Logger.d("testOAuth 测试成功");
				if (response instanceof BOCOPOAuthInfo) {
					info = (BOCOPOAuthInfo) response;
				}
				Editor edit = sp.edit();
				edit.remove(Params.Userid);
				edit.remove(Params.Token);
				edit.putString(Params.Userid, info.getUserId());
				edit.putString(Params.Token, info.getAccess_token().trim());
				edit.commit();
				userid = info.getUserId();
				token = info.getAccess_token().trim();
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});
	}

	// 得到卡列�?
	public static List<CardInfo> getcarlist(Context context) {
		final List<CardInfo> cardinfo = new ArrayList<CardInfo>();
		BOCOPPayApi bocopSDKApi = BOCOPPayApi.getInstance(context,
				ConstantsSet.CONSUMER_KEY, ConstantsSet.CONSUMER_SECRET);

		// //用户资料查询
		com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria userinfo = new com.boc.bocop.sdk.api.bean.useinfo.UserInfoCriteria();
		userinfo.setIs_financial("0");// 查询理财账户标志 0- 不查�?- 查询
		userinfo.setTrantype("02");// 卡用途分�?01-查询02-支付
		userinfo.setPageno("000001");//

		bocopSDKApi.searchUserInfo(context, userinfo, new ResponseListener() {

			private UserInfoSearch res;

			@Override
			public void onComplete(ResponseBean response) {
				// TODO Auto-generated method stub

				if (response instanceof UserInfoSearch) {
					res = (UserInfoSearch) response;
				}
				Logger.d("userinfo size:===" + res.getUsers().size());

				if (0 != res.getRecord_count()) {

					for (int i = 0; i < res.getUsers().size(); i++) {
						cardinfo.add(new CardInfo(res.getUsers().get(i)
								.getAccno(), res.getUsers().get(i).getAlias(),
								res.getUsers().get(i).getUsrid(), res
										.getUsers().get(i).getProbank(), res
										.getUsers().get(i).getLimitamt()));
						String str = "***" + res.getUsers().get(i).getAccno()
								+ "***" + res.getUsers().get(i).getAlias()
								+ "***" + res.getUsers().get(i).getUsrid()
								+ "***" + res.getUsers().get(i).getTime()
								+ "***" + res.getUsers().get(i).getProbank()
								+ "***" + res.getUsers().get(i).getLimitamt();
						Logger.d(str);
					}
				}

				// ui
				// handler.post(myRunnable);
			}

			@Override
			public void onError(Error arg0) {
				// TODO Auto-generated method stub
				if (arg0 instanceof ResponseError) {
					ResponseError err = (ResponseError) arg0;
					Log.e("error", err.getRtnmsg());
				}

			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

		});
		return cardinfo;

	}

}
