// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.example.easymoneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



//--------------------------CT=CategoryTable--------------------------\\
//----------------------------MT=MemoTable----------------------------\\
/* 兩個資料表，第一個CategoryTable用來存class、Bgroup以及total
 * 			第二個MemoTable用來存list、money、date、location、photo、CategoryTableID
 * 
 *  CategoryTable
 *--------------------------------------------------------------------------------------------------
 * |	_id		|	 group	|	class	|	total	|
 *--------------------------------------------------------------------------------------------------
 *
 *	MemoTable
 *--------------------------------------------------------------------------------------------------
 *|	_id		|	list	|	money	|	date	|       time| 	location	|	photo	|	CategoryTableID|
 *--------------------------------------------------------------------------------------------------
 * */



public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields


	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	
	//CategoryTable definition
	public static final String CT_ROWID = "_id";
	public static final int CT_COL_ROWID = 0;
	public static final String CT_group = "groupname";
	public static final String CT_class = "classname";
	public static final String CT_total = "totalmoney";
	
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int CT_COL_group = 1;
	public static final int CT_COL_class = 2;
	public static final int CT_COL_total = 3;

	//MemoTable definition
	public static final String MT_ROWID = "_id";
	public static final int MT_COL_ROWID = 0;
	public static final String MT_list = "list";
	public static final String MT_money = "money";
	public static final String MT_date = "date";
    public static final String MT_time ="time";
	public static final String MT_location = "location";
	public static final String MT_photo = "photo";
	public static final String MT_CategoryTableID = "CategoryTableID";
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int MT_COL_list = 1;
	public static final int MT_COL_money = 2;
	public static final int MT_COL_date = 3;
	public static final int MT_COL_location = 4;
	public static final int MT_COL_photo = 5;
	public static final int MT_COL_CategoryTableID = 6;


	
	public static final String[] ALL_CT = new String[] {CT_ROWID, CT_group, CT_class,
		CT_total};
	
	public static final String[] ALL_MT = new String[] {MT_ROWID, MT_list, MT_money,
		MT_date,MT_location,MT_photo,MT_CategoryTableID};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "FlashAccounting";
	// Track DB version if a new version of your app changes the format.
	public static int DATABASE_VERSION = 1;	
	
	
	static String create_CT_table(){
			String DATABASE_CREATE_SQL = 
			" create table " + " CategoryTable " 
			+ " ( " + CT_ROWID + " integer primary key autoincrement, "
			+ CT_group + " string not null, "
			+ CT_class + " string not null, "
			+ CT_total + " integer  "
			+ " ); ";
			return DATABASE_CREATE_SQL;
	}
	
	
	static String create_MT_table(){
		String DATABASE_CREATE_SQL = 
		" create table " + " MemoTable " 
		+ " ( " + MT_ROWID + " integer primary key autoincrement, "
		+ MT_list + " string not null, "
		+ MT_money + " integer not null, "
		+ MT_date + " string not null, "
        + MT_time + " string not null,"
		+ MT_location + " string , "
		+ MT_photo + " string , "
		+ MT_CategoryTableID + " int not null  "
		+ " ); ";
		return DATABASE_CREATE_SQL;
	}
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
	public long insert_CT_Row(String groupname, String classname, int total) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(CT_group, groupname);
		initialValues.put(CT_class, classname);
		initialValues.put(CT_total, total);

		// Insert it into the database.
		return db.insert("CategoryTable", null, initialValues);
		
	}
	
	
	// Add a new set of values to the database.
	public long insert_MT_Row(String list, int money, String date,
			 String time,String location, String photo, int CategoryTableID) {

		ContentValues initialValues = new ContentValues();
		initialValues.put(MT_list, list);
		initialValues.put(MT_money, money);
		initialValues.put(MT_date, date);
        initialValues.put(MT_time, time);
		initialValues.put(MT_location, location);
		initialValues.put(MT_photo, photo);
		initialValues.put(MT_CategoryTableID, CategoryTableID);
		// Insert it into the database.

		return db.insert("MemoTable", null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean delete_CT_Row(long rowId) {
		String where = CT_ROWID + "=" + rowId;
		return db.delete("CategoryTable", where, null) != 0;
	}
	
	public boolean delete_MT_Row(long rowId) {
		String where = MT_ROWID + "=" + rowId;
		return db.delete("MemoTable", where, null) != 0;
	}
	
	
	public void deleteAll_CT() {
		Cursor c = getAll_CT_Rows();
		long rowId = c.getColumnIndexOrThrow(CT_ROWID);
		if (c.moveToFirst()) {
			do {
				delete_CT_Row(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}

	// Return all data in the database.
	public Cursor getAll_CT_Rows() {
		String where = null;
		Cursor c = 	db.query(true, "CategoryTable", ALL_CT, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	
	public String get_CT_Row(long rowId) {
		String where = CT_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, "CategoryTable", ALL_CT, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c.getString(2);
	}
	
	public Cursor get_MT_Row(long rowId) {
		String where = MT_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, "MemoTable", ALL_MT, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean update_CT_Row(long rowId,String groupname, String classname, int total) {
		String where = CT_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();

		newValues.put(CT_group, groupname);
		newValues.put(CT_class, classname);
		newValues.put(CT_total, total);
		
		// Insert it into the database.
		return db.update("CategoryTable", newValues, where, null) != 0;
	}
	
	public boolean update_MT_Row(long rowId,String list, int money, String date, String time,
			String location, String group, String photo, int CategoryTableID) {
		String where = MT_ROWID + "=" + rowId;

		ContentValues newValues = new ContentValues();

		newValues.put(MT_list, list);
		newValues.put(MT_money, money);
		newValues.put(MT_date, date);
        newValues.put(MT_time, time);
		newValues.put(MT_location, location);
		newValues.put(MT_photo, photo);
		newValues.put(MT_CategoryTableID, CategoryTableID);
		
		// Insert it into the database.
		return db.update("MemoTable", newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			Log.i("database","start");
			_db.execSQL(create_CT_table());
			Log.i("CT","success");
			_db.execSQL(create_MT_table());
			Log.i("MT","success");
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			//_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}


