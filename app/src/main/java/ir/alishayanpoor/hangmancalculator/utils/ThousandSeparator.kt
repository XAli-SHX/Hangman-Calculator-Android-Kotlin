package ir.alishayanpoor.hangmancalculator.utils

import ir.alishayanpoor.logger.Logger
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun String.thousandSeparate(): String {
    return try {
        Logger.log(this)
        val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        formatter.applyPattern("#,###")
        formatter.format(this.toDouble())
    } catch (e: Exception) {
        Logger.log(e.localizedMessage)
        this
    }
}