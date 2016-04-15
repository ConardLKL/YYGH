package com.lhjl.yygh;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.adapter.MyyuyuelistAdapter;
import com.lhjl.yygh.domain.HuanzhejiluInfo;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

public class MyyuyueListActivity extends Activity {
	private MyyuyueListActivity context;
	private ListView yuyuelist;
	private List<HuanzhejiluInfo> info = new  ArrayList<HuanzhejiluInfo>();
	private MyyuyuelistAdapter madapter;
	private TextView title_id;
	private Button title_btn_left;
	private String xml002;
	String[] strarray2;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.myyuyuelist);
	init();
}
void init(){
	title_id = (TextView) findViewById(R.id.title_id);
	title_id.setText("我的预约");
	title_btn_left = (Button) findViewById(R.id.title_btn_left);
	title_btn_left.setBackgroundResource(R.drawable.back_btn_selector);	
	title_btn_left.setOnClickListener(new OnClickListener(
			) {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	yuyuelist = (ListView) findViewById(R.id.yuyuelist);
	xml002 = new SDcard().readFileSdcard("YY002.txt");
	 strarray2 = xml002.split("<DATA_ITEM>");
   System.out.println(strarray2.length);
	
	info = ClassicXML.readhuanzheStringXmlOut(xml002);
	if(info.size()>0){
		madapter = new MyyuyuelistAdapter(context, info, yuyuelist);
		yuyuelist.setAdapter(madapter);
	}
	
	yuyuelist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			 Intent intent = new Intent();
			 intent.setAction("yuyuecontent");
			 intent.putExtra("yisheng", info.get(arg2).getDoctorName());
			 intent.putExtra("keshi", info.get(arg2).getDepartmentItem());
			 intent.putExtra("time", info.get(arg2).getOrderDate());
			 intent.putExtra("user", info.get(arg2).getApplicant());
			 intent.putExtra("phone", info.get(arg2).getTelephone());
			 intent.putExtra("pay_type", info.get(arg2).getPaymentState());
			 intent.putExtra("pay_money", info.get(arg2).getRegistryFee());
			 intent.putExtra("hospital", info.get(arg2).getHospitalName());
			 intent.putExtra("xml", strarray2);
			 intent.putExtra("postion", arg2);
			 
			 startActivity(intent);
		}
	});
}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	xml002 = new SDcard().readFileSdcard("YY002.txt");
	 strarray2 = xml002.split("<DATA_ITEM>");
  System.out.println(strarray2.length);
  info.clear();
	info = ClassicXML.readhuanzheStringXmlOut(xml002);
		madapter = new MyyuyuelistAdapter(context, info, yuyuelist);
		yuyuelist.setAdapter(madapter);
	
}
//查询患者预约记录
public void chaxun_csp() {
	String date = ConstantsSet.getDate();
	String time = ConstantsSet.getTimeStr();
	// 访问分行csp无需验证
	// 请求报文头
	Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
	Header type = new BasicHeader("type", ConstantsSet.Type);
	// 报文体内容
	String mc = ConstantsSet.xmlhead(Params.CXKSLB_CODE, date, time,
			"",  "<businessType>04</businessType>");
	Log.e("mic", mc);
	// 请求报文体
	try {

		byte[] mcis = mc.getBytes("GBK");
		BOCOPCommonInterface.AccessUnloginMciscsp(context, new Header[] {
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
//							info = ClassicXML.readStringXmlOut(msg);
//							if (info.size() != 0) {
//								mAdapter = new MyAdapter(context,
//										R.layout.cxdt_list_item, info);
//								mListView.setAdapter(mAdapter);
//
//							} else {
//								Tool.showToast(context, "没有定投记录！");
//							}
						} else {
							Tool.showToast(context, "系统错误！");
						}
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				if (ppd != null) {
//					ppd.dismiss();
//				}
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
//				if (ppd != null) {
//					ppd.dismiss();
//				}
				Tool.showToast(context, "系统错误！");
			}

		});
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
//		if (ppd != null) {
//			ppd.dismiss();
//		}
	}

}
}
