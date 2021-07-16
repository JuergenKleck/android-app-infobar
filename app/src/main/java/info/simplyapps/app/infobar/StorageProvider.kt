package info.simplyapps.app.infobar

import android.content.Context

class StorageProvider {

    companion object {

        private const val fileName = "infobar.data"

        fun checkFile(context: Context) {
            if (!context.fileList().contains(fileName)) {
                writeContent(context, "")
            }
        }

        fun writeContent(context: Context, content: String) {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(content.toByteArray())
            }
        }

        fun readContent(context: Context): String =
            context.openFileInput(fileName).use { return String(it.readBytes()) }
    }

}