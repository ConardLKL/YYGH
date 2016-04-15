package com.lhjl.yygh;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

public class YuYuecontentActivity extends Activity {
	private YuYuecontentActivity context;
	private TextView title_id,hospital_name,keshi,yisheng_name,
	user_name,time,phone,pay,money;
	private Button title_btn_left,title_btn_right;
	private Map map = new HashMap();
	private String timetxt,user,yisheng,keshitxt,pay_tpye,hospital,all="",all2="",
	phoneno,pay_money,xml003;
	int postion;
	String[] strarray2;
	SDcard sdcard =new SDcard();
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.yuyuecontent);
	Intent in = getIntent();
	if(in!=null){
		timetxt = in.getStringExtra("time");
		user = in.getStringExtra("user");
		yisheng = in.getStringExtra("yisheng");
		keshitxt = in.getStringExtra("keshi");
		pay_tpye = in.getStringExtra("pay_type");
		phoneno = in.getStringExtra("phone");
		pay_money =in.getStringExtra("pay_money");
		hospital = in.getStringExtra("hospital");
		strarray2 = in.getStringArrayExtra("xml");
		postion = in.getIntExtra("postion", -1);
	}
	init();
}

void init(){
	title_id = (TextView) findViewById(R.id.title_id);
	title_id.setText("我的预约");
	title_btn_left = (Button) findViewById(R.id.title_btn_left);
	title_btn_right = (Button) findViewById(R.id.title_btn_right);
	title_btn_left.setBackgroundResource(R.drawable.back_btn_selector);	
	title_btn_left.setOnClickListener(new OnClickListener(
			) {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	title_btn_right.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			quxiao();
			
		}
	});
	hospital_name = (TextView) findViewById(R.id.hospital_name);
	hospital_name.setText(hospital);
	keshi = (TextView) findViewById(R.id.keshi);
	keshi.setText(keshitxt);
	yisheng_name = (TextView) findViewById(R.id.yisheng_name);
	yisheng_name.setText(yisheng);
	time = (TextView) findViewById(R.id.time);
	time.setText(timetxt);
	user_name = (TextView) findViewById(R.id.user_name);
	user_name.setText(user);
	phone = (TextView) findViewById(R.id.phone);
	phone.setText(phoneno);	
	pay = (TextView) findViewById(R.id.pay);
	pay.setText(pay_tpye);
	money = (TextView) findViewById(R.id.money);	
	money.setText(pay_money);
			
}


public void quxiao() {
	new AlertDialog.Builder(this)
			.setTitle("提示")
			.setMessage("确定取消该预约?")
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

					xml003 = sdcard.readFileSdcard("YY003.txt");
					map = ClassicXML.readStringTHJYXml(xml003);
					Tool.showToast(context, map.get("orderCode").toString());
					if((postion+1)==strarray2.length-1){
						all =strarray2[0];
						for (int i = 1; i < (postion+1); i++) {
							
							all += "<DATA_ITEM>" +strarray2[i].toString();
							
						}
						
						for (int i = postion+2; i < strarray2.length; i++) {
							all2 += "<DATA_ITEM>"+strarray2[i].toString();
							
						}
						System.out.println(all+all2);
						sdcard.writeSDFile(all + all2, "YY002.txt");
					}else {
						all = strarray2[0];
						for (int i = 1; i < strarray2.length-1; i++) {
							System.out.println(i);
							all += "<DATA_ITEM>"+strarray2[i].toString();
							System.out.println(all);
						}
						System.out.println(all + "</DATA_LIST></DATA_AREA></UTILITY_PAYMENT>");
						String al =all + "</DATA_LIST></DATA_AREA></UTILITY_PAYMENT>";
						sdcard.writeSDFile(al, "YY002.txt");
						
							
					}
					
					
					finish();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					// 取消按钮事件

				}
			}).show();
}
//退号交易
public void quxiao_csp() {
	String date = ConstantsSet.getDate();
	String time = ConstantsSet.getTimeStr();
	// 访问分行csp无需验证
	// 请求报文头
	Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
	Header type = new BasicHeader("type", ConstantsSet.Type);
	// 报文体内容
	String mc = ConstantsSet.xmlhead(Params.CXKSLB_CODE, date, time,
			"",  "<businessType>04</businessType><orderCode>"
					+ "2207220123456789"
					+ "</orderCode>");
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
