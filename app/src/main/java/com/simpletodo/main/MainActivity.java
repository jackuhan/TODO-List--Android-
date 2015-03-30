package com.simpletodo.main;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItemData;
import com.simpletodo.db.STODOSQLiteOpenHelper;
import com.simpletodo.dummydata.DummyItemFactory;
import com.simpletodo.view.MainView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	//View
	private MainView mainView;
	//
	private static STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//create sTODOSQLiteOpenHelper and hold only one instance in this application
		sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(this);
		//initialize memory data
		ListItemData listItemData = new ListItemData(this);
		listItemData.initialListItemData();
		//set main view
		mainView = new MainView(this, null);
		setContentView(mainView);
		
		//test "ONLY USE WHEN YOU FIRST INSTALL THE APPLICATION!!"
//		DummyItemFactory df = new DummyItemFactory(this, ListItemData.getListItems());
//		df.putDummyItemContent();
//		mainView.refreshItemViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public MainView getMainView(){
		return mainView;
	}
	
	public static STODOSQLiteOpenHelper getSTODOSQLiteOpenHelper(){
		return sTODOSQLiteOpenHelper;
	}


}
