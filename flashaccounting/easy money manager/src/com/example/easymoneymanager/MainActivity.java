package com.example.easymoneymanager;

import com.example.easymoneymanager.DBAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
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


	public int btn_num =2 ;	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        
        openDB();
//        show();
//        showInList();

        
//-------------------------------------------------------------mauching
        Button btnInMoney = (Button)findViewById(R.id.button3);			//收入按鈕
        btnInMoney.setOnClickListener(inMoneyListener);
        
        
        
//-------------------------------------------------------------mauching
        	

        	Cursor name =myDb.getAll_CT_Rows();
//        	Log.i("button", "begin");
        	create_btn(null , name) ;

        
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
	
	private void delete_btn(){
		
		GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;
		
		grid.removeViewAt(0);
	}
	
	//private String[] opts = new String[] { "123" , "456" };
	
	private void create_btn(String classname , Cursor name){
		
		if (name != null){
		if (name.moveToFirst()) {
			do {
//				Log.i("in the middle", "call by OnCreate");
				
				classname = name.getString(2);

				GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;
				
				Button btn = new Button(this);
		
				btn.setLayoutParams(new LayoutParams(200, 200));
		
				btn.setId(btn_num);
				Log.i("btn_num", Integer.toString(btn_num));
				btn.setText(classname);
		
				grid.addView(btn);
		
				btn_num = btn_num +1 ;
		
			} while(name.moveToNext());
			name.close();
		}}
		else if (name == null && classname != null) {
		GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;
		//Log.i("in the middle", "call by AddClass");
		Button btn = new Button(this);
		//Log.i("in the middle", "setLayoutParams");
		btn.setLayoutParams(new LayoutParams(200, 200));
		//Log.i("in the middle", "set btn ID num");
		btn.setId(btn_num);
		//Log.i("in the middle", "setText");
		btn.setText(classname);
		//Log.i("in the middle", "addView");
		grid.addView(btn);
		//Log.i("in the middle", "finish");
		btn_num = btn_num +1 ;
		}
		// Close the cursor to avoid a resource leak.

	}



    
//------------------------Original code------------------------
    
    private Button AddClass ;
//    private Handler handle;
    private AlertDialog alert ;
    public  EditText classname ;
    private void initView(){

        AddClass = (Button) findViewById(R.id.AddClass);

        AddClass.setOnClickListener(this);
        
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
					Log.i("button name" , classname);
//					add();
					create_btn(classname , null);
					Log.i("insert database" , classname);
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
    	
    	
    	public void DelClass(View v){
    		
    		//myDb.delete_CT_Row(0);
//    	    Log.i("delete database","start");
    		//myDb.delete_CT_Row(1);
//    		Log.i("delete database","seccess");
    		delete_btn();
    		
    		
    	}
    	
    	
    	private void DelAll(){   		
    		
    		myDb.deleteAll_CT();

    	}  


    	
    	@Override
    	public boolean onCreateOptionsMenu(Menu menu) {
      	  // Inflate the menu; this adds items to the action bar if it is present.
      	  //menu.add 參數定義:
      	   //menu.add (group ID , item_ID, 排列順序, item秀在畫面的名稱); 
      	  menu.add(0, 1, 4, "清空所有資料");
      	  menu.add(0, 2, 3, "menu_item2");
      	  menu.add(1, 3, 2, "menu_item3");
      	  menu.add(1, 4, 1, "exit");
      	  super.onCreateOptionsMenu(menu);
      	  return true;
    	}

    	 public boolean onOptionsItemSelected(MenuItem item){
    	  super.onOptionsItemSelected(item);
    	  switch(item.getItemId())
    	  {
    	  case 1:
    	   //這種寫法可以在指定位置秀出message   
    	   Toast toast1=Toast.makeText(this, "這是第一個item", Toast.LENGTH_LONG);
    	   toast1.setGravity(Gravity.CENTER_HORIZONTAL, 50, 50);
    	   toast1.show();
    	   DelAll();
    	   break;
    	  case 2:
    	   Toast toast2=Toast.makeText(this, "這是第二個item", Toast.LENGTH_SHORT);
    	   toast2.show();
    	   break;
    	  case 3:
    	   Toast.makeText(this, "這是第三個item", Toast.LENGTH_SHORT).show();
    	   break;
    	  case 4:
    	   finish();
    	   break;
    	  }
    	  return true;
    	 }


		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    
   
    
    
    

} 		

