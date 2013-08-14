package com.simpletodo.dummydata;

import java.util.List;
import java.util.UUID;

import android.app.Activity;

import com.simpletodo.bean.EventType;
import com.simpletodo.bean.ListItem;
import com.simpletodo.db.STODOSQLiteOpenHelper;

public class DummyItemFactory {
	
	private Activity mainActivity;
	private List<ListItem> listItems;
	
	public DummyItemFactory(Activity mainActivity, List<ListItem> listItems){
		this.mainActivity = mainActivity;
		this.listItems = listItems;
	}
	
	public void putDummyItemContent(){
		listItems.add(newDummyListItem(false, "Add task type " + addQuotes("Task"), EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Add sync function", EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Add share function", EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Add member identity", EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Add call function", EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Add three type layout", EventType.GOAL.toString()));
		listItems.add(newDummyListItem(false, "Test for disappear", EventType.GOAL.toString()));
	}
	
	private ListItem newDummyListItem(boolean iscomplete, String taskName, String type){
		ListItem lI = new ListItem();
		lI.setComplete(iscomplete);
		lI.setId(UUID.randomUUID().toString());
		lI.setItemName(taskName);
		lI.setItemType(type);
		STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(mainActivity);
		sTODOSQLiteOpenHelper.addListItem(lI); //store to sqlite
		return lI;
	}
	
	private String addQuotes(String input){
		return "\"" + input + "\"";
	}

}
