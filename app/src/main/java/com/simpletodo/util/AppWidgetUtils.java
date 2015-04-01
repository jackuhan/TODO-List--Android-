package com.simpletodo.util;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.simpletodo.main.MainActivity;

public class AppWidgetUtils {

    public static PendingIntent createConfigurePendingIntent(Context context, int appWidgetId, Class<?> configureClass) {
        Intent configureIntent = new Intent(context, configureClass);
        configureIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        return PendingIntent.getActivity(context, -appWidgetId, configureIntent, 0);
    }

    public static Intent createIntent(Context ctx, int pos) {
        Intent intent = new Intent(ctx, MainActivity.class);
        return intent;
    }

    public static PendingIntent createPendingIntent(Context ctx, int pos) {
        Intent intent = createIntent(ctx, pos);
        return PendingIntent.getActivity(ctx, pos, intent, 0);
    }


}
