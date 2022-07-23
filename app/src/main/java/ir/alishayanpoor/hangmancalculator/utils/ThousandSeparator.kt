package ir.alishayanpoor.hangmancalculator.utils

import ir.alishayanpoor.logger.Logger
import ir.alishayanpoor.logger.log
import java.text.DecimalFormat

fun String.thousandSeparate(): String {
    return try {
        Logger.log("thousandSeparate: init = $this")
        val formatter = DecimalFormat("#,###")
        formatter.format(this.toDouble()).log(message = "thousandSeparate: final = ")
    } catch (e: Exception) {
        Logger.log(e.localizedMessage)
        this
    }
}