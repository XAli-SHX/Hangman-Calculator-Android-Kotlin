package ir.alishayanpoor.hangmancalculator.utils

fun String.removeFloatingPoint(): String {
    if (!this.contains('.'))
        return this
    val floatingPointIndex = this.indexOf('.')
    return this.substring(0, floatingPointIndex + 1)
}