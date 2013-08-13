package com.simpetodo.bean;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.simpetodo.db.STODOSQLiteOpenHelper;

public class ListItemData {
	
	private static List<ListItem> listItems;
	private STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	
	public ListItemData(Context context){
		this.sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
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
