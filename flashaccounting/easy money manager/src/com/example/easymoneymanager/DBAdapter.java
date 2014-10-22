// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.example.easymoneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String expenditure_ROWID = "_id";
	public static final int expenditure_COL_ROWID = 0;
	public static final String receipt_ROWID = "_id";
	public static final int receipt_COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	//支出欄位
	public static final String expenditure_list = "expenditure_list";
	public static final String expenditure_date = "expenditure_date";
	public static final String expenditure_money = "expenditure_money";
	public static final String expenditure_location = "expenditure_location";
	public static final String expenditure_photo = "expenditure_photo";

	
	//收入欄位
	public static final String receipt_list = "receipt_list";
	public static final String receipt_date = "receipt_date";
	public static final String receipt_money = "receipt_money";
	public static final String receipt_location = "receipt_location";
	public static final String receipt_photo = "receipt_photo";
	
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int expenditure_COL_list = 1;
	public static final int expenditure_COL_date = 2;
	public static final int expenditure_COL_money = 3;
	public static final int expenditure_COL_location = 4;
	public static final int expenditure_COL_expenditure_photo = 5;
	
	public static final int receipte_COL_list = 1;
	public static final int receipt_COL_date = 2;
	public static final int receipt_COL_money = 3;
	public static final int receipt_COL_location = 4;
	public static final int receipt_COL_expenditure_photo = 5;
	
	
	public static final String[] expenditure_KEYS = new String[] {expenditure_ROWID, expenditure_list,
		expenditure_date, expenditure_money, expenditure_location, expenditure_photo};
	
	public static final String[] receipt_KEYS = new String[] {receipt_ROWID, receipt_list,
		receipt_date, receipt_money, receipt_location, receipt_photo};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "Flashaccounting";
	public static String expenditure_DATABASE_TABLE = "mainTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 1;	
	
	
	//新增支出資料庫的function
	private static String ExpenditureDatabaseCreate(String expenditure_DATABASE_TABLE){
    	 String expenditure_DATABASE_CREATE_SQL = 
			"create table " + expenditure_DATABASE_TABLE 
			+ " (" + expenditure_ROWID + " integer primary key autoincrement, "
			
			/*
			 * CHANGE 2:
			 */
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ expenditure_list + " text not null, "
			+ expenditure_date + " text not null, "
			+ expenditure_money + " integer not null, "
			+ expenditure_location + " string not null "
			+ expenditure_photo + " string "
			// Rest  of creation:
			+ ");";
    	 return expenditure_DATABASE_CREATE_SQL ;
	}
	/*
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			
			
			// CHANGE 2:
			 
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
			+ KEY_NAME + " text not null, "
			+ KEY_STUDENTNUM + " integer not null, "
			+ KEY_FAVCOLOUR + " string not null"
			
			// Rest  of creation:
			+ ");"; */
	
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
	public long insertRow(String name, int studentNum, String favColour) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_STUDENTNUM, studentNum);
		initialValues.put(KEY_FAVCOLOUR, favColour);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, expenditure_DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, expenditure_DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, int studentNum, String favColour) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_STUDENTNUM, studentNum);
		newValues.put(KEY_FAVCOLOUR, favColour);
		
		// Insert it into the database.
		return db.update(expenditure_DATABASE_TABLE, newValues, where, null) != 0;
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
			_db.execSQL(ExpenditureDatabaseCreate("交通"));
			_db.execSQL(ExpenditureDatabaseCreate("飲食"));
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + expenditure_DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}
