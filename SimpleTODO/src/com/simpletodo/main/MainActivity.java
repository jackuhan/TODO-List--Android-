package com.simpletodo.main;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItemData;
import com.simpletodo.db.STODOSQLiteOpenHelper;
import com.simpletodo.dummydata.DummyItemFactory;
import com.simpletodo.service.ASServiceConnection;
import com.simpletodo.service.AccessSQLiteService;
import com.simpletodo.service.AccessSQLiteService.AccessSQLiteServiceBinder;
import com.simpletodo.view.MainView;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	
	//View
	private static MainView mainView;
	//STODOSQLiteOpenHelper
	private static STODOSQLiteOpenHelper sTODOSQLiteOpenHelper;
	//Service
	private static AccessSQLiteService accessSQLiteService;
	private ASServiceConnection asServiceConnection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//create sTODOSQLiteOpenHelper and hold only one instance in this application
		callSTODOSQLiteOpenHelperService();
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
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		//unbind the service while leaving the application
		unbindService(asServiceConnection);
		accessSQLiteService = null;
	}
	
	public static MainView getMainView(){
		return mainView;
	}
	
	public static STODOSQLiteOpenHelper getSTODOSQLiteOpenHelper(){
		return sTODOSQLiteOpenHelper;
	}
	
	public static void setSTODOSQLiteOpenHelper(STODOSQLiteOpenHelper sTODOSQLiteOpenHelper){
		MainActivity.sTODOSQLiteOpenHelper = sTODOSQLiteOpenHelper;
	}
	
	public static AccessSQLiteService getAccessSQLiteService() {
		return accessSQLiteService;
	}

	public static void setAccessSQLiteService(
			AccessSQLiteService accessSQLiteService) {
		MainActivity.accessSQLiteService = accessSQLiteService;
	}
	
	private void callSTODOSQLiteOpenHelperService(){
		Intent intent = new Intent(this, AccessSQLiteService.class);
		asServiceConnection = new ASServiceConnection();
		bindService(intent, asServiceConnection, Context.BIND_AUTO_CREATE);
	}

	/** Defines callbacks for service binding, passed to bindService() */
//    private ServiceConnection asServiceConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName className,
//                IBinder service) {
//            // We've bound to LocalService, cast the IBinder and get AccessSQLiteService instance
//        	AccessSQLiteServiceBinder binder = (AccessSQLiteServiceBinder) service;
//        	accessSQLiteService = binder.getService();
//        	sTODOSQLiteOpenHelper = accessSQLiteService.getSTODOSQLiteOpenHelper();
//        	Log.d("TSET", sTODOSQLiteOpenHelper.toString());
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            //TODO
//        	
//        }
//    };
}
