package com.example.easymoneymanager;

/*
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Context;
import android.database.Cursor;

public class delclass extends Activity{
	
	ListView list ;
	
	DBAdapter myDb;
	
	String[] stringArray;
	
	private void openDB() {
		myDb = new DBAdapter(this);
		myDb.open();
	}
	private void closeDB() {
		myDb.close();
		
	}

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delclasspg);

		openDB();
		
		ArrayList<String> stringArrayList = new ArrayList<String>();

		Cursor name2 =myDb.getAll_CT_Rows();

		list=(ListView) findViewById(R.id.listView1);
		
		int i =0;
		
		if (name2.moveToFirst()) {
			do {

				 stringArrayList.add(name2.getString(2));

				i=i+1;

			} while(name2.moveToNext());
			name2.close();
		}
		
		 stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);
		
		ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.singlerow,R.id.TextView_del,stringArray);

		list.setAdapter(adapter);
		
	}
	
	class SingleRow {
		String classname;
	}
	
	/*
	class VivzAdapter extends BaseAdapter
	{
		ArrayList<SingleRow> list;
		Context context;
		String[] classnamearray;
		 VivzAdapter(Context c) 
		{
			 list = new ArrayList<delclass.SingleRow>();
			 
			 String[] classname = stringArray;
			 super (c,R.layout.singelrow,R.id.TextView,classname);

			 this.context =c ;

			 this.classnamearray = classname;

		}

		@SuppressLint("ViewHolder")
		@Override
		 public View getView(int position , View convertView , ViewGroup parent){

			 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 View row =inflater.inflate(R.layout.singelrow, parent , false);
			 
			 EditText delclassname = (EditText) row.findViewById(R.id.TextView);
			 //Button del_btn = (Button) row.findViewById(R.id.del_btn);
			 
			 delclassname.setText(classnamearray[position]);
			 //del_btn.setText("刪除");
			 return row;
		 }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	}*/
	
/*}

*/

//---------------------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

//這邊把原本繼承的 Activity 改為 ListActivity

//然後 implements ListView.OnItemClickListener，方便等下捕捉 ListView item 的 click 

public class delclass extends ListActivity implements ListView.OnItemClickListener {
		
		DBAdapter myDb;
	
          List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

          String[] stringArray;
         
  		ArrayList<String> stringArrayList = new ArrayList<String>();
  		
  		private void openDB() {
  			myDb = new DBAdapter(this);
  			myDb.open();
  		}
  		private void closeDB() {
  			myDb.close();
  			
  		}

         @Override
         public void onCreate(Bundle savedInstanceState) {
                  super.onCreate(savedInstanceState);
                  Log.i("delclass", "begin0");
                  openDB();
                  list = getData();
                  MyAdapter adapter = new MyAdapter(this);
                  setListAdapter(adapter); 
                  Log.i("delclass", "begin1");
                  

         }

         //將需要的資料塞到 List 裡面

         private List<Map<String,Object>> getData() {
                  List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();

                   
                    Log.i("delclass", "listmap0");
                    int i = 0 ;
              		Cursor name2 =myDb.getAll_CT_Rows();
            		if (name2.moveToFirst()) {
              			do {
              				HashMap<String,Object> item = new HashMap<String,Object>();
              				item.put("classname", name2.getString(2));

              				 stringArrayList.add(name2.getString(2));
              				 list2.add(item);
              				i=i+1;

              			} while(name2.moveToNext());
              			name2.close();
              		}
            		stringArray = stringArrayList.toArray(new String[stringArrayList.size()]);

                    Log.i("delclass", "listmap1");
                  return list2;
         }


         public final class MyView {
                  public TextView classname;
                  public Button del;
                  public Button edit;
         } 

         // 實作一個 Adapter 繼承 BaseAdapter
         public class MyAdapter extends BaseAdapter {
                  private LayoutInflater inflater;
                  public MyAdapter(Context context) {
                           inflater = LayoutInflater.from(context);
                           Log.i("delclass", "MyAdapter");
                  }

                  @Override
                  public int getCount() {
                           // TODO Auto-generated method stub

                           //回傳這個 List 有幾個 item

                           return list.size();

                  }

                  @Override
                  public Object getItem(int position) {
                           // TODO Auto-generated method stub
                           return null;
                  }

                  @Override
                  public long getItemId(int position) {
                           // TODO Auto-generated method stub
                           return 0;
                  }

                  @Override
                  public View getView(final int position, View convertView, ViewGroup parent) {
                      Log.i("delclass", "getView0");
                           // TODO Auto-generated method stub
                           MyView myviews = null;
                           myviews = new MyView();
                           convertView = inflater.inflate(R.layout.singlerow, null);
                           myviews.classname = (TextView) convertView.findViewById(R.id.TextView_del);
                           myviews.del = (Button) convertView.findViewById(R.id.button2);
                           myviews.edit = (Button) convertView.findViewById(R.id.button1);


                           myviews.classname.setText((String) list.get(position).get("classname"));
                           Log.i("delclass", "getView1");
                           myviews.del.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                             Toast.makeText(delclass.this, stringArray[position]+"del", Toast.LENGTH_SHORT).show();
                                    }
                           }); 
                           Log.i("delclass", "getView2");
                           myviews.edit.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                                        Toast.makeText(delclass.this, stringArray[position]+"edit", Toast.LENGTH_SHORT).show();
                               }
                      }); 
                           return convertView;
                  }
         }

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		}
}