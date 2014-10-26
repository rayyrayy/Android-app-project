package com.example.easymoneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import android.widget.Toast;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ContentValues;
import android.content.Intent;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;

import android.os.Message;
import android.os.Handler;
import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;




// 資料庫待修改
public class MainActivity extends Activity implements OnClickListener {

	DBAdapter myDb;

	public int btn_num =1 ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        
        openDB();
//        show();
//        showInList();

        

  /*  
//-------------------------------------------------------------mauching
        Button btnInMoney = (Button)findViewById(R.id.button3);			//收入按鈕
        btnInMoney.setOnClickListener(inMoneyListener);
        
        
        
//-------------------------------------------------------------mauching
*/
      
        for (int i = 1 ; i < 3 ; i++){
        	String name =myDb.get_CT_Row(i);
        	if (name != null)create_btn(name) ;
        }
        
        
        

//------------------------Original code------------------------

    		
    	AddClass = (Button)findViewById(R.id.AddClass);
    		
    	AddClass.setOnClickListener(new OnClickListener() {
    			
    	@Override
    	public void onClick(View v) {
    				
    			AddClass();
    			// TODO Auto-generated method stub
    				
    		}
    	});          
//------------------------Original code end------------------------   

    }


/*    
  //-------------------------Activity 生命週期------------------------------------mauching
    
	@Override
	protected void onStart() {		
		super.onStart();
		Toast.makeText(getApplicationContext(),"onStart(1)",Toast.LENGTH_SHORT).show();
	} 
	
	@Override
	protected void onResume() {		
		super.onResume();
		Toast.makeText(getApplicationContext(),"onResume(1)",Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onRestart() {		
		super.onRestart();
		Toast.makeText(getApplicationContext(),"onRestart(1)",Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {		
		super.onPause();
		Toast.makeText(getApplicationContext(),"onPause(1)",Toast.LENGTH_SHORT).show();
	} 
	
	@Override
	protected void onStop() {		
		super.onStop();
		Toast.makeText(getApplicationContext(),"onStop(1)",Toast.LENGTH_SHORT).show();
	}
	
    @Override
	protected void onDestroy() {    	
		super.onDestroy();
		Toast.makeText(getApplicationContext(),"onDestroy(1)",Toast.LENGTH_SHORT).show();
    }
*/

 //-------------------------------------------------------------mauching
 //-------------聽btnInMoeny按鈕事件，並呼叫新的Activity-------------------mauching   
    private Button.OnClickListener inMoneyListener = new Button.OnClickListener(){
    	public void onClick(View v){
    		Intent intent = new Intent();
    		intent.setClass(MainActivity.this, InMoney.class);
    		startActivity(intent);
    	
    	}
    };
//---------------------------------------------------------------mauching    



 

	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
		
	}
	
	
	
	//private String[] opts = new String[] { "123" , "456" };
	
	private void create_btn(String classname){
		
		GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;
		
		Button btn = new Button(this);
		
		btn.setLayoutParams(new LayoutParams(200, 200));
		
		btn.setId(btn_num);
		
		btn.setText(classname);
		
		grid.addView(btn);
		
		btn_num = btn_num +1 ;
	}


    
//------------------------Original code------------------------
    
    private Button AddClass , DelClass;
//    private Handler handle;
    private AlertDialog alert ;
    public  EditText classname ;
    private void initView(){

        AddClass = (Button) findViewById(R.id.AddClass);

        AddClass.setOnClickListener(this);
        
        DelClass = (Button) findViewById(R.id.DelClass);
        
        DelClass.setOnClickListener(this);
        
    }
   
    
    	private void AddClass(){
    		
    	    final Dialog dialog_addclasspg = new Dialog(this, R.style.dialogStyle);
    	    
    	    dialog_addclasspg.setContentView(R.layout.addclasspg);
    	    
    	    dialog_addclasspg.setTitle("新增支出類別");
    	    
    	    final EditText addclassname = (EditText)dialog_addclasspg.findViewById(R.id.addclassname);
    	    
    	    Button Add_ok , Add_cancel ;
    	    Add_ok = (Button)dialog_addclasspg.findViewById(R.id.Add_ok);
    	    Add_cancel = (Button)dialog_addclasspg.findViewById(R.id.Add_cancel);
    	    
    	    Add_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Editable str;
					str = addclassname.getText();
					String classname =str.toString();
					Log.i("edit text" , classname);
//					add();
					create_btn(classname);
					myDb.insert_CT_Row("personal", classname, 0);
					dialog_addclasspg.dismiss();
				}
			});
    	    
    	    Add_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					
					dialog_addclasspg.dismiss();
				}
			});
    	    
    	   dialog_addclasspg.show() ;
  
    		
    		
    	}

    	public void querytable(View v){
         
    		for (int i = 1 ; i < 3 ; i++){
            	String name =myDb.get_CT_Row(i);
            	if (name != null) ; Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            }
    	}
    	
    	/*
    	public void DelClass(){
    		
    		myDb.delete_CT_Row(0);
    		myDb.delete_CT_Row(1);
    		
    	}
    	
    	
    	private void DelClass(){
    		
    		
    		AlertDialog alert = null ;
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		

    		builder.setMultiChoiceItems(opts, new boolean[]{false , false}, new OnMultiChoiceClickListener()){
    			
    		};
    		
    		alert = builder.create();
    		
    		alert.show();
    		
    		

    	}  */


    	
    	@Override
    	public boolean onCreateOptionsMenu(Menu menu) {
    		// Inflate the menu; this adds items to the action bar if it is present.
    		getMenuInflater().inflate(R.menu.main, menu);
    		return true;
    	}



		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    
   
    
    
    

} 		

