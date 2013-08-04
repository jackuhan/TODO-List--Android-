package com.simpetodo.appwidget;


import com.example.simpletodo.R;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {

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
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

}
