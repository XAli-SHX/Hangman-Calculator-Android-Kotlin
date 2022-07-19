package ir.alishayanpoor.hangmancalculator.utils

fun <T> List<T>.remove(element: T): List<T> {
    val newList = mutableListOf<T>()
    this.forEach {
        if (it != element)
            newList.add(it)
    }
    return newList.toList()
}