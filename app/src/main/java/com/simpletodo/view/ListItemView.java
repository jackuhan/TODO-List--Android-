package com.simpletodo.view;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItem;
import com.simpletodo.listener.CheckBoxListener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListItemView extends RelativeLayout {
	
	private TextView upperText;
	private TextView lowerText;
	private CheckBox checkBox;
	private ListItem listItem;
	private Context context;
	
	public ListItemView(Context context, AttributeSet attrs, ListItem listItem) {
	    super(context, attrs);
	    this.context = context;
	    
	    LayoutInflater.from(context).inflate(R.layout.list_item, this, true);
	    
	    upperText = (TextView)findViewById(R.id.upper_text);
	    lowerText = (TextView)findViewById(R.id.lower_text);
	    checkBox = (CheckBox)findViewById(R.id.checkbox);
	    this.listItem = listItem;
	    
	    setupListItemView();
	}
	
	private void setUpperText(String str){
		upperText.setText(str);
	}
	
	private void setLowerText(String str){
		lowerText.setText(str);
	}
	
	private void determinCheckBox(){
		if(listItem.isComplete() == true){
			checkBox.setChecked(true);
		}
		else if(listItem.isComplete() == false){
			checkBox.setChecked(false);
		}
	}
	
	private void setupListItemView(){
		setUpperText(listItem.getItemName());
		setLowerText(listItem.getItemType());
		determinCheckBox();
	    checkBox.setOnCheckedChangeListener(new CheckBoxListener(listItem, context));
	}
	
	public ListItem getListItem(){
		return listItem;
	}

}
