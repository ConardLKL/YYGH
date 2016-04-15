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
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.boc.bocop.sdk.util.Logger;
import com.bocsoft.ofa.http.asynchttpclient.AsyncHttpResponseHandler;
import com.lhjl.yygh.domain.PaiBanlistInfo;
import com.lhjl.yygh.util.BOCOPCommonInterface;
import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.ConstantsSet;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

public class YuYueActivity extends Activity {
	private YuYueActivity context;
	private TextView title_id,one,yisheng_name,keshi,yisheng_zhicheng,text_time,text_feiyong,text_leixing;
	private Button title_btn_left,popu_btn,popu_x;
	PopupWindow popuWindow;
	private TextView 
	no1,no2,no3,no4,no5,no6,no7,yishengjianjie,
	text1s,text1x,text2s,text2x,text3s,text3x,text4s,text4x,text5s,text5x,text6s,text6x,text7s,text7x;
	String name,keshitxt,yisheng_type,free,shijian,xml013,xml002,data;
	private List<PaiBanlistInfo> info = new ArrayList<PaiBanlistInfo>();
	private ImageView yisheng_img;
	private int postion;
	
	private String jianjie = "，教授，博士，硕士研究生导师，大连医科大学心血管病医院副院长，心内一科主任，中华医学会心血管病学分会冠心病学组委员，大连市心血管病学会副主任委员。1982年工作，88年毕业于大连医科大学，曾2次留学日本。2006年毕业于中国医科大学。从事各种心脏病的治疗，擅长各种心脏病，尤其对胸痛、冠心病的诊断、冠心病心绞痛、心肌梗死等导管球囊扩张、冠脉支架术等介入性诊断治疗技术有深入研究。";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context =this;
		setContentView(R.layout.yuyue);
		Intent in = getIntent();
		if(in!=null){
			free = in.getStringExtra("free");
			postion = in.getIntExtra("postion", -1);
			keshitxt = in.getStringExtra("yisheng_keshi");
			name = in.getStringExtra("name");
			yisheng_type = in.getStringExtra("yisheng_type");
		}
		init();
	}
	
	void init(){
		yishengjianjie = (TextView) findViewById(R.id.yishengjianjie);
		yishengjianjie.setText(name+","+yisheng_type+jianjie);
		yisheng_img = (ImageView) findViewById(R.id.yisheng_img);
		if(postion==0){
			xml013 = new SDcard().readFileSdcard("YY013.txt");
		}else if(postion==1){
			xml013 = new SDcard().readFileSdcard("YY0131.txt");
		}else if(postion==2){
			xml013 = new SDcard().readFileSdcard("YY0132.txt");
		}else if(postion==3){
			xml013 = new SDcard().readFileSdcard("YY0133.txt");
		}else if(postion==4){
			xml013 = new SDcard().readFileSdcard("YY013.txt");
		}
		System.out.println(name);
		if(name.equals("王蓉")){
			yisheng_img.setBackgroundResource(R.drawable.doc1);
		}else if(name.equals("周翔宇")){
			yisheng_img.setBackgroundResource(R.drawable.doc2);
		}else if(name.equals("李响")){
			yisheng_img.setBackgroundResource(R.drawable.doc3);
		}else if(name.equals("王益")){
			yisheng_img.setBackgroundResource(R.drawable.doc4);
		}else if(name.equals("徐建伟")){
			yisheng_img.setBackgroundResource(R.drawable.doc5);
		}else if(name.equals("王晓晓")){
			yisheng_img.setBackgroundResource(R.drawable.doc9);
		}else if(name.equals("牛壮")){
			yisheng_img.setBackgroundResource(R.drawable.doc5);
		}else if(name.equals("吴霞")){
			yisheng_img.setBackgroundResource(R.drawable.doc6);
		}else if(name.equals("赵猛")){
			yisheng_img.setBackgroundResource(R.drawable.doc8);
		}else if(name.equals("王宏")){
			yisheng_img.setBackgroundResource(R.drawable.doc2);
		}else if(name.equals("徐淼")){
			yisheng_img.setBackgroundResource(R.drawable.doc3);
		}else if(name.equals("孟晓辉")){
			yisheng_img.setBackgroundResource(R.drawable.doc5);
		}else if(name.equals("郝仁")){
			yisheng_img.setBackgroundResource(R.drawable.doc2);
		}else if(name.equals("孙嘉")){
			yisheng_img.setBackgroundResource(R.drawable.doc1);
		}else if(name.equals("赵颖")){
			yisheng_img.setBackgroundResource(R.drawable.doc7);
		}else if(name.equals("李健")){
			yisheng_img.setBackgroundResource(R.drawable.doc5);
		}else if(name.equals("朱伟")){
			yisheng_img.setBackgroundResource(R.drawable.doc2);
		}else if(name.equals("牛立伟")){
			yisheng_img.setBackgroundResource(R.drawable.doc8);
		}else if(name.equals("葛珊珊")){
			yisheng_img.setBackgroundResource(R.drawable.doc3);
		}else {
			yisheng_img.setBackgroundResource(R.drawable.doc5);
		}
		
		
		
		
		title_id = (TextView) findViewById(R.id.title_id);
		title_id.setText("医生详情");
		keshi = (TextView) findViewById(R.id.keshi);
		keshi.setText(keshitxt);
		yisheng_name = (TextView) findViewById(R.id.yisheng_name);	
		yisheng_name.setText(name);
		yisheng_zhicheng = (TextView) findViewById(R.id.yisheng_zhicheng);
		yisheng_zhicheng.setText(yisheng_type);
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
		no1 = (TextView) findViewById(R.id.no1);
		
		no2 = (TextView) findViewById(R.id.no2);
		no3 = (TextView) findViewById(R.id.no3);
		no4 = (TextView) findViewById(R.id.no4);
		no5 = (TextView) findViewById(R.id.no5);
		no6 = (TextView) findViewById(R.id.no6);
		no7 = (TextView) findViewById(R.id.no7);
		text1s = (TextView) findViewById(R.id.textno1s);
		text1x = (TextView) findViewById(R.id.textno1x);
		text2s = (TextView) findViewById(R.id.textno2s);
		text2x = (TextView) findViewById(R.id.textno2x);
		text3s = (TextView) findViewById(R.id.textno3s);
		text3x = (TextView) findViewById(R.id.textno3x);
		text4s = (TextView) findViewById(R.id.textno4s);
		text4x = (TextView) findViewById(R.id.textno4x);
		text5s = (TextView) findViewById(R.id.textno5s);
		text5x = (TextView) findViewById(R.id.textno5x);
		text6s = (TextView) findViewById(R.id.textno6s);
		text6x = (TextView) findViewById(R.id.textno6x);
		text7s = (TextView) findViewById(R.id.textno7s);
		text7x = (TextView) findViewById(R.id.textno7x);
		
	
	   info = ClassicXML.readpaibanStringXmlOut(xml013);
	   
	   if(info.size()>0){
		   no1.setText("周一\n"+info.get(0).getAdmitTime());
		   no2.setText("周二\n"+info.get(1).getAdmitTime());
		   no3.setText("周三\n"+info.get(2).getAdmitTime());
		   no4.setText("周四\n"+info.get(3).getAdmitTime());
		   no5.setText("周五\n"+info.get(4).getAdmitTime());
		   no6.setText("周六\n"+info.get(5).getAdmitTime());
		   no7.setText("周日\n"+info.get(6).getAdmitTime());
		  if(info.get(0).getAdmitSeg().equals("0")){
			  text1s.setText("预约");
			  text1x.setText("预约");
			  text1s.setBackgroundResource(R.color.blue);
			  text1x.setBackgroundResource(R.color.blue);
		  }else if(info.get(0).getAdmitSeg().equals("1")){
			  text1s.setText("预约");
			  text1s.setBackgroundResource(R.color.blue);
		  }else if(info.get(0).getAdmitSeg().equals("2")){
			  text1x.setText("预约");
			  text1x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(1).getAdmitSeg().equals("0")){
			  text2s.setText("预约");
			  text2x.setText("预约");
			  text2s.setBackgroundResource(R.color.blue);
			  text2x.setBackgroundResource(R.color.blue);
		  }else if(info.get(1).getAdmitSeg().equals("1")){
			  text2s.setText("预约");
			  text2s.setBackgroundResource(R.color.blue);
		  }else if(info.get(1).getAdmitSeg().equals("2")){
			  text2x.setText("预约");
			  text2x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(2).getAdmitSeg().equals("0")){
			  text3s.setText("预约");
			  text3x.setText("预约");
			  text3s.setBackgroundResource(R.color.blue);
			  text3x.setBackgroundResource(R.color.blue);
		  }else if(info.get(2).getAdmitSeg().equals("1")){
			  text3s.setText("预约");
			  text3s.setBackgroundResource(R.color.blue);
		  }else if(info.get(2).getAdmitSeg().equals("2")){
			  text3x.setText("预约");
			  text3x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(3).getAdmitSeg().equals("0")){
			  text4s.setText("预约");
			  text4x.setText("预约");
			  text4s.setBackgroundResource(R.color.blue);
			  text4x.setBackgroundResource(R.color.blue);
		  }else if(info.get(3).getAdmitSeg().equals("1")){
			  text4s.setText("预约");
			  text4s.setBackgroundResource(R.color.blue);
		  }else if(info.get(3).getAdmitSeg().equals("2")){
			  text4x.setText("预约");
			  text4x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(4).getAdmitSeg().equals("0")){
			  text5s.setText("预约");
			  text5x.setText("预约");
			  text5s.setBackgroundResource(R.color.blue);
			  text5x.setBackgroundResource(R.color.blue);
		  }else if(info.get(4).getAdmitSeg().equals("1")){
			  text5s.setText("预约");
			  text5s.setBackgroundResource(R.color.blue);
		  }else if(info.get(4).getAdmitSeg().equals("2")){
			  text5x.setText("预约");
			  text5x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(5).getAdmitSeg().equals("0")){
			  text6s.setText("预约");
			  text6x.setText("预约");
			  text6s.setBackgroundResource(R.color.blue);
			  text6x.setBackgroundResource(R.color.blue);
		  }else if(info.get(5).getAdmitSeg().equals("1")){
			  text6s.setText("预约");
			  text6s.setBackgroundResource(R.color.blue);
		  }else if(info.get(5).getAdmitSeg().equals("2")){
			  text6x.setText("预约");
			  text6x.setBackgroundResource(R.color.blue);
		  }
		  if(info.get(6).getAdmitSeg().equals("0")){
			  text7s.setText("预约");
			  text7x.setText("预约");
			  text7s.setBackgroundResource(R.color.blue);
			  text7x.setBackgroundResource(R.color.blue);
		  }else if(info.get(6).getAdmitSeg().equals("1")){
			  text7s.setText("预约");
			  text7s.setBackgroundResource(R.color.blue);
		  }else if(info.get(6).getAdmitSeg().equals("2")){
			  text7x.setText("预约");
			  text7x.setBackgroundResource(R.color.blue);
		  }
	   }
	   text1s.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(!text1s.getText().equals("")){
				  shijian = "周一上午";
				  data = info.get(0).getAdmitTime();
				initPopupWindow();
			}
		}
	});
	   text2s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text2s.getText().equals("")){
					  shijian = "周二上午";
					  data = info.get(1).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text3s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text3s.getText().equals("")){
					  shijian = "周三上午";
					  data = info.get(2).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text4s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text4s.getText().equals("")){
					  shijian = "周四上午";
					  data = info.get(3).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text5s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text5s.getText().equals("")){
					  shijian = "周五上午";
					  data = info.get(4).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text6s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text6s.getText().equals("")){
					  shijian = "周六上午";
					  data = info.get(5).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text7s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text7s.getText().equals("")){
					  shijian = "周日上午";
					  data =info.get(6).getAdmitTime() ;
					initPopupWindow();
				}
			}
		});
	   
	   text1x.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(!text1x.getText().equals("")){
				  shijian = "周一下午";
				  data = info.get(0).getAdmitTime() ;
				initPopupWindow();
			}
		}
	});
	   text2x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text2x.getText().equals("")){
					 shijian = "周二下午";
					 data = info.get(1).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text3x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text3x.getText().equals("")){
					 shijian = "周三下午";
					 data = info.get(2).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text4x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text4x.getText().equals("")){
					 shijian = "周四下午";
					 data = info.get(3).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text5x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text5x.getText().equals("")){
					 shijian = "周五下午";
					 data =info.get(4).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text6x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text6x.getText().equals("")){
					 shijian = "周六下午";
					 data = info.get(5).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	   text7x.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!text7x.getText().equals("")){
					 shijian = "周日下午";
					 data = info.get(6).getAdmitTime();
					initPopupWindow();
				}
			}
		});
	}
	
	private void initPopupWindow() {
		View view = this.getLayoutInflater().inflate(R.layout.popuwindow, null);
		popuWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		 WindowManager.LayoutParams params=context.getWindow().getAttributes();  
	        params.alpha=0.8f;  
	            
	    context.getWindow().setAttributes(params);  
		// 这里设置显示PopuWindow之后在外面点击是否有效。如果为false的话，那么点击PopuWindow外面并不会关闭PopuWindow。
		popuWindow.setOutsideTouchable(true);// 不能在没有焦点的时候使用
		popu_btn = (Button) view.findViewById(R.id.popu_btn);
		popu_x= (Button) view.findViewById(R.id.popu_x);
		text_time = (TextView)view.findViewById(R.id.text_time);
		text_time.setText(shijian +" " +data);
		text_feiyong = (TextView) view.findViewById(R.id.text_feiyong);
		text_feiyong.setText(free);
		text_leixing = (TextView) view.findViewById(R.id.text_leixing);
		text_leixing.setText(yisheng_type);
		popu_x.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				 popuWindow.dismiss();
				 WindowManager.LayoutParams params=context.getWindow().getAttributes();  
			      params.alpha=1f;  
			      context.getWindow().setAttributes(params);  
			}
		});
		popu_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.setAction("tijiaodingdan");
				intent.putExtra("yisheng", name);
				intent.putExtra("keshi", keshitxt);
				intent.putExtra("yishengtype", yisheng_type);
				intent.putExtra("time", shijian);
				intent.putExtra("free", free);
				intent.putExtra("data", data);
				intent.putExtra("postion", postion);
				 startActivity(intent);
				 popuWindow.dismiss();
				 WindowManager.LayoutParams params=context.getWindow().getAttributes();  
			      params.alpha=1f;  
			      context.getWindow().setAttributes(params);  
			}
		});
		popuWindow.showAtLocation(view, Gravity.CENTER | Gravity.CENTER, 0, 0);
		popuWindow.setFocusable(true);
		popuWindow.update();
		
		
	}
	
	
	//查询排班表
			public void chaxun_csp() {
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
								+ "</departmentId><doctorId>"
								+ "001"
								+ "</doctorId><destineDate>"
								+ "20120215"
								+ "</destineDate><findType>"
								+ "2"
								+ "</findType><pagesize>"
								+ "10"
								+ "</pagesize><currentIndex>"
								+ "1"
								+ "</currentIndex>");
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
//										info = ClassicXML.readStringXmlOut(msg);
//										if (info.size() != 0) {
//											mAdapter = new MyAdapter(context,
//													R.layout.cxdt_list_item, info);
//											mListView.setAdapter(mAdapter);
	//
//										} else {
//											Tool.showToast(context, "没有定投记录！");
//										}
									} else {
										Tool.showToast(context, "系统错误！");
									}
								}
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

//							if (ppd != null) {
//								ppd.dismiss();
//							}
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
//							if (ppd != null) {
//								ppd.dismiss();
//							}
							Tool.showToast(context, "系统错误！");
						}

					});
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					if (ppd != null) {
//						ppd.dismiss();
//					}
				}

			}
	
}
