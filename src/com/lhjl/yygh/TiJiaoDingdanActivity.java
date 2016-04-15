package com.lhjl.yygh;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

public class TiJiaoDingdanActivity extends Activity {
	private TiJiaoDingdanActivity context;
	private TextView title_id,yisheng_name,hospital_keshi,user,hospital_time,hospital_money,cardid,phone;
	private Button title_btn_left,tijiao_quxiao,tijiao_queren;
	private Map maps = new HashMap();
	private Map map = new HashMap();
	private String orgHISTradeNo,lockQueueNo,free,time,yisheng,yishengtype,keshitxt;
	private String xml014,xml015,xml002,xml1,xml2,hosptal,ks,user_name,data,xml002n;
	SDcard sdcard = new SDcard();
	private ImageView yuyue_img;
	private int postion;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.tijiaodingdan);
	Intent in =getIntent();
	if(in!=null){
		free = in.getStringExtra("free");
//		time = in.getStringExtra("time");
		yisheng = in.getStringExtra("yisheng");
		yishengtype = in.getStringExtra("yishengtype");
		keshitxt = in.getStringExtra("keshi");
		data = in.getStringExtra("data");
		postion = in.getIntExtra("postion", -1);
	}
	xml002= sdcard.readFileSdcard("YY002.txt");
	String[] strarray2 = xml002.split("</DATA_LIST>");
	xml1= strarray2[0].toString();
	xml2=strarray2[1].toString();
	String[] strarray = keshitxt.split(" - ");
	hosptal = strarray[0].toString();
	ks = strarray[1].toString();
	init();
}
void init(){
	
	yuyue_img = (ImageView) findViewById(R.id.yuyue_img);
	if(postion==0){
		yuyue_img.setBackgroundResource(R.drawable.doc1);
	}else if(postion==1){
		yuyue_img.setBackgroundResource(R.drawable.doc2);
	}else if(postion==2){
		yuyue_img.setBackgroundResource(R.drawable.doc3);
	}else if(postion==3){
		yuyue_img.setBackgroundResource(R.drawable.doc4);
	}else if(postion==4){
		yuyue_img.setBackgroundResource(R.drawable.doc5);
	}
	
	title_id = (TextView) findViewById(R.id.title_id);
	title_id.setText("确认订单");
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
	
	tijiao_quxiao = (Button) findViewById(R.id.tijiao_quxiao);
	tijiao_queren = (Button) findViewById(R.id.tijiao_queren);
	tijiao_quxiao.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	user = (TextView) findViewById(R.id.user);
	user_name = user.getText().toString();
	yisheng_name = (TextView) findViewById(R.id.yisheng_name);
	hospital_keshi = (TextView) findViewById(R.id.hospital_keshi);
	hospital_time = (TextView) findViewById(R.id.hospital_time);
	hospital_money = (TextView) findViewById(R.id.hospital_money);
	cardid = (TextView) findViewById(R.id.cardid);
	phone = (TextView) findViewById(R.id.phone);
	yisheng_name.setText(yisheng);
	hospital_keshi.setText(keshitxt);
	hospital_time.setText(data);
	hospital_money.setText(free);
	tijiao_queren.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			///修改本地报文
			String add = "<DATA_ITEM><medicalAccountNo>223456781</medicalAccountNo><bindCardNo>62284750183495406</bindCardNo><orderCode>YY20150620</orderCode><scheduleItemCode>01</scheduleItemCode><applyDate>"+data+"</applyDate><orderState>00</orderState><applicant>"+user_name+"</applicant><orderDate>"+data+"</orderDate><departmentItem>"+ks+"</departmentItem><doctorName>"+yisheng+"</doctorName><registryFee>"+free+"</registryFee><serialNo>20150621001</serialNo><orderContent>"+ks+"</orderContent><contactTel>123456789</contactTel><telephone>1351105445</telephone><cancelFlag>00</cancelFlag><paymentState>00</paymentState><hospitalName>"+hosptal+"</hospitalName></DATA_ITEM>";
			String all = xml1+add+"</DATA_LIST>"+xml2;
			
			
			sdcard.writeSDFile(all, "YY002.txt");
			
//			scard.writeSDFile(Params.YY002, "YY002.txt");
			
			//先锁号交易
			xml014= sdcard.readFileSdcard("YY014.txt");
			maps = ClassicXML.readStringSHJYXml(xml014);
			if (maps.get("ERR_CODE").equals("00")) {
				
				lockQueueNo = (String) maps.get("lockQueueNo");
				orgHISTradeNo = (String) maps.get("orgHISTradeNo");
				
				//预约挂号
				xml015 = sdcard.readFileSdcard("YY015.txt");
				map = ClassicXML.readStringYYGHXml(Params.YY015);
				Tool.showToast(context, map.get("orderCode")
						.toString());
				
				
				xml002n = new SDcard().readFileSdcard("YY002.txt");
				String[] strarray2 = xml002n.split("<DATA_ITEM>");
				 Intent intent = new Intent();
				 intent.setAction("yuyuecontent");
				 intent.putExtra("yisheng", yisheng);
				 intent.putExtra("keshi", ks);
				 intent.putExtra("time", data);
				 intent.putExtra("user", user_name);
				 intent.putExtra("phone", "1351105445");
				 intent.putExtra("pay_type", yishengtype);
				 intent.putExtra("pay_money", free);
				 intent.putExtra("hospital", hosptal);
				 intent.putExtra("xml", strarray2);
				 intent.putExtra("postion", postion);
				 startActivity(intent);
				 finish();
			} else {
				Tool.showToast(context, maps.get("ERR_MSG")
						.toString());
			}
		}
	});
}
//锁号交易
public void suohao_csp() {
	String date = ConstantsSet.getDate();
	String time = ConstantsSet.getTimeStr();
	// 访问分行csp无需验证
	// 请求报文头
	Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
	Header type = new BasicHeader("type", ConstantsSet.Type);
	// 报文体内容
	String mc = ConstantsSet.xmlhead(Params.CXKSLB_CODE, date, time,
			"",  "<businessType>03</businessType><customerName>"
					+ "张三"
					+ "</customerName><identityType>"
					+ "01"
					+ "</identityType><identityNo>"
					+ "2207220123456789"
					+ "</identityNo><medicalAccountNo>"
					+ "223456781"
					+ "</medicalAccountNo><bindCardNo>"
					+ "62284750183495406"
					+ "</bindCardNo><scheduleItemCode>"
					+ "001001"
					+ "</scheduleItemCode>");
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
