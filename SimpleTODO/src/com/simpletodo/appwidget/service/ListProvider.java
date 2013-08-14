package com.simpletodo.appwidget.service;

import java.util.List;
import com.example.simpletodo.R;
import com.simpletodo.bean.ListItem;
import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

@SuppressLint("NewApi")
public class ListProvider implements RemoteViewsFactory {
	
	private int appWidgetId;
	private List<ListItem> listItems;
	private Context context;
	
	public ListProvider(List<ListItem> listItems, Context Context, Intent intent){
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RemoteViews getViewAt(int position) {
		final RemoteViews remoteView = new RemoteViews(
				context.getPackageName(), R.layout.list_item);
		ListItem listItem = listItems.get(position);
		remoteView.setTextViewText(R.id.upper_text, listItem.getItemName());
		remoteView.setTextViewText(R.id.lower_text, listItem.getItemType());
		return remoteView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataSetChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

}
