package com.simpletodo.util;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.simpletodo.main.MainActivity;

public class AppWidgetUtils {

    public static PendingIntent createActivityPendingIntent(Context context, int appWidgetId, Class<?> configureClass) {
        Intent configureIntent = new Intent(context, configureClass);
        configureIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        return PendingIntent.getActivity(context, -appWidgetId, configureIntent, 0);
    }

    public static PendingIntent createBroadcastPendingIntent(Context context, int appWidgetId, String ACTIVON) {
        Intent configureIntent = new Intent();
        configureIntent.setAction(ACTIVON);
        return PendingIntent.getBroadcast(context, 0, configureIntent, 0);
    }

    public static PendingIntent createServicePendingIntent(Context context, int appWidgetId, String ACTION) {
        Intent configureIntent = new Intent();
        configureIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        configureIntent.setAction(ACTION);
        return PendingIntent.getService(context, 0, configureIntent, 0);
    }


}
