package com.simpletodo.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.simpletodo.db.STODOSQLiteOpenHelper;
import com.simpletodo.main.MainActivity;

public class ListItemData {
	
	private static List<ListItem> listItems;
	private STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	
	public ListItemData(Context context){
		this.sTODOSQLiteOpenHelper = MainActivity.getSTODOSQLiteOpenHelper();
	}
	
	public void initialListItemData(){
		setListItems(new ArrayList<ListItem>());
		listItems = sTODOSQLiteOpenHelper.getAllListItem();
	}

	public static List<ListItem> getListItems() {
		if(listItems == null){
			listItems = new ArrayList<ListItem>();
		}
		return listItems;
	}

	public static void setListItems(List<ListItem> listItems) {
		ListItemData.listItems = listItems;
	}
}
