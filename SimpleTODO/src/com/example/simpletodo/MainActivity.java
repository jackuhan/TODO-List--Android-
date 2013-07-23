package com.example.simpletodo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.bean.EventType;
import com.example.bean.ListItem;
import com.example.db.STODOSQLiteOpenHelper;
import com.example.listener.AddButtonListener;
import com.example.listener.ListItemViewLongClickListener;
import com.example.view.ListItemView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

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
		
		//test
//		sTODOSQLiteOpenHelper.deleteAllData();


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
