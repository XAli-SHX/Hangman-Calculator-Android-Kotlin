package ir.alishayanpoor.hangmancalculator.utils

fun String.removeWhiteSpaces(): String {
    return this.replace(" ", "")
}

fun String.removeLastNChars(n: Int): String {
    require(n >= 0) { "n should be positive" }
    require(this.length >= n) { "string length should be greater than n" }
    return this.substring(0, this.length - n)
}

fun String.removeLastChar(): String {
    return this.removeLastNChars(1)
}