package ir.alishayanpoor.hangmancalculator.utils

fun String.thousandSeparate(): String {
    return String.format("%,d", this)
}