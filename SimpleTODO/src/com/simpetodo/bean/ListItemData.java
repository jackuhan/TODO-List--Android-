package com.simpetodo.bean;

import java.util.ArrayList;
import java.util.List;

import com.simpetodo.db.STODOSQLiteOpenHelper;

public class ListItemData {
	
	private static List<ListItem> listItems;
	private STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	
	public ListItemData(STODOSQLiteOpenHelper sTODOSQLiteOpenHelper){
		this.sTODOSQLiteOpenHelper = sTODOSQLiteOpenHelper;
	}
	
	public void initialListItemData(){
		setListItems(new ArrayList<ListItem>());
		listItems = sTODOSQLiteOpenHelper.getAllListItem();
	}

	public static List<ListItem> getListItems() {
		return listItems;
	}

	public static void setListItems(List<ListItem> listItems) {
		ListItemData.listItems = listItems;
	}

}