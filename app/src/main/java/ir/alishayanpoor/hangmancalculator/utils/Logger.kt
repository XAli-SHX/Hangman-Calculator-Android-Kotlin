package ir.alishayanpoor.hangmancalculator.utils

import android.util.Log

object Logger {
    fun log(message: String?, tag: String = "Ali") {
        Log.i(tag, message ?: "NULL")
    }

    fun logDiv(tag: String = "Ali") {
        Log.i(tag, "----------------------------------------")
    }
}

fun <T> T.log(tag: String = "Ali"): T {
    Logger.log(this.toString(), tag)
    return this
}