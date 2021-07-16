package info.simplyapps.app.infobar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Activity for pure debugging reasons as the debugger cannot attach to a widget
 */
class DebugLauncher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug_launcher)
    }
}