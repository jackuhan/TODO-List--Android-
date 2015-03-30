package com.simpletodo.appwidget.service;

import java.util.List;

import com.example.simpletodo.R;
import com.simpletodo.bean.ListItem;
import com.simpletodo.bean.ListItemData;
import com.simpletodo.db.STODOSQLiteOpenHelper;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;


public class ListProvider implements RemoteViewsFactory {
	
	private int appWidgetId;
	private List<ListItem> listItems;
	private Context context;
	
	public ListProvider(List<ListItem> listItems, Context context, Intent intent){
		this.context = context;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);
		
		this.listItems = listItems;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public RemoteViews getLoadingView() {
		return null;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		final RemoteViews remoteView = new RemoteViews(
				context.getPackageName(), R.layout.appwidget_listitem);
		ListItem listItem = listItems.get(position);
		remoteView.setTextViewText(R.id.appwidget_upper_text, listItem.getItemName());
		remoteView.setTextViewText(R.id.appwidget_lower_text, listItem.getItemType());
		return remoteView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onCreate() {
		STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
		listItems = sTODOSQLiteOpenHelper.getAllListItem();
	}

	@Override
	public void onDataSetChanged() {
		STODOSQLiteOpenHelper sTODOSQLiteOpenHelper = new STODOSQLiteOpenHelper(context);
		listItems = sTODOSQLiteOpenHelper.getAllListItem();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
