package com.lhjl.yygh.util;

import org.apache.http.Header;
import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;

import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpClient;
import com.bocsoft.ofa.http.asynchttpclient.ResponseHandlerInterface;

public class BOCOPCommonInterface {

	/*
	 * public final static String DOMIN = "http://22.188.21.23"; public final
	 * static int PORT = 9084; private final static String LOGIN_MCIS = DOMIN +
	 * ":" + PORT + "/bocop/mcis"; private final static String LOGIN_MCISCSP =
	 * DOMIN + ":" + PORT + "/bocop/mciscsp"; private final static String
	 * UNLOGIN_MCIS = DOMIN + ":" + PORT + "/bocop/unlogin/mcis"; private final
	 * static String UNLOGIN_MCISCSP = DOMIN + ":" + PORT +
	 * "/bocop/unlogin/mciscsp";
	 */

	/**
	 * 访问总行通用接口，需要验证用户权�? *
	 * 
	 * @param context
	 * @param headers
	 * @param mcis
	 * @param responseHandler
	 */
	public static void AccessLoginMcis(Context context, Header[] headers,
			byte[] mcis, ResponseHandlerInterface responseHandler) {

		post(context, ConstantsSet.LOGIN_MCIS, headers, mcis, responseHandler);

	}

	/**
	 * 访问分行CSP通用接口，需要验证用户权�? *
	 * 
	 * @param context
	 * @param headers
	 * @param mcis
	 * @param responseHandler
	 */
	// public static void AccessLoginMciscsp(Context context, Header[] headers,
	// byte[] mcis, ResponseHandlerInterface responseHandler) {
	//
	// post(context, ConstantsSet.LOGIN_MCISCSP, headers, mcis,
	// responseHandler);
	//
	// }

	/**
	 * 访问总行通用接口，无�?��证用户权�? *
	 * 
	 * @param context
	 * @param headers
	 * @param mcis
	 * @param responseHandler
	 */
	public static void AccessUnloginMcis(Context context, Header[] headers,
			byte[] mcis, ResponseHandlerInterface responseHandler) {

		post(context, ConstantsSet.UNLOGIN_MCIS, headers, mcis, responseHandler);

	}

	/**
	 * 访问分行CSP通用接口（无�?��证用户权限）
	 * 
	 * @param context
	 * @param headers
	 * @param mcis
	 * @param responseHandler
	 */
	public static void AccessUnloginMciscsp(Context context, Header[] headers,
			byte[] mcis, ResponseHandlerInterface responseHandler) {

		post(context, ConstantsSet.UNLOGIN_MCISCSP, headers, mcis,
				responseHandler);

	}

	private static void post(Context context, String url, Header[] headers,
			byte[] mcis, ResponseHandlerInterface responseHandler) {

		final String contentType = "application/octet-stream; charset=UTF-8";
		AsyncHttpClient client = new AsyncHttpClient();
		client.addHeader("Content-Type", contentType);
		client.addHeader("Cache-Control", "no-cache");
		client.addHeader("Accept-Charset", "UTF-8");

		ByteArrayEntity entity = new ByteArrayEntity(mcis);
		client.post(context, url, headers, entity, contentType, responseHandler);
	}

}
