package com.example.easymoneymanager;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.os.Message;
import android.os.Handler;


public class MainActivity extends Activity {

private Button AddClass ;
private Handler handle;
private AlertDialog dialog ;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AddClass = (Button)findViewById(R.id.AddClass);
		
		AddClass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AddClass();
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void AddClass(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle("新增類別");
		builder.setMessage("請輸入類別名稱");
		builder.setIcon(R.drawable.ic_launcher);
		
		EditText classname = new EditText(MainActivity.this);
		builder.setView(classname);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
