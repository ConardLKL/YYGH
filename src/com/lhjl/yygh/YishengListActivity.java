package com.lhjl.yygh;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.adapter.KESHIgridviewAdapter;
import com.lhjl.yygh.adapter.YishengListadapter;
import com.lhjl.yygh.domain.YiShengListInfo;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class YishengListActivity extends Activity {
	private ListView Mlist;
	private YishengListActivity context;
	private TextView title_id;
	private Button back;
	private YishengListadapter mAdapter;
	private String keshi,name_yy,xml012,xml0121,xml0122,xml0123,xml0124,xml0125,xml0126,xml0127,xml0128,xml0129;
	private int postion;
	private List<YiShengListInfo> info =new ArrayList<YiShengListInfo>();
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context =this;
	setContentView(R.layout.yishenglist);
	Intent in = getIntent();
	if(in!=null){
		name_yy = in.getStringExtra("name_yy");
		keshi = in.getStringExtra("keshi");
		postion = in.getIntExtra("postion", -1);
	}
	init();
}
void init(){
	title_id = (TextView) findViewById(R.id.title_id);
	title_id.setText("选择医生");
	back = (Button) findViewById(R.id.title_btn_left);
	back.setBackgroundResource(R.drawable.back_btn_selector);
	Mlist = (ListView) findViewById(R.id.yisheng_list);
	back.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	if(postion==0){
	xml012 = new SDcard().readFileSdcard("YY012.txt");
	info = ClassicXML.readyishengStringXmlOut(xml012);
	}else if(postion==1){
		xml0121 = new SDcard().readFileSdcard("YY0121.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0121);
	}else if(postion==2){
		xml0122 = new SDcard().readFileSdcard("YY0122.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0122);
	}
	else if(postion==3){
		xml0123 = new SDcard().readFileSdcard("YY0123.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0123);
	}
	else if(postion==4){
		xml0124 = new SDcard().readFileSdcard("YY0124.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0124);
	}
	else if(postion==5){
		xml0125 = new SDcard().readFileSdcard("YY0125.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0125);
	}
	else if(postion==6){
		xml0126 = new SDcard().readFileSdcard("YY0126.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0126);
	}
	else if(postion==7){
		xml0127 = new SDcard().readFileSdcard("YY0127.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0127);
	}
	else if(postion==8){
		xml0128 = new SDcard().readFileSdcard("YY0128.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0128);
	}
	else if(postion==9){
		xml0129 = new SDcard().readFileSdcard("YY0121.txt");
		info = ClassicXML.readyishengStringXmlOut(xml0129);
	}
	if (info.size() != 0) {
		
			mAdapter = new YishengListadapter(context,
					 info , Mlist, name_yy + " - " + keshi);
			Mlist.setAdapter(mAdapter);
	}
//	 //准备要添加的数据条目 
//    List<Map<String, Object>> itemls = new ArrayList<Map<String,Object>>(); 
//      Map<String, Object> iteml = new HashMap<String, Object>(); 
//      iteml.put("imageItem", "站三");//添加图像资源的ID   
//      iteml.put("textItem", "心内科");//按序号添加ItemText   
//      itemls.add(iteml); 
//      Map<String, Object> iteml1 = new HashMap<String, Object>(); 
//      iteml1.put("imageItem", "李四");//添加图像资源的ID   
//      iteml1.put("textItem", "普外科");//按序号添加ItemText   
//      itemls.add(iteml1); 
//      Map<String, Object> iteml2 = new HashMap<String, Object>(); 
//      iteml2.put("imageItem", "赵武");//添加图像资源的ID   
//      iteml2.put("textItem", "妇产科");//按序号添加ItemText   
//      itemls.add(iteml2); 
//      Map<String, Object> iteml3 = new HashMap<String, Object>(); 
//      iteml3.put("imageItem", "孙");//添加图像资源的ID   
//      iteml3.put("textItem", "耳鼻喉科");//按序号添加ItemText   
//      itemls.add(iteml3); 
//      Map<String, Object> iteml4 = new HashMap<String, Object>(); 
//      iteml4.put("imageItem", "杨");//添加图像资源的ID   
//      iteml4.put("textItem", "神经外科");//按序号添加ItemText   
//      itemls.add(iteml4); 
//      
//      
//      //实例化一个适配器 
//      SimpleAdapter adapterl = new SimpleAdapter
//      		(this, itemls, R.layout.yisheng_listitem,
//      				new String[]{"imageItem", "textItem"},
//      				new int[]{R.id.yisheng_name, R.id.yisheng_keshi});
    Mlist.setAdapter(mAdapter);
    Mlist.setOnItemClickListener(new OnItemClickListener(
    		) {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					String yisheng_type= (String)((TextView)arg1.findViewById(R.id.yisheng_type)).getText();
					String yisheng_keshi= (String)((TextView)arg1.findViewById(R.id.yisheng_keshi)).getText();
					String name = (String)((TextView)arg1.findViewById(R.id.yisheng_name)).getText();
					String free = (String)((TextView)arg1.findViewById(R.id.yisheng_feiyong)).getText();
					Intent intent =new Intent();
					 intent.setAction("yuyuenew");
					 intent.putExtra("postion", arg2);
					 intent.putExtra("yisheng_type", yisheng_type);
					 intent.putExtra("name", name);
					 intent.putExtra("free", free);
					 intent.putExtra("yisheng_keshi", yisheng_keshi);
					 startActivity(intent);
				}
	});
}

//查询医生列表
		public void chaxundingtou_csp() {
			String date = ConstantsSet.getDate();
			String time = ConstantsSet.getTimeStr();
			// 访问分行csp无需验证
			// 请求报文头
			Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
			Header type = new BasicHeader("type", ConstantsSet.Type);
			// 报文体内容
			String mc = ConstantsSet.xmlhead(Params.CXKSLB_CODE, date, time,
					"",  "<businessType>03</businessType><departmentId>"
							+ "101"
							+ "</departmentId><destineDate>"
							+ "</destineDate>");
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
									Tool.showToast(context, "系统错误！");
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
						Tool.showToast(context, "系统错误！");
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
