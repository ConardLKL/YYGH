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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class YuYueNewActivity extends Activity {
	private YuYueNewActivity context;
	private Button title_btn_left,popu_btn,popu_x;
	PopupWindow popuWindow;
	private TextView 
	yishengjianjie,title_id,keshi,yisheng_name,yisheng_zhicheng,text_time,text_leixing,text_feiyong;
	String name,keshitxt,yisheng_type,free,shijian,xml013,xml002,data;
	private List<PaiBanlistInfo> info = new ArrayList<PaiBanlistInfo>();
	private ImageView yisheng_img;
	private int postion;
GridView gridView;
private static LayoutInflater inflater=null;
	private String jianjie = "，教授，博士，硕士研究生导师，大连医科大学心血管病医院副院长，心内一科主任，中华医学会心血管病学分会冠心病学组委员，大连市心血管病学会副主任委员。1982年工作，88年毕业于大连医科大学，曾2次留学日本。2006年毕业于中国医科大学。从事各种心脏病的治疗，擅长各种心脏病，尤其对胸痛、冠心病的诊断、冠心病心绞痛、心肌梗死等导管球囊扩张、冠脉支架术等介入性诊断治疗技术有深入研究。";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context =this;
		setContentView(R.layout.hgridview);
		Intent in = getIntent();
		if(in!=null){
			free = in.getStringExtra("free");
			postion = in.getIntExtra("postion", -1);
			keshitxt = in.getStringExtra("yisheng_keshi");
			name = in.getStringExtra("name");
			yisheng_type = in.getStringExtra("yisheng_type");
			
		}
		init();
		
		setGridView();
	}
	
	void init(){
		  gridView = (GridView) findViewById(R.id.grid);
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
	}
	 /**设置GirdView参数，绑定数据*/
    private void setGridView() {
    	  info = ClassicXML.readpaibanStringXmlOut(xml013);
        int size = info.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length ) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
//        gridView.setHorizontalSpacing(1); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
        HGridAdapter adapter = new HGridAdapter(context, info,
                gridView);
        gridView.setAdapter(adapter);
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
		text_time.setText( data);
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
//				intent.putExtra("time", shijian);
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
			/**
			 * 
			 * 
			 * @author Administrator
			 * 
			 */
			public class HGridAdapter extends ArrayAdapter<PaiBanlistInfo> {

				private GridView Mlist;
				// private Testload asyncImageLoader;
//				private AsyncImageLoader asyncImageLoader;
				Context context;
				public SharedPreferences sp;
				 
				public HGridAdapter(Activity activity,
						List<PaiBanlistInfo> imageAndTexts, GridView Mlist) {
					super(activity, 0, imageAndTexts);
					this.Mlist = Mlist;
					 inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//					 ImageCacheManager cacheMgr = new ImageCacheManager(activity);
//				        asyncImageLoader = new AsyncImageLoader(activity, cacheMgr.getMemoryCache(), cacheMgr.getPlacardFileCache());
				}

				public View getView(final int position, View convertView, ViewGroup parent) {
					Activity activity = (Activity) getContext();
					sp = PreferenceManager.getDefaultSharedPreferences(activity);
					// Inflate the views from XML
					View rowView = convertView;
					GdCache gdCache;
					if (rowView == null) {
						LayoutInflater inflater = activity.getLayoutInflater();
						rowView = inflater.inflate(R.layout.hgridview_item, null);
						gdCache = new GdCache(rowView);
						rowView.setTag(gdCache);
					} else {
						gdCache = (GdCache) rowView.getTag();
					}
					PaiBanlistInfo imageAndText = getItem(position);
					
					// Set the text on the TextView
					final TextView tvpm = gdCache.getTvpm();
					TextView tvtitle = gdCache.getTvtitle();
					final TextView tvam = gdCache.getTvam();
					
					if(imageAndText.getAdmitSeg().equals("0")){
						tvpm.setText("预约");
						tvam.setText("预约");
						tvam.setBackgroundResource(R.color.blue);
						tvpm.setBackgroundResource(R.color.blue);
					}
					else if(imageAndText.getAdmitSeg().equals("1")){
						tvam.setText("预约");
						tvam.setBackgroundResource(R.color.blue);
					}
					else if(imageAndText.getAdmitSeg().equals("2")){
						tvpm.setText("预约");
						tvpm.setBackgroundResource(R.color.blue);
					}
						tvtitle.setText(imageAndText.getAdmitTime());
						
						tvam.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								if(!tvam.getText().equals("")){
									  data = info.get(position).getAdmitTime() +"(上午)";
									  initPopupWindow();
								}
							}
						});
						
tvpm.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								if(!tvpm.getText().equals("")){
									  data = info.get(position).getAdmitTime()+"(下午)";
									initPopupWindow();
								}
							}
						});
					return rowView;
				}

				class GdCache {

					private View baseView;
					private TextView tvtitle;
					private ImageView imageView;
					private TextView tvam;
					private TextView tvpm;
					public GdCache(View baseView) {
						this.baseView = baseView;
					}

					public TextView getTvpm() {
						if (tvpm == null) {
							tvpm = (TextView) baseView.findViewById(R.id.tvpm);
						}
						return tvpm;
					}
					public TextView getTvtitle() {
						if (tvtitle == null) {
							tvtitle = (TextView) baseView.findViewById(R.id.tvtitle);
						}
						return tvtitle;
					}
					public TextView getTvam() {
						if (tvam == null) {
							tvam = (TextView) baseView.findViewById(R.id.tvam);
						}
						return tvam;
					}
				}
			}
}
