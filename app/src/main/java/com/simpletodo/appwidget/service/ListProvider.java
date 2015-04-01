package com.simpletodo.appwidget.service;

import java.util.List;

import com.example.simpletodo.R;
import com.simpletodo.bean.EventType;
import com.simpletodo.bean.ListItem;
import com.simpletodo.db.MySQLiteOpenHelper;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

/**
 * 相当于adapter
 */
public class ListProvider implements RemoteViewsFactory {

    private int appWidgetId;
    private List<ListItem> listItems;
    private Context context;

    public ListProvider(List<ListItem> listItems, Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
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
        final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.appwidget_listitem);
        ListItem listItem = listItems.get(position);
        remoteView.setTextViewText(R.id.appwidget_upper_text, listItem.itemName);

        if(listItem.complete){
            remoteView.setImageViewResource(R.id.ib_appwidget_done, R.drawable.btn_check_on_focused_holo_dark);
        } else {
            remoteView.setImageViewResource(R.id.ib_appwidget_done, R.drawable.btn_check_off_focused_holo_light);
        }

        if(listItem.itemType.equals(EventType.NORMAL.toString())){
            remoteView.setImageViewResource(R.id.ib_appwidget_priority, R.drawable.ic_priority_high_import_no);
        } else {
            remoteView.setImageViewResource(R.id.ib_appwidget_priority, R.drawable.ic_priority_high_import_yes);
        }

        return remoteView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        MySQLiteOpenHelper sTODOSQLiteOpenHelper = new MySQLiteOpenHelper(context);
        listItems = sTODOSQLiteOpenHelper.getAllListItem();
    }

    @Override
    public void onDataSetChanged() {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        listItems = mySQLiteOpenHelper.getAllListItem();
    }

    @Override
    public void onDestroy() {
    }

}
