package com.example.combo;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CallActivity extends Activity {
	Button callBtn;
	Button dialBtn;
	EditText number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		number = (EditText) findViewById(R.id.edittext1);
		callBtn = (Button) findViewById(R.id.button1);
		dialBtn = (Button) findViewById(R.id.button2);
		MyPhoneListener phoneListener = new MyPhoneListener();
		TelephonyManager telephonyManager = 
			(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);
				callBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String uri = "tel:"+number.getText().toString();
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
					
					startActivity(callIntent);
				}catch(Exception e) {
					Toast.makeText(getApplicationContext(),"Your call has failed...",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
		
		dialBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					String uri = "tel:"+number.getText().toString();
					Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
					startActivity(dialIntent);
				}catch(Exception e) {
					Toast.makeText(getApplicationContext(),"Your call has failed...",
						Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		});
	}
	
	private class MyPhoneListener extends PhoneStateListener {
		 
		private boolean onCall = false;
 
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
 
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				Toast.makeText(CallActivity.this, incomingNumber + " calls you", 
						Toast.LENGTH_LONG).show();
				break;
			
			case TelephonyManager.CALL_STATE_OFFHOOK:
				Toast.makeText(CallActivity.this, "on call...", 
						Toast.LENGTH_LONG).show();
				onCall = true;
				break;

			case TelephonyManager.CALL_STATE_IDLE:
				if (onCall == true) {
					Toast.makeText(CallActivity.this, "restart app after call", 
							Toast.LENGTH_LONG).show();
					Intent restart = getBaseContext().getPackageManager().
						getLaunchIntentForPackage(getBaseContext().getPackageName());
					restart.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(restart);
                    onCall = false;
				}
				break;
			default:
				break;
			}
			
		}
	}

		
		
	}
