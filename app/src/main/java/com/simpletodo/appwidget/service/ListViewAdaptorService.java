package com.simpletodo.appwidget.service;

import java.util.List;

import com.example.simpletodo.R;
import com.simpletodo.bean.EventType;
import com.simpletodo.bean.ListItem;
import com.simpletodo.db.MySQLiteOpenHelper;
import com.simpletodo.util.LogUtil;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;


public class ListViewAdaptorService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        List<ListItem> listItems = mySQLiteOpenHelper.getAllListItem();

        return (new ListProvider(listItems, this.getApplicationContext(), intent));//return the RemoteView ListView Adaptor
    }

    /**
     * 相当于adapter
     */
    public class ListProvider implements RemoteViewsFactory {

        private List<ListItem> listItems;
        private Context context;

        public ListProvider(List<ListItem> listItems, Context context, Intent intent) {
            this.context = context;
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
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public void onCreate() {
            MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
            listItems = mySQLiteOpenHelper.getAllListItem();
        }

        @Override
        public void onDestroy() {
            listItems = null;
        }

        @Override
        public RemoteViews getViewAt(int position) {

            LogUtil.d("ListViewAdaptorService","getViewAt position "+position);
            final RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.appwidget_listitem);
            ListItem listItem = listItems.get(position);
            remoteView.setTextViewText(R.id.appwidget_todo_text, listItem.itemName);


            if(listItem.complete) {
                remoteView.setImageViewResource(R.id.ib_appwidget_done, R.drawable.btn_check_on_focused_holo_dark);
            } else {
                remoteView.setImageViewResource(R.id.ib_appwidget_done, R.drawable.btn_check_off_focused_holo_light);
            }

            if(listItem.itemType.equals(EventType.NORMAL.toString())) {
                remoteView.setImageViewResource(R.id.ib_appwidget_priority, R.drawable.ic_priority_high_import_no);
            } else {
                remoteView.setImageViewResource(R.id.ib_appwidget_priority, R.drawable.ic_priority_high_import_yes);
            }

            Intent done_intent = new Intent();
            done_intent.putExtra("pos", position);
            done_intent.putExtra("type", "done");

            Intent text_intent = new Intent();
            done_intent.putExtra("pos", position);
            done_intent.putExtra("type", "text");

            Intent priority_intent = new Intent();
            priority_intent.putExtra("pos", position);
            priority_intent.putExtra("type", "priority");

            remoteView.setOnClickFillInIntent(R.id.ib_appwidget_done, done_intent);
            remoteView.setOnClickFillInIntent(R.id.appwidget_todo_text, text_intent);
            remoteView.setOnClickFillInIntent(R.id.ib_appwidget_priority, priority_intent);

            return remoteView;
        }


        @Override
        public void onDataSetChanged() {
            MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
            listItems = mySQLiteOpenHelper.getAllListItem();
        }

    }


}
