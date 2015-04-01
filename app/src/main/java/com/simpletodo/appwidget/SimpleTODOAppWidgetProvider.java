package com.simpletodo.appwidget;


import com.example.simpletodo.R;
import com.simpletodo.appwidget.service.ListViewAdaptorService;
import com.simpletodo.main.MainActivity;
import com.simpletodo.util.AppWidgetUtils;
import com.simpletodo.util.LogUtil;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class SimpleToDoAppWidgetProvider extends AppWidgetProvider {
    public RemoteViews remoteViews;

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        //当App Widget的实例从宿主中删除时被调用。
        LogUtil.e("SimpleToDoAppWidgetProvider", "onDeleted");
    }

    @Override
    public void onDisabled(Context context) {
        //当你的App Widget的最后一个实例被从宿主中删除时被调用。因为：譬如上图中的人人网小部件，你可添加N个实例。
        LogUtil.e("SimpleToDoAppWidgetProvider", "onDisabled");
    }

    @Override
    public void onEnabled(Context context) {
        // 当一个App Widget实例第一次创建时被调用。
        LogUtil.i("SimpleToDoAppWidgetProvider", "onEnabled");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        //这个接收到每个广播时都会被调用，而且在上面的回调函数之前。你通常不需要实现这个方法，
        // 因为缺省的AppWidgetProvider实现过滤所有AppWidget 广播并恰当的调用上述方法。
        LogUtil.i("SimpleToDoAppWidgetProvider", "onReceive");
        if (null != intent.getAction()) {
            LogUtil.i("SimpleToDoAppWidgetProvider onReceive", intent.getAction());
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidget = new ComponentName(context.getPackageName(), SimpleToDoAppWidgetProvider.class.getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

        if (null != intent.getAction() && intent.getAction().equals("android.appwidget.action.APPWIDGET_UPDATE")) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }

        if (null != intent.getAction() && intent.getAction().equals(context.getPackageName())) {
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        LogUtil.i("SimpleToDoAppWidgetProvider", "onUpdate");
        if (remoteViews == null) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.appwidget_main);
        }
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            setListView(context, appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds[i], R.id.appwidget_listview);
        }
    }

    private void setListView(Context context, int appWidgetId) {

        Intent svcIntent = new Intent(context, ListViewAdaptorService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(R.id.appwidget_listview, svcIntent);
        remoteViews.setEmptyView(R.id.appwidget_listview, R.id.emepty);

        remoteViews.setOnClickPendingIntent(R.id.ib_task_new, AppWidgetUtils.createConfigurePendingIntent(context, appWidgetId, MainActivity.class));
    }


}
