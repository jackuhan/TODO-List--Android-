package com.simpetodo.appwidget;


import java.util.ArrayList;
import java.util.List;

import com.example.simpletodo.R;
import com.simpetodo.bean.ListItem;
import com.simpetodo.bean.ListItemData;
import com.simpetodo.main.MainActivity;
import com.simpetodo.view.AppWidgetMainView;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class SimpleTODOAppWidgetProvider extends AppWidgetProvider {

	private static String testIntent = "TEST_ACTIVITY";
	
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
		
		Intent intent = new Intent(context, SimpleTODOAppWidgetProvider.class);
		intent.setAction(testIntent);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.appwidget_addbutton, pendingIntent);
		
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		if(intent.getAction().equals(testIntent)){
			AppWidgetMainView appWidgetMainView = new AppWidgetMainView(context, null);
			//initiate ListView
			new ListItemData(new MainActivity());
			List<ListItem> listItems = ListItemData.getListItems();
			ArrayList<String> listItems_String = new ArrayList<String>();
			for(ListItem listItem : listItems){
				listItems_String.add(listItem.getItemName());
			}
		    AppWidgetManager manager = AppWidgetManager.getInstance(context);
		    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.camera_widget_layout);
		    
		    views.setTextViewText(R.id.widget_amount_cameras, amountCameras);
		    views.setTextViewText(R.id.widget_location, locationCity);
		    // views.setTextViewText(R.id.widget_security, securityStatus);
		    views.setTextViewText(R.id.locationStatus, locationStatus);

		    final int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(CameraWidgetProvider.class.getPackage().getName(), CameraWidgetProvider.class.getName()));

		    manager.updateAppWidget(appWidgetIds, views);
		}	
	}


}
