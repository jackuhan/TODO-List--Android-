package com.simpletodo.appwidget.service;

import java.util.List;

import com.simpletodo.bean.ListItemData;
import com.simpletodo.service.ASServiceConnection;
import com.simpletodo.service.AccessSQLiteService;

import com.simpletodo.bean.ListItem;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;


public class ListViewAdaptorService extends RemoteViewsService  {
	
	private ASServiceConnection asServiceConnection;
	
	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		int appWidgetId = intent.getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		
		//initiate ListItemData
		callSTODOSQLiteOpenHelperService();
		List<ListItem> listItems = 
				asServiceConnection.getAccessSQLiteService().getSTODOSQLiteOpenHelper().getAllListItem();

		Log.d("TEST", "Calling onGetViewFactory()");
		return (new ListProvider(listItems, this.getApplicationContext(), intent));
	}
	
	private void callSTODOSQLiteOpenHelperService(){
		Intent intent = new Intent(this, AccessSQLiteService.class);
		asServiceConnection = new ASServiceConnection();
		bindService(intent, asServiceConnection, Context.BIND_AUTO_CREATE);
	}


}
