package com.simpetodo.listener;

import com.simpetodo.bean.ListItem;
import com.simpetodo.db.STODOSQLiteOpenHelper;

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
			STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
			//modified listItem should store into database
			sTODOSQLiteOpenHelper.setItemComplete(listItem);
			Log.d("DEBUG", "set to true");
		}
		
		else {
			listItem.setComplete(false);
			STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
			//modified listItem should store into database
			sTODOSQLiteOpenHelper.setItemIncomplete(listItem);
			Log.d("DEBUG", "set to false");
		}
		
	}

}
