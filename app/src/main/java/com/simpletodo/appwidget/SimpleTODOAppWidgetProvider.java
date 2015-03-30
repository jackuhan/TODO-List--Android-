package com.simpletodo.appwidget;


import com.example.simpletodo.R;
import com.simpletodo.appwidget.service.ListViewAdaptorService;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {
	//TODO
	//Add the new action String (Maybe for Button or Icon)
//	private static String testIntent = "";
	
	//which layout to show on widget
	private RemoteViews remoteViews;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		//make sure there is only one RemoteViews instance,
		//otherwise it'll cause some problem after re-enter
		//the activity
		if(remoteViews == null){
			remoteViews = 
					new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
		}	
		final int N = appWidgetIds.length;
		for (int i=0; i<N; i++) {
			setListView(context,appWidgetIds[i]);
			//use this to update the ListView by notifying the adapter
			
			//After my testing, notifyAppWidgetViewDataChanged() most
			//be after updateAppWidget() or the ListView in RemoteView will
			//not be updated sometime
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
			appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.appwidget_listview);
		}		
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		//TODO
		//Add something to do here with corresponding action
		if(intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")){

		}
	}
	
	private void setListView(Context context, int appWidgetId){
		
		//RemoteViews Service needed to provide adapter for ListView
		Intent svcIntent = new Intent(context, ListViewAdaptorService.class);
		//passing app widget id to that RemoteViews Service
		svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		//setting a unique Uri to the intent
		//don't know its purpose to me right now
		svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
		//setting adapter to listview of the widget
		remoteViews.setRemoteAdapter(R.id.appwidget_listview, svcIntent);
		//setting an empty view in case of no data
		remoteViews.setEmptyView(R.id.appwidget_listview, R.id.appwidget_listview);
	}


}
