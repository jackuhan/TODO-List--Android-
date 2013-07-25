package com.example.db;

import java.io.File;
import java.lang.reflect.Array;
import java.util.List;

import com.example.bean.ListItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class STODOSQLiteOpenHelper extends SQLiteOpenHelper {
	
	private static final int STODO_VERSION = 2;
    private static final String STODO_TABLE_NAME = "STODO";
    private static final String ITEM_ID = "ITEM_ID";
    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String ITEM_TYPE = "ITEM_TYPE";
    private static final String ISCOMPLETE = "ISCOMPLETE";
    private SQLiteDatabase db = null;
    
    //List Item table
    private static final String STODO_TABLE_CREATE =
                "CREATE TABLE " + STODO_TABLE_NAME + " (" +
                ITEM_ID + " TEXT, " +
                ITEM_NAME + " TEXT, " +
                ITEM_TYPE + " TEXT, " +
                ISCOMPLETE + " INTEGER);";
	
	public STODOSQLiteOpenHelper(Context context) {
        super(context, STODO_TABLE_NAME, null, STODO_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(STODO_TABLE_CREATE);
            System.out.println("STODO_TABLE_CREATE!!!");
          } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + STODO_TABLE_NAME);
		onCreate(db);
	}
	
	public void addListItem(ListItem listItem){
	    db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    System.out.println("Start to write to db!");
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
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + STODO_TABLE_NAME,null);
		if (cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                String id = cursor.getString(cursor
                        .getColumnIndex(ITEM_ID));
                String name = cursor.getString(cursor
                        .getColumnIndex(ITEM_NAME));
                String type = cursor.getString(cursor
                        .getColumnIndex(ITEM_TYPE));
                String isComplete = cursor.getString(cursor
                        .getColumnIndex(ISCOMPLETE));
                ListItem listItem = new ListItem();
                listItem.setId(id);
                listItem.setItemName(name);
                listItem.setItemType(type);
                if(Integer.valueOf(isComplete) == 1){
                	listItem.setComplete(true);
                }
                else if(Integer.valueOf(isComplete) == 0){
                	listItem.setComplete(false);
                }
                
                listItems.add(listItem);
                cursor.moveToNext();
            }
        }
		db.close();
	}
	
	//set listItem to complete status
	public void setItemComplete(ListItem listItem){
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(ISCOMPLETE, "1");
		db.update(STODO_TABLE_NAME, contentValues, ITEM_ID + "=?", new String[]{listItem.getId()});
		db.close();
	}
	
	//set listItem to incomplete status
	public void setItemIncomplete(ListItem listItem){
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(ISCOMPLETE, "0");
		db.update(STODO_TABLE_NAME, contentValues, ITEM_ID + "=?", new String[]{listItem.getId()});
		db.close();
	}
	
	public void deleteSpecificListItem(String id){
		db = this.getWritableDatabase();
		db.delete(STODO_TABLE_NAME, ITEM_ID + "=?", new String[]{id});
	}
	
	public void deleteAllData(){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(STODO_TABLE_NAME, null, null);
		db.close();
	}
}
