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

    private DBAdapter dbhelper = null;

    private TextView result = null;
    private EditText editName = null;
    private EditText editTel = null;
    private EditText editEmail = null;
    private EditText editId = null;
    private Button btnAdd = null;
    private Button btnDel = null;
    private Button btnUpdate = null;
    private Button btnInMoney = null;		//暫時挪作他使用的收入按鈕


	
	DBAdapter myDb;


	public int btn_num =0 ;
	
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

      /*  
        for (int i = 0 ; i < 9 ; i++){
        	String name =myDb.get_CT_Row(i);
        	if (name != null)create_btn(name) ;
        }
        */
        
        

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
        closeDatabase();
    }

    private void openDatabase(){
        dbhelper = new DBAdapter(this); 
    }

    private void closeDatabase(){
        dbhelper.close();
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
    
/*
    private void initView(){
        result = (TextView) findViewById(R.id.txtResult);
        editName = (EditText) findViewById(R.id.editName);
        editTel = (EditText) findViewById(R.id.editTel);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editId = (EditText) findViewById(R.id.editId);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnAdd:
            add();
            break;

        case R.id.btnDel:
            del();
            break;

        case R.id.btnUpdate:
            update();
            break;

        default:
            break;
        }

        show();
        showInList();
    }

    private void add(){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());
        db.insert(TABLE_NAME, null, values);

        cleanEditText();
    }

    private Cursor getCursor(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columns = {_ID, NAME, TEL, EMAIL};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        startManagingCursor(cursor);

        return cursor;
    }

    private void show(){

        Cursor cursor = getCursor();

        StringBuilder resultData = new StringBuilder("RESULT: \n");

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tel = cursor.getString(2);
            String email = cursor.getString(3);

            resultData.append(id).append(": ");
            resultData.append(name).append(": ");
            resultData.append(tel).append(": ");
            resultData.append(email).append(": ");
            resultData.append("\n");
        }

        result.setText(resultData);
    }

    private void showInList(){

        Cursor cursor = getCursor();

        String[] from = {_ID, NAME, TEL, EMAIL};
        int[] to = {R.id.txtId, R.id.txtName, R.id.txtTel, R.id.txtEmail};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_item, cursor, from, to);
        listData.setAdapter(adapter);
    }

    private void del(){
        String id = editId.getText().toString();

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + "=" + id, null);

        cleanEditText();
    }

    private void update(){
        String id = editId.getText().toString();
=======
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
	}
>>>>>>> c4852b2307bda187806f072cc32a3fe24bf47f88

    
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
         
    		//for (int i = 0 ; i < 1 ; i++){
            	String name =myDb.get_CT_Row(1);
            	if (name != null) ; Toast.makeText(this, name, Toast.LENGTH_LONG).show();
         //   }
    		//Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
    	}
    	
    	public void DelClass(){
    		
    		myDb.delete_CT_Row(0);
    		myDb.delete_CT_Row(1);
    		
    	}
    	
    	/*
    	private void DelClass(){
    		
    		
    		AlertDialog alert = null ;
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		

    		builder.setMultiChoiceItems(opts, new boolean[]{false , false}, new OnMultiChoiceClickListener()){
    			
    		};
    		
    		alert = builder.create();
    		
    		alert.show();
    		
    		
<<<<<<< HEAD
    	}

        private void add(){
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
 //           values.put(classes, addclassname.getText().toString());
            db.insert(flashaccounting, null, values);

        }
=======
    		
    	}*/
    	
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

