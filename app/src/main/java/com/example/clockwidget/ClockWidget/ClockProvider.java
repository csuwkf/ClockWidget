package com.example.clockwidget.ClockWidget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import com.example.clockwidget.DateSettingsActivity;
import com.example.clockwidget.R;

public class ClockProvider extends AppWidgetProvider {
    private static final String TAG = "AppWidgetProvider";
    private static final String ACTION_TIMEZONE_CHANGED = Intent.ACTION_TIMEZONE_CHANGED;

    // 刷新的时候执行
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for(int i=0;i<appWidgetIds.length;i++){
            Intent intent = new Intent(context, DateSettingsActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteViews =new RemoteViews(context.getPackageName(), R.layout.clockwidge_activity);
            remoteViews.setOnClickPendingIntent(R.id.ll_clockwidge,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    // 第一个添加到屏幕上
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        // 启动Service
        context.startService(new Intent(context, ClockService.class));
    }

    // 最后一个widget从屏幕移除
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        context.stopService(new Intent(context, ClockService.class));
    }

    // 从屏幕移除
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
}
