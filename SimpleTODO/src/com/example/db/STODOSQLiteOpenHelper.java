package com.example.db;

import java.util.List;

import com.example.bean.ListItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class STODOSQLiteOpenHelper extends SQLiteOpenHelper {
	
	private static final int STODO_VERSION = 2;
    private static final String STODO_TABLE_NAME = "STODO";
    private static final String ITEM_ID = "ITEM_ID";
    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String ITEM_TYPE = "ITEM_TYPE";
    private static final String ISCOMPLETE = "ISCOMPLETE";
    
    //List Item table
    private static final String STODO_TABLE_CREATE =
                "CREATE TABLE " + STODO_TABLE_NAME + " (" +
                ITEM_ID + " INTEGER, " +
                ITEM_NAME + " TEXT, " +
                ITEM_TYPE + " TEXT, " +
                ISCOMPLETE + " INTEGER" +");";
	
	public STODOSQLiteOpenHelper(Context context) {
        super(context, STODO_TABLE_NAME, null, STODO_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STODO_TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void addListItem(ListItem listItem){
	    SQLiteDatabase db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    values.put(ITEM_ID, listItem.getId());
	    values.put(ITEM_NAME, listItem.getItemName());
	    values.put(ITEM_TYPE, listItem.getItemType());
	    if(listItem.isComplete() == true){
	    	values.put(ISCOMPLETE, 1);
	    }
	    else if(listItem.isComplete() == false){
	    	values.put(ISCOMPLETE, 0);
	    }
	    // Inserting Row
	    db.insert(STODO_TABLE_NAME, null, values);
	    db.close(); // Closing database connection
	    System.out.println("Write to db successful!");
	}
	
	public void getAllListItem(List<ListItem> listItems){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from table",null);
		if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String name = cursor.getString(cursor
                        .getColumnIndex(ITEM_ID));

                listItems.add(name);
                cursor.moveToNext();
            }
        }
	}
}
