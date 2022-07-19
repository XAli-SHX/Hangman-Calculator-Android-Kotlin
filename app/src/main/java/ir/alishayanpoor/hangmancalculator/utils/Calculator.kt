package ir.alishayanpoor.hangmancalculator.utils

import ir.alishayanpoor.hangmancalculator.exception.AppException

object Calculator {
    @Throws(AppException::class)
    fun calc(rawText: String): String {
        var op: String? = null
        val text = rawText.removeWhiteSpaces()
        op = findOp(text)
        op?.let {
            return when (it) {
                Constants.CALC_BUTTON_SUM -> add(extractNumbers(text, it))
                Constants.CALC_BUTTON_SUB -> sub(extractNumbers(text, it))
                Constants.CALC_BUTTON_DIV -> div(extractNumbers(text, it))
                Constants.CALC_BUTTON_MUL -> mul(extractNumbers(text, it))
                else -> throw AppException("Not supported yet - unknown op")
            }.toString()
        } ?: throw AppException("Not supported yet - null op")
    }

    private fun findOp(text: String): String? {
        text.forEach { char ->
            val op = getArithmeticList().find { it == char.toString() }
            op?.let { return it }
        }
        return null
    }

    fun extractNumbers(
        rawText: String,
        op: String,
    ): Pair<Int, Int> {
        val numData = rawText.split(op)
        val num1 = numData[0].toInt()
        val num2 = numData[1].toInt()
        return Pair(num1, num2)
    }

    fun add(numbers: Pair<Int, Int>) = numbers.first + numbers.second
    fun sub(numbers: Pair<Int, Int>) = numbers.first - numbers.second

    @Throws(AppException::class)
    fun div(numbers: Pair<Int, Int>): Int {
        if (numbers.second == 0)
            throw AppException("Divide by ZERO is not accepted")
        return numbers.first / numbers.second
    }

    fun mul(numbers: Pair<Int, Int>) = numbers.first * numbers.second

    fun getArithmeticList(): List<String> {
        return listOf(
            Constants.CALC_BUTTON_DIV,
            Constants.CALC_BUTTON_MUL,
            Constants.CALC_BUTTON_SUB,
            Constants.CALC_BUTTON_SUM,
        )
    }
}