package com.simpletodo.bean;

public class ListItem {
	
	public String id;
	public String itemName;
	public String itemType;
	public boolean complete;
	
	public ListItem(){
		complete = false;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
