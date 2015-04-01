package com.simpletodo.main;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItemData;
import com.simpletodo.db.MySQLiteOpenHelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private MainView mainView;
	private static MySQLiteOpenHelper sTODOSQLiteOpenHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sTODOSQLiteOpenHelper = new MySQLiteOpenHelper(this);
		ListItemData listItemData = new ListItemData(this);
		listItemData.initialListItemData();
		mainView = new MainView(this, null);
		setContentView(mainView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public MainView getMainView(){
		return mainView;
	}
	
	public static MySQLiteOpenHelper getSTODOSQLiteOpenHelper(){
		return sTODOSQLiteOpenHelper;
	}


}
