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
	public static final String flashaccounting_ROWID = "_id";
	public static final int flashaccounting_COL_ROWID = 0;

	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String flashaccounting_expenditure_or_receipt = "expenditure_or_receipt";
	public static final String flashaccounting_list = "list";
	public static final String flashaccounting_money = "money";
	public static final String flashaccounting_date = "date";
	public static final String flashaccounting_location = "location";
	public static final String flashaccounting_group = "group";
	public static final String flashaccounting_photo = "photo";

	
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_expenditure_or_receipt = 2;
	public static final int COL_list = 2;
	public static final int COL_money = 3;
	public static final int COL_date = 4;
	public static final int COL_location = 5;
	public static final int COL_group = 6;
	public static final int COL_photo = 7;
	


	
	public static final String[] ALL_flashaccounting = new String[] {flashaccounting_ROWID, flashaccounting_expenditure_or_receipt,
		flashaccounting_list , flashaccounting_money, flashaccounting_date, flashaccounting_location, flashaccounting_group, flashaccounting_photo};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "flashaccounting";
	public static String DATABASE_TABLE = "mainTable";
	// Track DB version if a new version of your app changes the format.
	public static int DATABASE_VERSION = 1;	
	
	
	static String create_table(String tablename ){
			String DATABASE_CREATE_SQL = 
			"create table " + tablename 
			+ " (" + flashaccounting_ROWID + " integer primary key autoincrement, "
			
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
			+ flashaccounting_expenditure_or_receipt + " integer not null, "
			+ flashaccounting_list + " string not null, "
			+ flashaccounting_money + " integer not null, "
			+ flashaccounting_date + " string not null"
			+ flashaccounting_location + " string not null, "
			+ flashaccounting_group + " string not null, "
			+ flashaccounting_photo + " string not null, "
			
			// Rest  of creation:
			+ ");";
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
	public long insertRow(int expenditure_or_receipt, String list, int money, String date,
			String location, String group, String photo, String DATABASE_TABLE) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(flashaccounting_expenditure_or_receipt, expenditure_or_receipt);
		initialValues.put(flashaccounting_list, list);
		initialValues.put(flashaccounting_money, money);
		initialValues.put(flashaccounting_date, date);
		initialValues.put(flashaccounting_location, location);
		initialValues.put(flashaccounting_group, group);
		initialValues.put(flashaccounting_photo, photo);
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId, String DATABASE_TABLE) {
		String where = flashaccounting_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll(String DATABASE_TABLE) {
		Cursor c = getAllRows(DATABASE_TABLE);
		long rowId = c.getColumnIndexOrThrow(flashaccounting_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId),  DATABASE_TABLE);				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows(String DATABASE_TABLE) {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_flashaccounting, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId , String DATABASE_TABLE) {
		String where = flashaccounting_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_flashaccounting, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId,int expenditure_or_receipt, String list, int money, String date,
			String location, String group, String photo, String DATABASE_TABLE) {
		String where = flashaccounting_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(flashaccounting_expenditure_or_receipt, expenditure_or_receipt);
		newValues.put(flashaccounting_list, list);
		newValues.put(flashaccounting_money, money);
		newValues.put(flashaccounting_date, date);
		newValues.put(flashaccounting_location, location);
		newValues.put(flashaccounting_group, group);
		newValues.put(flashaccounting_photo, photo);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
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
			_db.execSQL(create_table("transportation"));
//			_db.execSQL(create_table("eating"));
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}
