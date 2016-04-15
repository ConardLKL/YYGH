package com.lhjl.yygh;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends Activity {
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	
	new Thread(new Runnable() {
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);

				startActivity(new Intent(LoginActivity.this,
				// TestWeiXinWhatsNewActivity.class));
						MainActivity.class));
				// 推送
				// PushManager.startWork(getApplicationContext(),
				// PushConstants.LOGIN_TYPE_API_KEY,
				// Utils.getMetaValue(LoginActivity.this,
				// "api_key"));
				finish();
			} catch (Exception e) {

			}
		}
	}).start();

}
}
