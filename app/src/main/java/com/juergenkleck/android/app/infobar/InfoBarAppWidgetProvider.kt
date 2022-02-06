package com.juergenkleck.android.app.infobar

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Android app - InfoBar
 *
 * Copyright 2022 by Juergen Kleck <develop@juergenkleck.com>
 */
class InfoBarAppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        context?.let { StorageProvider.checkFile(context) }

        // Perform this loop procedure for each App Widget that belongs to this provider
        appWidgetIds?.forEach { appWidgetId ->
            // Create an Intent to launch Config activity
            val pendingIntent: PendingIntent = Intent(context, ConfigActivity::class.java)
                .let { intent ->
                    PendingIntent.getActivity(context, 0, intent, 0)
                }

            // Get the layout for the App Widget and attach an on-click listener to the button
            val views: RemoteViews = RemoteViews(context?.packageName, R.layout.app_widget_layout).apply {
                setTextViewText(R.id.widget_textview, context?.let { StorageProvider.readContent(it) })
                setOnClickPendingIntent(R.id.button_config, pendingIntent)
            }

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }

    }

}