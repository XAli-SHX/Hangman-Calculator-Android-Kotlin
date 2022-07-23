package ir.alishayanpoor.hangmancalculator.utils

import ir.alishayanpoor.hangmancalculator.exception.AppException
import kotlin.math.absoluteValue

object Calculator {
    @Throws(AppException::class)
    fun calc(rawText: String): String {
        var op: String? = null
        val text = rawText.removeWhiteSpaces()
        op = findOp(text)
        val res = op?.let {
            when (it) {
                Constants.CALC_BUTTON_SUM -> add(extractNumbers(text, it))
                Constants.CALC_BUTTON_SUB -> sub(extractNumbers(text, it))
                Constants.CALC_BUTTON_DIV -> div(extractNumbers(text, it))
                Constants.CALC_BUTTON_MUL -> mul(extractNumbers(text, it))
                else -> throw AppException("Not supported yet - unknown op")
            }.absoluteValue.toString()
        } ?: throw AppException("Not supported yet - null op")
        return res.removeFloatingPoint()
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
    ): Pair<Double, Double> {
        val numData = rawText.split(op)
        val num1 = numData[0].toDouble()
        val num2 = numData[1].toDouble()
        return Pair(num1, num2)
    }

    fun add(numbers: Pair<Double, Double>) = numbers.first + numbers.second
    fun sub(numbers: Pair<Double, Double>) = numbers.first - numbers.second

    @Throws(AppException::class)
    fun div(numbers: Pair<Double, Double>): Double {
        if (numbers.second == 0.toDouble())
            throw AppException("Divide by ZERO is not accepted")
        return numbers.first / numbers.second
    }

    fun mul(numbers: Pair<Double, Double>) = numbers.first * numbers.second

    fun String.isArithmeticOperation(): Boolean {
        return getArithmeticList().any { this == it }
    }

    fun getArithmeticList(): List<String> {
        return listOf(
            Constants.CALC_BUTTON_DIV,
            Constants.CALC_BUTTON_MUL,
            Constants.CALC_BUTTON_SUB,
            Constants.CALC_BUTTON_SUM,
        )
    }
}