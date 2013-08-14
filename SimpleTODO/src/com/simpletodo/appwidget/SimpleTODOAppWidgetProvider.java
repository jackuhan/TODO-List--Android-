package com.simpletodo.appwidget;


import com.example.simpletodo.R;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {
	//TODO
	//Add the new action String (Maybe for Button or Icon)
//	private static String testIntent = "";
	
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
		
		//Add intent here
		//TODO
		//Maybe you can abstract them into a class
//		Intent intent = new Intent(context, SimpleTODOAppWidgetProvider.class);
//		intent.setAction(testIntent);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//		views.setOnClickPendingIntent(R.id.appwidget_addbutton, pendingIntent);
		
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		//TODO
		//Add something to do here with corresponding action
//		if(intent.getAction().equals(testIntent)){
//			
//		}	
	}
	
	private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.id.appwidget_listview);
		return remoteViews;
	}


}
