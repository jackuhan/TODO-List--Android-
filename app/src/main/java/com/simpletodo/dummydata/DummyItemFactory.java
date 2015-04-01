package com.simpletodo.dummydata;

import java.util.List;
import java.util.UUID;

import android.app.Activity;

import com.simpletodo.bean.EventType;
import com.simpletodo.bean.ListItem;
import com.simpletodo.db.MySQLiteOpenHelper;
import com.simpletodo.main.MainActivity;

public class DummyItemFactory {

	private List<ListItem> listItems;
	
	public DummyItemFactory(Activity mainActivity, List<ListItem> listItems){
		this.listItems = listItems;
	}
	
	public void putDummyItemContent(){
		listItems.add(newDummyListItem(false, "Add task type " + addQuotes("Task"), EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Add sync function", EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Add share function", EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Add member identity", EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Add call function", EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Add three type layout", EventType.IMPORTANT.toString()));
		listItems.add(newDummyListItem(false, "Test for disappear", EventType.IMPORTANT.toString()));
	}
	
	private ListItem newDummyListItem(boolean iscomplete, String taskName, String type){
		ListItem listItem = new ListItem();
		listItem.setComplete(iscomplete);
		listItem.setId(UUID.randomUUID().toString());
		listItem.setItemName(taskName);
		listItem.setItemType(type);
		MySQLiteOpenHelper sqLiteOpenHelper = MainActivity.getSTODOSQLiteOpenHelper();
		sqLiteOpenHelper.addListItem(listItem);
		return listItem;
	}
	
	private String addQuotes(String input){
		return "\"" + input + "\"";
	}

}
