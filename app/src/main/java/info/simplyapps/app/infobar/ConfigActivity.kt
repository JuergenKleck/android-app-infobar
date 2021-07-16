package info.simplyapps.app.infobar

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RemoteViews

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        // check if the data file exists
        StorageProvider.checkFile(applicationContext)

        // load the text
        findViewById<EditText>(R.id.config_edittext).text.append(StorageProvider.readContent(applicationContext))

        val appWidgetId = intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        // set click listener to update the data file and close the activity
        findViewById<Button>(R.id.button_update).setOnClickListener {
            StorageProvider.writeContent(context = applicationContext, findViewById<EditText>(R.id.config_edittext).text.toString())

            // trigger the widget update
            val appWidgetManager: AppWidgetManager = AppWidgetManager.getInstance(applicationContext)
            val views: RemoteViews = RemoteViews(applicationContext.packageName, R.layout.app_widget_layout).apply {
                setTextViewText(R.id.widget_textview, applicationContext?.let { StorageProvider.readContent(it) })
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)

            // return the activity with OK result
            val resultValue = Intent().apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            }
            setResult(Activity.RESULT_OK, resultValue)
            // close the activity
            finish()
        }
    }
}