package com.example.easymoneymanager;

import static android.provider.BaseColumns._ID;
import static com.example.easymoneymanager.DbConstants.classes;
import static com.example.easymoneymanager.DbConstants.data;
import static com.example.easymoneymanager.DbConstants.group;
import static com.example.easymoneymanager.DbConstants.list;
import static com.example.easymoneymanager.DbConstants.location;
import static com.example.easymoneymanager.DbConstants.money;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ContentValues;
import android.os.Message;
import android.os.Handler;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.SimpleCursorAdapter;



/* 資料庫待修改
public class SQLiteDemoActivity extends Activity implements OnClickListener {

    private DBHelper dbhelper = null;

    private TextView result = null;
    private ListView listData = null;
    private EditText editName = null;
    private EditText editTel = null;
    private EditText editEmail = null;
    private EditText editId = null;
    private Button btnAdd = null;
    private Button btnDel = null;
    private Button btnUpdate = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        openDatabase();
        show();
        showInList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    private void openDatabase(){
        dbhelper = new DBHelper(this); 
    }

    private void closeDatabase(){
        dbhelper.close();
    }

    private void initView(){
        result = (TextView) findViewById(R.id.txtResult);
        listData = (ListView) findViewById(R.id.listData);
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

}  */

public class MainActivity extends Activity {

private Button AddClass  ;
private Handler handle;
private AlertDialog alert ;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
    	Button btn = (Button) findViewById(R.id.one);
    	btn.setEnabled(false);
		
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
		
		builder.setTitle("新增支出類別");
		builder.setMessage("請輸入支出類別名稱");
		builder.setIcon(R.drawable.ic_launcher);
		
		EditText classname = new EditText(MainActivity.this);
		builder.setView(classname);
		
		builder.setPositiveButton("新增", new  DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
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
