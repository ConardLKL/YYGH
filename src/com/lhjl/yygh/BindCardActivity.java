package com.lhjl.yygh;

import java.util.HashMap;
import java.util.Map;

import com.lhjl.yygh.util.ClassicXML;
import com.lhjl.yygh.util.Params;
import com.lhjl.yygh.util.SDcard;
import com.lhjl.yygh.util.Tool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BindCardActivity extends Activity {
	private BindCardActivity context;
	private TextView user_name,zhengjianleixing,cusno,cardno,birthday;
	private EditText phoneno;
	private Button bindcardbtn,title_btn_right,title_btn_left;
	private  Map map = new HashMap();
	private  Map maps = new HashMap();
	private   Map mapa = new HashMap();
	private String xml,xml004,xml001;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.bindcard);
	init();
}
void init(){
	title_btn_left = (Button) findViewById(R.id.title_btn_left);
	title_btn_left.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	});
	title_btn_right = (Button) findViewById(R.id.title_btn_right);
	title_btn_right.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 xml = new SDcard().readFileSdcard("YY005.txt");
			
			maps = ClassicXML.readdelbindStringXml(xml);
			Tool.showToast(context, maps.get("medicalAccountNo").toString());
			finish();
		}
	});
	bindcardbtn =(Button) findViewById(R.id.bindcardbtn);
	bindcardbtn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			 xml004 = new SDcard().readFileSdcard("YY004.txt");
			map = ClassicXML.readbindStringXml(xml004);
			Tool.showToast(context, map.get("telNo").toString());
			finish();
		}
	});
	phoneno = (EditText) findViewById(R.id.phoneno);
	user_name = (TextView) findViewById(R.id.user_name);
	zhengjianleixing = (TextView) findViewById(R.id.zhengjianleixing);
	cusno = (TextView) findViewById(R.id.cusno);
	cardno = (TextView) findViewById(R.id.cardno);
	birthday = (TextView) findViewById(R.id.birthday);
	 xml001 = new SDcard().readFileSdcard("YY001.txt");
	mapa = ClassicXML.readzhglbindStringXml(xml001);
	cardno.setText(mapa.get("bindCardNo").toString());
	cusno.setText(mapa.get("identityNo").toString());
	zhengjianleixing.setText(mapa.get("identityType").toString());
	user_name.setText(mapa.get("customerName").toString());
	phoneno.setText(mapa.get("telNo").toString());
}
}
