package com.simpetodo.view;

import java.util.ArrayList;
import java.util.List;

import com.example.simpletodo.R;
import com.simpetodo.bean.ListItem;
import com.simpetodo.bean.ListItemData;
import com.simpetodo.main.MainActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class AppWidgetMainView extends RelativeLayout {
	
	private ListView listView;
	private Button addButton;
	private Context context;

	public AppWidgetMainView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.appwidget_main, this, true);
		this.context = context;
		linkView();
		initiateListView();
	}
	
	private void linkView(){
		listView = (ListView)findViewById(R.id.appwidget_listview);
//		addButton = (Button)findViewById(R.id.appwidget_addbutton);
	}
	
	private void initiateListView(){
		new ListItemData(new MainActivity());
		List<ListItem> listItems = ListItemData.getListItems();
		ArrayList<String> listItems_String = new ArrayList<String>();
		for(ListItem listItem : listItems){
			listItems_String.add(listItem.getItemName());
		}
		ArrayAdapter<String> arrayAdapter =      
		         new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listItems_String);
		listView.setAdapter(arrayAdapter);
	}

}
