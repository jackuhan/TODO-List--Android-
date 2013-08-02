package com.example.simpletodo;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.ListItem;
import com.example.db.STODOSQLiteOpenHelper;
import com.example.dummydata.DummyItemFactory;
import com.example.listener.AddButtonListener;
import com.example.listener.ListItemViewLongClickListener;
import com.example.view.ListItemView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	//data
	private List<ListItem> listItems;
	
	//View
	private Button addButton;
	private LinearLayout listItemViewContainer;
	
	//Database
	private STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(this.getApplicationContext());
		listItems = new ArrayList<ListItem>();
		linkView();
		initiateItemViewContainer();
		setListItemViewContainer();
		
		//test "ONLY USE WHEN YOU FIRST INSTALL THE APPLICATION!!"
		DummyItemFactory df = new DummyItemFactory(this, listItems);
		df.putDummyItemContent();
		setListItemViewContainer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void linkView(){
		addButton = (Button)findViewById(R.id.addbutton);
		addButton.setOnClickListener(new AddButtonListener(this, listItems));	
		listItemViewContainer = (LinearLayout)findViewById(R.id.linearlayout_in_scrollview);
	}
	
	//Add the ListItemView into main screen
	public void setListItemViewContainer(){
		listItemViewContainer.removeAllViews();
		for(ListItem listItem : listItems){
			ListItemView listItemView = new ListItemView(this, null, listItem);
			listItemView.setOnLongClickListener(new ListItemViewLongClickListener(listItems, this));
			listItemViewContainer.addView(listItemView);
		}
	}
	
	private void initiateItemViewContainer(){
		sTODOSQLiteOpenHelper.getAllListItem(listItems);
		setListItemViewContainer();
	}


}
