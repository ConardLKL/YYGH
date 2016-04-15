package com.lhjl.yygh;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.adapter.YiYuanlistAdapter;
import com.lhjl.yygh.adapter.YuanQuAdapter;
import com.lhjl.yygh.domain.YiYuanListInfo;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

/**
 * Created with IntelliJ IDEA.
 * 
 */
public class TabAFm extends Fragment {
	private SharedPreferences sp;
	private TabAFm taba;
	private TextView ii_hall_ssq_summary;
	private Button title_btn_left;
	private GridView gridview;
	private YuanQuAdapter yqadapter;
	private YiYuanlistAdapter yyadapter;
	private  String  xml;
	private ListView yuqulist;
	private List<YiYuanListInfo> info = new ArrayList<YiYuanListInfo>();
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		System.out.println("AAAAAAAAAA____onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		taba = this;
		System.out.println("AAAAAAAAAA____onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("AAAAAAAAAA____onCreateView");
		return inflater.inflate(R.layout.tab_a, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
		System.out.println("AAAAAAAAAA____onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		System.out.println("AAAAAAAAAA____onStart");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("AAAAAAAAAA____onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("AAAAAAAAAA____onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		System.out.println("AAAAAAAAAA____onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		System.out.println("AAAAAAAAAA____onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("AAAAAAAAAA____onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		System.out.println("AAAAAAAAAA____onDetach");
	}

	 void init() {
		 xml = new SDcard().readFileSdcard("YY009.txt");
		 String XML = "<UTILITY_PAYMENT><CONST_HEAD><REQUEST_TYPE>0240</REQUEST_TYPE><REQUEST_MERCH>BOCAPP00</REQUEST_MERCH><AGENT_CODE>1123123</AGENT_CODE><TRN_CODE>YY001</TRN_CODE><CSPS_TRACENO>123</CSPS_TRACENO><FRONT_DATE></FRONT_DATE><FRONT_TIME></FRONT_TIME></CONST_HEAD><DATA_AREA><customerId>111111</customerId><businessType>00</businessType><channelFlag>02</channelFlag><bindCardNo></bindCardNo></DATA_AREA></UTILITY_PAYMENT>";
		
		 StringEntity s;
		 HttpResponse re;
		try {
			 HttpClient httpclient = new DefaultHttpClient();
			 HttpPost request = new HttpPost("http://115.29.100.140:7991");
			s = new StringEntity(XML, HTTP.UTF_8);
			 s.setContentType("text/xml charset=utf-8");
			 request.setEntity(s);
				re = httpclient.execute(request);
				 String result = EntityUtils.toString(re.getEntity(), HTTP.UTF_8);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

		   
		   //获得GridView实例 
//		    gridview = (GridView) this.getView().findViewById(R.id.yuyue_gridview); 
		yuqulist = (ListView) this.getView().findViewById(R.id.yuqulist);
			info = ClassicXML.readyuanquStringXmlOut(xml);
			if (info.size() != 0) {
//				yqadapter = new YuanQuAdapter(getActivity(),
//						 info , gridview);
//				gridview.setAdapter(yqadapter);
				yyadapter = new YiYuanlistAdapter(getActivity(), info, yuqulist);
				yuqulist.setAdapter(yyadapter);
			}
//		    gridview.setOnItemClickListener(new OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1,
//						int arg2, long arg3) {
//					// TODO Auto-generated method stub
//					String name = (String)((TextView)arg1.findViewById(R.id.yiyuan_text)).getText();
//					 Intent intent = new Intent();
//						 intent.setAction("keshi");
//						 intent.putExtra("name", name);
//						 intent.putExtra("postion", arg2+"");
//						 intent.putExtra("hospitaltel", info.get(arg2).getHospital_tel());
//						 intent.putExtra("hospitaladdr", info.get(arg2).getHospital_addr());
//						 startActivity(intent);
//				}
//			});
		 yuqulist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
//				String name = (String)((TextView)arg1.findViewById(R.id.yiyuan_text)).getText();
				 Intent intent = new Intent();
					 intent.setAction("keshi");
					 intent.putExtra("name", info.get(arg2).getHospitalName());
					 intent.putExtra("postion", arg2+"");
					 intent.putExtra("dengji", info.get(arg2).getHospital_dengji());
					 intent.putExtra("hospitaltel", info.get(arg2).getHospital_tel());
					 intent.putExtra("hospitaladdr", info.get(arg2).getHospital_addr());
					 startActivity(intent);
			}
		});
	 }
	 
	 
	 
	 
	// 查询院区列表
		public void chaxundingtou_csp() {
			String date = ConstantsSet.getDate();
			String time = ConstantsSet.getTimeStr();
			// 访问分行csp无需验证
			// 请求报文头
			Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
			Header type = new BasicHeader("type", ConstantsSet.Type);
			// 报文体内容
			String mc = ConstantsSet.xmlhead(Params.CXYQLB_CODE, date, time,
					"","<businessType>03</businessType>");
			Log.e("mic", mc);
			// 请求报文体
			try {

				byte[] mcis = mc.getBytes("GBK");
				BOCOPCommonInterface.AccessUnloginMciscsp(getActivity(), new Header[] {
						clentid, type }, mcis, new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {

						// Logger.d(TAG, "post start..");

					}

					@Override
					public void onFinish() {
						// Logger.d(TAG, "post finish..");
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] binaryData) {
						// TODO Auto-generated method stub
						// Logger.d(TAG, "post success.." + statusCode);

						try {
							Map<String, String> map = new HashMap<String, String>();
							for (Header h : headers) {
								Logger.d("---", h.getName() + "----------->"
										+ URLDecoder.decode(h.getValue(), "UTF-8"));
								map.put(h.getName(),
										URLDecoder.decode(h.getValue(), "UTF-8"));

							}
							Log.e("aaaaaaaaa", map.get("rtnmsg") + ".");
							if (binaryData != null) {
								Logger.d("---+", "content : "
										+ new String(binaryData, "GBK"));

								String data = new String(binaryData, "GBK");
								if (map.get("msgcde").equals("0000000")) {
									String msg = data.substring(136,
											data.length() - 1);
//									info = ClassicXML.readStringXmlOut(msg);
//									if (info.size() != 0) {
//										mAdapter = new MyAdapter(context,
//												R.layout.cxdt_list_item, info);
//										mListView.setAdapter(mAdapter);
//
//									} else {
//										Tool.showToast(context, "没有定投记录！");
//									}
								} else {
									Tool.showToast(getActivity(), "系统错误！");
								}
							}
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

//						if (ppd != null) {
//							ppd.dismiss();
//						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] binaryData, Throwable error) {
						// TODO Auto-generated method stub
						// Logger.d(TAG, "post failure.." + statusCode);

						try {
							for (Header h : headers) {
								Logger.d("========", h.getName() + "----------->"
										+ URLDecoder.decode(h.getValue(), "UTF-8"));

							}
							if (binaryData != null) {
								Logger.d("=====+", "content : "
										+ new String(binaryData, "GBK"));
								Log.e("statusCode : ", statusCode + "\n"
										+ new String(binaryData, "GBK"));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						if (ppd != null) {
//							ppd.dismiss();
//						}
						Tool.showToast(getActivity(), "系统错误！");
					}

				});
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				if (ppd != null) {
//					ppd.dismiss();
//				}
			}

		}
}
