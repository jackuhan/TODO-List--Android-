package com.simpletodo.appwidget.service;

import com.simpletodo.bean.ListItemData;
import com.simpletodo.main.MainActivity;
import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

@SuppressLint("NewApi")
public class ListViewAdaptorService extends RemoteViewsService  {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		int appWidgetId = intent.getIntExtra(
				AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		
		//initiate ListItemData
		new ListItemData(new MainActivity());
		
		return (new ListProvider(ListItemData.getListItems(), this.getApplicationContext(), intent));
	}

}
