package ir.alishayanpoor.logger

import android.util.Log
import com.google.gson.GsonBuilder

object Logger {
    fun log(message: String?, tag: String = "Ali") {
        if (BuildConfig.BUILD_TYPE == "debug")
            Log.i(tag, message ?: "NULL")
    }

    fun logDiv(tag: String = "Ali") {
        log("----------------------------------------", tag)
    }

    fun jsonLog(message: Any?, tag: String = "Ali") {
        log(GsonBuilder().setPrettyPrinting().create().toJson(message), tag)
    }
}

fun <T> T.log(tag: String = "Ali"): T {
    Logger.log(this.toString(), tag)
    return this
}

fun <T> T.jsonLog(tag: String = "Ali"): T {
    Logger.jsonLog(this, tag)
    return this
}