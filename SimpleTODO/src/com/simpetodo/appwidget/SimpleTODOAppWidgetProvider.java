package com.simpetodo.appwidget;


import com.example.simpletodo.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {
	
	//Operations in the AppWidget
	private static String addButtonClick = "ADDBUTTON_CLICK";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int N = appWidgetIds.length;
		for (int i=0; i<N; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}
	}
		
	public void onDeleted(Context context, int[] appWidgetIds) {
	}
	
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
		int appWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		ComponentName simpleTODOAppWidget = new ComponentName( context, SimpleTODOAppWidgetProvider.class );
		
		// set intent and register onClick
		Intent i = new Intent(context, SimpleTODOAppWidgetProvider.class);
		i.setAction(addButtonClick);
		PendingIntent pi = PendingIntent.getBroadcast(context,0, i,0);
		views.setOnClickPendingIntent(R.id.appwidget_addbutton,pi);
		
		appWidgetManager.updateAppWidget(simpleTODOAppWidget, views);
	}
	
	@Override
	public void onReceive(Context context, Intent intent){
		super.onReceive(context, intent);
		if (intent.getAction().equals(addButtonClick)) {
			   //do some really cool stuff here
			Log.d("DEBUG", "APPWIDGET CLICK!!");
		}
	}

}
