package com.simpletodo.appwidget.service;

import java.util.List;

import com.simpletodo.bean.ListItem;
import com.simpletodo.db.STODOSQLiteOpenHelper;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;


public class ListViewAdaptorService extends RemoteViewsService  {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		int appWidgetId = intent.getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		
		//get SQLiteOpenHelper
		STODOSQLiteOpenHelper sTODOSQLiteOpenHelper  = new STODOSQLiteOpenHelper(this);
		List<ListItem> listItems = sTODOSQLiteOpenHelper.getAllListItem();
		//return the RemoteView ListView Adaptor
		return (new ListProvider(listItems, this.getApplicationContext(), intent));
	}

}
