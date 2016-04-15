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
import com.lhjl.yygh.domain.DAkeshiListInfo;
import com.lhjl.yygh.domain.KeShiListInfo;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class KeShiActivity extends Activity {
	private KeShiActivity context;
	private Button yiyuan_loading,title_btn_left;
	private TextView yiyuan_content,yiyuan_name,yiyuan_dengji;
	private LinearLayout linear_zhuanjia,linear_keshi;
	private TextView title_id,yiyuan_addr,yiyuan_phone;
	private String name,xml011,postion;
	private boolean tag = false;
	private GridView mgrid;
	private ListView mlist;
	private List<KeShiListInfo> info = new ArrayList<KeShiListInfo>();
	private List<YiShengListInfo> ysinfo =new ArrayList<YiShengListInfo>();
	private KESHIgridviewAdapter mAdapter;
	private YishengListadapter ysAdapter;
	private ImageView yiyuanzhuanjia_choose,yiyuankeshi_choose,yiyuan_head_img;
	private String xml012;
	private String hospitaltel,hospitaladdr,dengji,
	content = "是集医疗、教学、科研于一体的综合性现代化三级甲等医院，经过70余年的发展，现拥有一部、二部、三部及泉涌部四个医疗区，编制床位3700余张，年门急诊量220万余人次、收治住院病人8万余人次、手术量达5.5万余例。";
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.yiyuan_keshi);
	Intent in = getIntent();
	if(in!=null){
		name = in.getStringExtra("name");
		postion = in.getStringExtra("postion");
		hospitaltel = in.getStringExtra("hospitaltel");
		hospitaladdr = in.getStringExtra("hospitaladdr");
		dengji = in.getStringExtra("dengji");
	}
	initview();
}

void initview(){
	yiyuan_dengji = (TextView) findViewById(R.id.yiyuan_dengji);
	yiyuan_dengji.setText(dengji);
	yiyuan_addr = (TextView) findViewById(R.id.yiyuan_addr);
	yiyuan_phone = (TextView) findViewById(R.id.yiyuan_phone);
	yiyuan_addr.setText(hospitaladdr);
	yiyuan_phone.setText(hospitaltel);
	title_id = (TextView) findViewById(R.id.title_id);
	title_id.setText(name);
	title_btn_left = (Button) findViewById(R.id.title_btn_left);
	title_btn_left.setBackgroundResource(R.drawable.back_btn_selector);	
	title_btn_left.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			finish();
			
		}
	});
	yiyuan_head_img = (ImageView) findViewById(R.id.yiyuan_head_img);
	if(postion.equals("0")){
		yiyuan_head_img.setBackgroundResource(R.drawable.yy1);
	}else if(postion.equals("1")){
		yiyuan_head_img.setBackgroundResource(R.drawable.yy2);
	}else {
		yiyuan_head_img.setBackgroundResource(R.drawable.yy3);
	}
	yiyuan_name = (TextView) findViewById(R.id.yiyuan_name);
	yiyuan_name.setText(name);
	 mgrid = (GridView) findViewById(R.id.yiyuan_keshigridview);
	    mlist = (ListView) findViewById(R.id.yiyuan_keshilistview);
	yiyuan_loading = (Button) findViewById(R.id.yiyuan_loading);
	yiyuan_content = (TextView) findViewById(R.id.yiyuan_content);	
	linear_zhuanjia = (LinearLayout) findViewById(R.id.linear_zhuanjia);
	linear_keshi = (LinearLayout) findViewById(R.id.linear_keshi);
	yiyuanzhuanjia_choose = (ImageView) findViewById(R.id.yiyuanzhuanjia_choose);
	yiyuankeshi_choose = (ImageView) findViewById(R.id.yiyuankeshi_choose);
	yiyuan_loading.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(tag == true){
				yiyuan_content.setText("");
				yiyuan_content.setVisibility(View.GONE);
				yiyuan_loading.setBackgroundResource(R.drawable.zkr);
				tag=false;
			}else {
				yiyuan_content.setVisibility(View.VISIBLE);
				yiyuan_content.setText(name + content);
				yiyuan_loading.setBackgroundResource(R.drawable.zkd);
				tag=true;
			}
			
		}
	});
	
	linear_zhuanjia.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mgrid.setVisibility(View.GONE);
			mlist.setVisibility(View.VISIBLE);
			yiyuanzhuanjia_choose.setBackgroundResource(R.drawable.keshi_choose);
			yiyuankeshi_choose.setBackgroundResource(R.drawable.keshi_choose_zhan);
		}
	});
	
	linear_keshi.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			mlist.setVisibility(View.GONE);
			mgrid.setVisibility(View.VISIBLE);
			yiyuanzhuanjia_choose.setBackgroundResource(R.drawable.keshi_choose_zhan);
			yiyuankeshi_choose.setBackgroundResource(R.drawable.keshi_choose);
		}
	});
	
	
	xml011 = new SDcard().readFileSdcard("YY011.txt");
	info = ClassicXML.readkeshiStringXmlOut(xml011);
	if (info.size() != 0) {
		mAdapter = new KESHIgridviewAdapter(context,
				 info , mgrid);
		mgrid.setAdapter(mAdapter);

	}
	
//	 //准备要添加的数据条目 
//    List<Map<String, Object>> items = new ArrayList<Map<String,Object>>(); 
//      Map<String, Object> item = new HashMap<String, Object>(); 
//      item.put("imageItem", R.drawable.xinnei);//添加图像资源的ID   
//      item.put("textItem", "心内科");//按序号添加ItemText   
//      items.add(item); 
//      Map<String, Object> item1 = new HashMap<String, Object>(); 
//      item1.put("imageItem", R.drawable.puwai);//添加图像资源的ID   
//      item1.put("textItem", "普外科");//按序号添加ItemText   
//      items.add(item1); 
//      Map<String, Object> item2 = new HashMap<String, Object>(); 
//      item2.put("imageItem", R.drawable.fuchan);//添加图像资源的ID   
//      item2.put("textItem", "妇产科");//按序号添加ItemText   
//      items.add(item2); 
//      Map<String, Object> item3 = new HashMap<String, Object>(); 
//      item3.put("imageItem", R.drawable.erbihou);//添加图像资源的ID   
//      item3.put("textItem", "耳鼻喉科");//按序号添加ItemText   
//      items.add(item3); 
//      Map<String, Object> item4 = new HashMap<String, Object>(); 
//      item4.put("imageItem", R.drawable.shenjin);//添加图像资源的ID   
//      item4.put("textItem", "神经外科");//按序号添加ItemText   
//      items.add(item4); 
//    //实例化一个适配器 
//    SimpleAdapter adapter = new SimpleAdapter
//    		(this, items, R.layout.keshi_gridviewitem,
//    				new String[]{"imageItem", "textItem"},
//    				new int[]{R.id.keshi_img, R.id.keshi_text});
//	
//   
//    mgrid.setAdapter(adapter);
//    //准备要添加的数据条目 
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
//	mlist.setAdapter(adapterl);
	xml012 = new SDcard().readFileSdcard("YY012.txt");
	ysinfo = ClassicXML.readyishengStringXmlOut(xml012);
	if (ysinfo.size() != 0) {
		ysAdapter = new YishengListadapter(context,
				ysinfo , mlist, name + " - " + "内科");
		mlist.setAdapter(ysAdapter);

	}
    
    
    mgrid.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String keshi= (String)((TextView)arg1.findViewById(R.id.keshi_text)).getText();
			 Intent intent = new Intent();
			 intent.setAction("yishenglist");
			 intent.putExtra("keshi", keshi );
			 intent.putExtra("name_yy", name);
			 intent.putExtra("postion", arg2);
			 startActivity(intent);
		}
	});
    mlist.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String yisheng_type= (String)((TextView)arg1.findViewById(R.id.yisheng_type)).getText();
			String yisheng_keshi= (String)((TextView)arg1.findViewById(R.id.yisheng_keshi)).getText();
			String name = (String)((TextView)arg1.findViewById(R.id.yisheng_name)).getText();
			String free = (String)((TextView)arg1.findViewById(R.id.yisheng_feiyong)).getText();
			Intent intent =new Intent();
			 intent.setAction("yuyuenew");
			 intent.putExtra("yisheng_type", yisheng_type);
			 intent.putExtra("name", name);
			 intent.putExtra("postion", arg2);
			 intent.putExtra("free", free);
			 intent.putExtra("yisheng_keshi", yisheng_keshi);
			 startActivity(intent);
		}
	});
}


//查询科室列表
		public void chaxundingtou_csp() {
			String date = ConstantsSet.getDate();
			String time = ConstantsSet.getTimeStr();
			// 访问分行csp无需验证
			// 请求报文头
			Header clentid = new BasicHeader("clentid", ConstantsSet.CONSUMER_KEY);
			Header type = new BasicHeader("type", ConstantsSet.Type);
			// 报文体内容
			String mc = ConstantsSet.xmlhead(Params.CXKSLB_CODE, date, time,
					"", "<businessType>03</businessType><hospital> "
							+ "军医院"
							+ "</hospital><MainDepartmentId>"
							+ ""
							+ "</MainDepartmentId>");
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
