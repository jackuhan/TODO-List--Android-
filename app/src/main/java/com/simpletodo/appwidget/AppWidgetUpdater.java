package com.simpletodo.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppWidgetUpdater {
	
	private Context context;
	
	public AppWidgetUpdater(Context context){
		this.context = context;
	}
	
	public void updateAppWidget(){
		Log.d("TEST", "updateAppWidget() was called!!");
		ComponentName appWidget = new ComponentName(context, SimpleTODOAppWidgetProvider.class);
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(appWidget);
		
		Intent intent = new Intent(context, SimpleTODOAppWidgetProvider.class);
		intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");

		// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
		// since it seems the onUpdate() is only fired on that:

		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,appWidgetIds);
		context.sendBroadcast(intent);
	}


}
