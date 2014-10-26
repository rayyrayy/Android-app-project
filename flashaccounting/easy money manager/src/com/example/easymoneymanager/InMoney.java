package com.example.easymoneymanager;

import android.app.Activity;
import android.os.Bundle;



public class InMoney extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inmoney);
		
	}
    @Override
    protected void onStart(){
    	super.onStart();
    }
    @Override
    protected void onResume(){
    	super.onResume();
    }
    protected void onRestrart(){
    	super.onRestart();
    }
    @Override
    protected void onPause(){
    	super.onPause();
    }
    @Override
    protected void onStop(){
    	super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
