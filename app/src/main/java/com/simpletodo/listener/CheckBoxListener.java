package com.simpletodo.listener;

import com.simpletodo.bean.ListItem;
import com.simpletodo.db.MySQLiteOpenHelper;
import com.simpletodo.main.MainActivity;

import android.content.Context;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckBoxListener implements OnCheckedChangeListener {
	
	private ListItem listItem;
	private Context context;
	
	public CheckBoxListener(ListItem listItem, Context context){
		this.listItem = listItem;
		this.context = context;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
		if (isChecked){
			listItem.setComplete(true);
			MySQLiteOpenHelper sTODOSQLiteOpenHelper = MainActivity.getSTODOSQLiteOpenHelper();
			//modified listItem should store into database
			sTODOSQLiteOpenHelper.setItemComplete(listItem);
			Log.d("DEBUG", "set to true");
		}
		
		else {
			listItem.setComplete(false);
			MySQLiteOpenHelper sTODOSQLiteOpenHelper = MainActivity.getSTODOSQLiteOpenHelper();
			//modified listItem should store into database
			sTODOSQLiteOpenHelper.setItemIncomplete(listItem);
			Log.d("DEBUG", "set to false");
		}
		
	}

}
