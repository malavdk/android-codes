package com.example.combo;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b1,b2;
		final EditText ed1;
		final EditText ed2;
		final TextView tx1;
		TextView tx2;
		b1= (Button)findViewById(R.id.button1);
		b2= (Button)findViewById(R.id.button2);
		ed1=(EditText)findViewById(R.id.edittext1);
		ed2=(EditText)findViewById(R.id.edittext2);
		b1.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            if(ed1.getText().toString().equals("admin") &&
	         
	            ed2.getText().toString().equals("admin")) {
	               Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
	               Intent intent = new Intent(MainActivity.this,SecondActivity.class);
	               startActivity(intent);
	            }
	            else{
	               Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
	            }
	         }
	      });
	      
	      b2.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	            finish();
	         }
	      });
	}
}
