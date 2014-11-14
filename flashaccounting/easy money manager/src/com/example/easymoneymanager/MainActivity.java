package com.example.easymoneymanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;




public class MainActivity extends Activity implements OnClickListener {
	
	ListView list;
	//呼叫DB
	DBAdapter myDb;

	//類別按鈕計數
	public int btn_num = 0 ;	
	//Button[] btn = new Button[9];
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        openDB();

        
//-------------------------------------------------------------mauching
        Button btnInMoney = (Button)findViewById(R.id.button3);			//收入按鈕
        btnInMoney.setOnClickListener(inMoneyListener);
        
        
        
//-------------------------------------------------------------mauching

        	Cursor name =myDb.getAll_CT_Rows();
//        	Log.i("button", "begin");
        	create_btn(null , name) ;


    }



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
	
	private void delete_btn(int sum){
		
		if (sum == btn_num){
			for (int i=0 ; i<sum ; i++){
			
			GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;
			
			grid.removeViewAt(0);
			}
		}
		else ;
	}
	
	
	private void create_btn(String classname , Cursor name){
		
		if (name != null){
			if (name.moveToFirst()) {
				do {
					classname = name.getString(2);
	
					GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;			
					
					Button btn = new Button(this);
					btn.setGravity(0x77);
					btn.setLayoutParams(new LayoutParams(230, 230));
					btn.setId(btn_num);
					Log.i("btn_num", Integer.toString(btn_num));
					btn.setText(classname);
			
					final String buttonevent = classname;
				    btn.setOnClickListener(new View.OnClickListener() {
				        public void onClick(View v) {
				            //your desired functionality
				        	Log.i("button event", buttonevent);
				        }});
				    
				    grid.addView(btn);
				    
					btn_num = btn_num +1 ;
			
				} while(name.moveToNext());
				name.close();
			}
		}
		else if (classname != null) {
			
		GridLayout grid = (GridLayout) findViewById(R.id.gridLayout1) ;

		Button btn = new Button(this);

		btn.setGravity(0x77);

		btn.setLayoutParams(new LayoutParams(230, 230));

		btn.setId(btn_num);

		btn.setText(classname);

		final String buttonevent = classname;

	    btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            //your desired functionality
	        	Log.i("button event", buttonevent);
	        }});

		grid.addView(btn);

		btn_num = btn_num +1 ;

		}
	}



    
//------------------------Original code------------------------
    

    public  EditText classname ;
   
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

    	
    	private void DelClass() {

    		Cursor name =myDb.getAll_CT_Rows();
    		
    		String[] mylist = {""} ;
    		String stemp = "" ;
    		if (name.moveToFirst()) {
				do {
					int i =0;
					Log.i("name", name.getString(2));
					mylist[i] = name.getString(2);
					Log.i("mylist", mylist[i]);
					i=i+1;

				} while(name.moveToNext());
				name.close();
			}
			Log.i("stemp", stemp);
    		list = (ListView) findViewById(R.id.listView1);

    		String[] classname = mylist;
    		Log.i("test", "stuck3");
    	    final Dialog dialog_delclasspg = new Dialog(this, R.style.dialogStyle);
    	    
    	    dialog_delclasspg.setContentView(R.layout.delclasspg);
    	    
    	    dialog_delclasspg.setTitle("刪除類別");
    		Log.i("test", "stuck4");
    	    VivzAdapter  adapter = new VivzAdapter(this, classname);
    	    Log.i("test", "stuck4.1");
    	  
    	    dialog_delclasspg.show() ;
    	    
    	      list.setAdapter(adapter);	
    		Log.i("test", "stuck5");
    	}
 	
    	class VivzAdapter extends ArrayAdapter<String>
    	{
    		Context context;
    		String[] classnamearray;
    		 VivzAdapter(Context c , String[] classname) 
    		{
    			 super (c,R.layout.singelrow,R.id.TextView,classname);

    			 this.context =c ;

    			 this.classnamearray = classname;

			}

			@SuppressLint("ViewHolder")
			@Override
    		 public View getView(int position , View convertView , ViewGroup parent){
    			 Log.i("test", "stuck7.1");
    			 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			 View row =inflater.inflate(R.layout.singelrow, parent , false);
    			 
    			 EditText delclassname = (EditText) row.findViewById(R.id.TextView);
    			 //Button del_btn = (Button) row.findViewById(R.id.del_btn);
    			 
    			 delclassname.setText(classnamearray[position]);
    			 //del_btn.setText("刪除");
    			 return row;
    		 }
    	}
    	
    	public void DelClass(View v){
    		
    		//myDb.delete_CT_Row(0);
//    	    Log.i("delete database","start");
    		//myDb.delete_CT_Row(1);
//    		Log.i("delete database","seccess");
    		//delete_btn();
    			
    	}
    	
    	
    	private void DelAll(){   		
    		
    		myDb.deleteAll_CT();
    		delete_btn(btn_num);

    	}  


    	
    	@Override
    	public boolean onCreateOptionsMenu(Menu menu) {
      	  // Inflate the menu; this adds items to the action bar if it is present.
      	  //menu.add 參數定義:
      	   //menu.add (group ID , item_ID, 排列順序, item秀在畫面的名稱); 
      	  menu.add(0, 1, 4, "清空所有類別");
      	  menu.add(0, 2, 3, "顯示時間");
      	  menu.add(0, 3, 2, "刪除類別");
      	  menu.add(0, 4, 1, "exit");
      	  //super.onCreateOptionsMenu(menu);
          MenuInflater inflater = getMenuInflater();
          inflater.inflate(R.menu.main, menu);
          return true;
    	}

    	 public boolean onOptionsItemSelected(MenuItem item){
    	  super.onOptionsItemSelected(item);
    	  switch(item.getItemId())
    	  {
    	  case R.id.AddClass:
    	   AddClass();
    	   break;
    	  case 1:
    	   //這種寫法可以在指定位置秀出message   
    	   Toast toast1=Toast.makeText(this, "資料庫已清空", Toast.LENGTH_LONG);
    	   toast1.setGravity(Gravity.CENTER_HORIZONTAL, 50, 50);
    	   toast1.show();
    	   DelAll();
    	   break;
    	  case 2:
    		  SimpleDateFormat s = new SimpleDateFormat("dd/MM-yyyy-hh-mm-ss");
    		  String format = s.format(new Date());
    	   Toast toast2=Toast.makeText(this, format, Toast.LENGTH_SHORT);
    	   toast2.show();
    	   break;
    	  case 3:
    	   DelClass();
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

