package com.simpletodo.appwidget;


import com.example.simpletodo.R;
import com.simpletodo.appwidget.service.ListViewAdaptorService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {
	//TODO
	//Add the new action String (Maybe for Button or Icon)
//	private static String testIntent = "";
	
	private Context context;
	private AppWidgetManager appWidgetManager;
	private int[] appWidgetIds;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		final int N = appWidgetIds.length;
		for (int i=0; i<N; i++) {
//			int appWidgetId = appWidgetIds[i];
//			updateAppWidget(context, appWidgetManager, appWidgetId);		
			RemoteViews remoteViews = updateWidgetListView(context,
					appWidgetIds[i]);
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
			Log.d("TEST", appWidgetIds.toString() + " from provider");
		}	
		
	}
	
	
//	private void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//		int appWidgetId) {
//		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
//		
//		//Add intent here
//		//TODO
//		//Maybe you can abstract them into a class
//		Intent intent = new Intent(context, SimpleTODOAppWidgetProvider.class);
//		intent.setAction(testIntent);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
//		views.setOnClickPendingIntent(R.id.appwidget_addbutton, pendingIntent);
//		
//		appWidgetManager.updateAppWidget(appWidgetId, views);
//	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		//TODO
		//Add something to do here with corresponding action
//		if(intent.getAction().equals("APPWIDGET_UPDATE")){
//
//		}	
	}
	
	@SuppressWarnings("deprecation")
	private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
		
		Log.d("TEST", "calling updateWidgetListView!!!!");
		//which layout to show on widget
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		//RemoteViews Service needed to provide adapter for ListView
		Intent svcIntent = new Intent(context, ListViewAdaptorService.class);
		//passing app widget id to that RemoteViews Service
		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		//setting a unique Uri to the intent
		//don't know its purpose to me right now
		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		//setting adapter to listview of the widget
		remoteViews.setRemoteAdapter(appWidgetId, R.id.appwidget_listview,
				svcIntent);
		//setting an empty view in case of no data
		remoteViews.setEmptyView(R.id.appwidget_listview, R.id.appwidget_addbutton);
		
		return remoteViews;
	}


}
