package com.lhjl.yygh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.LayoutParams;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.boc.bocop.sdk.BOCOPPayApi;
import com.lhjl.yygh.adapter.FragmentTabAdapter;
import com.lhjl.yygh.util.FileSizeUtil;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;

public class MainActivity extends FragmentActivity {
	/**
	 * Called when the activity is first created.
	 */
	private MainActivity context;
	private RadioGroup rgs;
	public List<Fragment> fragments = new ArrayList<Fragment>();

	public String tag = "0";
	private TextView title_id;
	private String phoneno;
	private SharedPreferences sp;
	static Map maps = new HashMap();
	BOCOPPayApi bocopSDKApi = null;
	LinearLayout main_bottom;
	private Button title_btn_left;
	private float density;
	private SDcard scard = new SDcard();
private PopupWindow popupWindow;
private int fileLen;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.main);
		
		DisplayMetrics dm = new DisplayMetrics();
		// 取得窗口属性
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		// 窗口的宽度
		int screenWidth = dm.widthPixels;
		// 窗口高度
		int screenHeight = dm.heightPixels;
		density = dm.density; // 屏幕密度（0.75 / 1.0 / 1.5）
		int height = screenHeight / 11;
		sp = PreferenceManager.getDefaultSharedPreferences(context);
		tag = sp.getString(Params.TAG, "0");
		if(tag.equals("0")){
		
		
		scard.createSDCardDir();
		
		scard.writeSDFile(Params.YY001, "YY001.txt");
		scard.writeSDFile(Params.YY002, "YY002.txt");
		scard.writeSDFile(Params.YY003, "YY003.txt");
		scard.writeSDFile(Params.YY004, "YY004.txt");
		scard.writeSDFile(Params.YY005, "YY005.txt");
		scard.writeSDFile(Params.YY006, "YY006.txt");
		scard.writeSDFile(Params.YY007, "YY007.txt");
		scard.writeSDFile(Params.YY008, "YY008.txt");
		scard.writeSDFile(Params.YY009, "YY009.txt");
		scard.writeSDFile(Params.YY010, "YY010.txt");
		scard.writeSDFile(Params.YY011, "YY011.txt");
		scard.writeSDFile(Params.YY012, "YY012.txt");
		scard.writeSDFile(Params.YY013, "YY013.txt");
		scard.writeSDFile(Params.YY014, "YY014.txt");
		scard.writeSDFile(Params.YY015, "YY015.txt");
		scard.writeSDFile(Params.YY018, "YY018.txt");
		scard.writeSDFile(Params.YY019, "YY019.txt");
		scard.writeSDFile(Params.YY020, "YY020.txt");
		//医生
		scard.writeSDFile(Params.YY0121, "YY0121.txt");
		scard.writeSDFile(Params.YY0122, "YY0122.txt");
		scard.writeSDFile(Params.YY0123, "YY0123.txt");
		scard.writeSDFile(Params.YY0124, "YY0124.txt");
		scard.writeSDFile(Params.YY0125, "YY0125.txt");
		scard.writeSDFile(Params.YY0126, "YY0126.txt");
		scard.writeSDFile(Params.YY0127, "YY0127.txt");
		scard.writeSDFile(Params.YY0128, "YY0128.txt");
		scard.writeSDFile(Params.YY0129, "YY0129.txt");
		scard.writeSDFile(Params.YY0131, "YY0131.txt");
		scard.writeSDFile(Params.YY0132, "YY0132.txt");
		scard.writeSDFile(Params.YY0133, "YY0133.txt");
//			scard.input(Params.YY001, "YY001.txt");
//			scard.input(Params.YY002, "YY002.txt");
//			scard.input(Params.YY003, "YY003.txt");
//			scard.input(Params.YY004, "YY004.txt");
//			scard.input(Params.YY005, "YY005.txt");
//			scard.input(Params.YY006, "YY006.txt");
//			scard.input(Params.YY007, "YY007.txt");
//			scard.input(Params.YY008, "YY008.txt");
//			scard.input(Params.YY009, "YY009.txt");
//			scard.input(Params.YY010, "YY010.txt");
//			scard.input(Params.YY011, "YY011.txt");
//			scard.input(Params.YY012, "YY012.txt");
//			scard.input(Params.YY013, "YY013.txt");
//			scard.input(Params.YY014, "YY014.txt");
//			scard.input(Params.YY015, "YY015.txt");
//			scard.input(Params.YY018, "YY018.txt");
//			scard.input(Params.YY019, "YY019.txt");
//			scard.input(Params.YY020, "YY020.txt");
		
		Editor edit =sp.edit();
		edit.putString(Params.TAG, "1");
		edit.commit();
		}
		
		fragments.add(new TabAFm());
		fragments.add(new TabBFm());
		// fragments.add(new TabCFm());
		// fragments.add(new TabDFm());
		// fragments.add(new TabEFm());

		title_id = (TextView) findViewById(R.id.title_id);
		title_id.setText("预约挂号");
		rgs = (RadioGroup) findViewById(R.id.tabs_rgg);
		rgs.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, height));
		 title_btn_left = (Button)findViewById(R.id.title_btn_left);
		 title_btn_left.setText("大连");
		FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments,
				R.id.tab_content, rgs);
		tabAdapter
				.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
					@Override
					public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
							int checkedId, int index) {
						System.out.println("Extra---- " + index
								+ " checked!!! ");
						if (index == 0) {
							 title_btn_left.setBackground(null);
							 title_btn_left.setText("大  连");
							title_id.setText("预约挂号");
						} else if (index == 1) {
							title_btn_left.setText("");
							 title_btn_left.setBackground(null);
							title_id.setText("我的");
						} 
					}
				});

		title_btn_left.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(title_id.getText().equals("预约挂号")){
					getPopupWindow();
					// 这里是位置显示方式,在屏幕的左侧  
		            popupWindow.showAtLocation(arg0, Gravity.LEFT|Gravity.TOP, 0, (int)(73*density));
				}else{}
				
			}
		});

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		// super.onBackPressed();
		back();
	}
	
	
	
	public void back() {
		new AlertDialog.Builder(this)
				.setTitle("提示")
				.setMessage("确定退出程序?")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
//						bocopSDKApi = BOCOPPayApi.getInstance(context,
//								ConstantsSet.CONSUMER_KEY,
//								ConstantsSet.CONSUMER_SECRET);
//						bocopSDKApi.delOAuthorize(context);
//						Editor edit = sp.edit();
//						edit.remove(Params.Userid);
//						edit.commit();
						android.os.Process.killProcess(android.os.Process.myPid());

					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 取消按钮事件

					}
				}).show();
	}
	/**  
     * 创建PopupWindow 
     */  
    protected void initPopuptWindow() {  
        // TODO Auto-generated method stub  
        // 获取自定义布局文件activity_popupwindow_left.xml的视图  
        View popupWindow_view = getLayoutInflater().inflate(R.layout.activity_popupwindow_left, null,  
                false);  
        
       GridView gridview =  (GridView) popupWindow_view.findViewById(R.id.popuwindow_grid);
      
       //准备要添加的数据条目 
	    List<Map<String, Object>> items = new ArrayList<Map<String,Object>>(); 
	      Map<String, Object> item = new HashMap<String, Object>(); 
	      item.put("textItem", "大连");//按序号添加ItemText   
	      items.add(item); 
	      Map<String, Object> item1 = new HashMap<String, Object>(); 
	      item1.put("textItem", "锦州");//按序号添加ItemText   
	      items.add(item1); 
	      Map<String, Object> item2 = new HashMap<String, Object>(); 
	      item2.put("textItem", "沈阳");//按序号添加ItemText   
	      items.add(item2); 
	      Map<String, Object> item3 = new HashMap<String, Object>(); 
	      item3.put("textItem", "葫芦岛");//按序号添加ItemText   
	      items.add(item3); 
	    //实例化一个适配器 
	    SimpleAdapter adapter = new SimpleAdapter
	    		(this, items, R.layout.popuitem,
	    				new String[]{"textItem"},
	    				new int[]{R.id.popuwind_text});
	    gridview.setAdapter(adapter);
       // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度  
        popupWindow = new PopupWindow(popupWindow_view,  (int)(200*density), LayoutParams.WRAP_CONTENT, true);  
        // 设置动画效果  
//        popupWindow.setAnimationStyle(R.style.AnimationFade);  
        // 点击其他地方消失  
        popupWindow_view.setOnTouchListener(new OnTouchListener() {  
            @Override  
            public boolean onTouch(View v, MotionEvent event) {  
                // TODO Auto-generated method stub  
                if (popupWindow != null && popupWindow.isShowing()) {  
                    popupWindow.dismiss();  
                    popupWindow = null;  
                }  
                return false;  
            }  
        });  
    }  
    /*** 
     * 获取PopupWindow实例 
     */  
    private void getPopupWindow() {  
        if (null != popupWindow) {  
            popupWindow.dismiss();  
            return;  
        } else {  
            initPopuptWindow();  
        }  
    }  
    
    
//    public int size(String fileName){
//    	if (Environment.MEDIA_MOUNTED.equals(Environment
//				.getExternalStorageState())) {
//			// // 创建一个文件夹对象，赋值为外部存储器的目录
//			File sdcardDir = Environment.getExternalStorageDirectory();
//			// 得到一个路径，内容是sdcard的文件夹路径和名字
//			String path = sdcardDir.getPath() + "/YYGH";
////			File dF = new File(path + "//" + "YY018.txt");
//			FileInputStream fis;
//			
//			
//		try {
//			File file = new File(path+ "//" + fileName);
//			fis = new FileInputStream(file);
//			 fileLen = fis.available();
//			System.out.println(fileLen+"******");
//    		} catch (Exception e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
//    		}
//    	return fileLen;
//    }
}
