package com.example.easymoneymanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.TextView;
import android.app.Dialog;
import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.os.Message;
import android.os.Handler;
//import android.content.Context;
//import android.database.Cursor;
//import android.widget.SimpleCursorAdapter;




// 資料庫待修改
public class MainActivity extends Activity implements OnClickListener {

	
	DBAdapter myDb;

	/*
    private TextView result = null;
    private EditText editName = null;
    private EditText editTel = null;
    private EditText editEmail = null;
    private EditText editId = null;
    private Button btnAdd = null;
    private Button btnDel = null;
    private Button btnUpdate = null;  */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        
        openDB();
//        show();
//        showInList();

        
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

/*	private void displayText(String message) {
        TextView textView = (TextView) findViewById(R.id.textDisplay);
        textView.setText(message);
	}
    */
/*    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDB();
    }*/

	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
		
	}
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

        ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(EMAIL, editEmail.getText().toString());

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.update(TABLE_NAME, values, _ID + "=" + id, null);

        cleanEditText();
    }

    private void cleanEditText(){
        editName.setText("");
        editTel.setText("");
        editEmail.setText("");
        editId.setText("");
    }
    */
    
    
//------------------------Original code------------------------
    
    private Button AddClass ;
//    private Handler handle;
//    private AlertDialog alert , alert2;
    public  EditText classname ;
    private void initView(){

        AddClass = (Button) findViewById(R.id.AddClass);

        AddClass.setOnClickListener(this);
       
    }
   
    
/*    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.AddClass:
            add();
            break;

        default:
            break;
        }

//        show();
//       showInList();
    }
*/  
    
    
    	private void AddClass(){
    		
    	    final Dialog dialog_addclasspg = new Dialog(this, R.style.dialogStyle);
    	    
    	    dialog_addclasspg.setContentView(R.layout.addclasspg);
    	    
    	    dialog_addclasspg.setTitle("新增支出類別");
    	    
    //	    EditText addclassname = (EditText)dialog_addclasspg.findViewById(R.id.addclassname);
    	    
    	    Button Add_ok , Add_cancel ;
    	    Add_ok = (Button)dialog_addclasspg.findViewById(R.id.Add_ok);
    	    Add_cancel = (Button)dialog_addclasspg.findViewById(R.id.Add_cancel);
    	    
    	    Add_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
//					add();
					
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
    	    /*
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //    AlertDialog.Builder builder2 = new AlertDialog.Builder(this);  
            
    		builder.setTitle("新增支出類別");
    		builder.setMessage("請輸入支出類別名稱");
    		builder.setIcon(R.drawable.ic_launcher);
    		
    	/*	builder2.setTitle("新增支出類別");
    		builder2.setMessage(classname.getText().toString());
    		builder2.setIcon(R.drawable.ic_launcher);  */
    		/*
    		classname = new EditText(MainActivity.this);
    		builder.setView(classname);
    		
    		builder.setPositiveButton("新增", new  DialogInterface.OnClickListener() {
    			
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    	            add();

    	    		
    	    	//	alert2.show();
    	            
    			}
    		});
    		
    		builder.setNegativeButton("取消", new  DialogInterface.OnClickListener() {
    			
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				// TODO Auto-generated method stub
    				
    			}
    		});
    		
    		alert = builder.create();
    		
    		alert.show();
    		*/

    		
    		
    	}
/*
        private void add(){
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(classes, addclassname.getText().toString());
            db.insert(flashaccounting, null, values);

        } */
    	
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

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    
    
    
//------------------------Original code end------------------------    
    
    
    
    

} 

