package ir.alishayanpoor.hangmancalculator.domain.use_case

import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.utils.Constants
import ir.alishayanpoor.hangmancalculator.utils.removeLastChar
import ir.alishayanpoor.hangmancalculator.utils.removeWhiteSpaces

class ArithmeticUseCase {
    @Throws(AppException::class)
    fun enterNewChar(old: String, newChar: String, maxCharSize: Int): String {
        val newString = old + newChar
        if (newString.length > maxCharSize)
            throw AppException("Reached max char size which is $maxCharSize")
        newChar.forEach { char ->
            if (getArithmeticList().any { it == char.toString() })
                throw AppException("Currently supporting only one arithmetic operator")
        }
        return newString
    }

    @Throws(AppException::class)
    fun calculateResult(rawText: String): String {
        val raw = rawText.removeWhiteSpaces()
        if (rawText.isBlank())
            throw AppException("There is nothing to calculate")

        return raw
    }

    @Throws(AppException::class)
    fun delete(rawText: String): String {
        if (rawText.isEmpty())
            throw AppException("Empty String; Cannot delete it")
        return rawText.removeLastChar()
    }

    private fun getArithmeticList(): List<String> {
        return listOf(
            Constants.CALC_BUTTON_DIV,
            Constants.CALC_BUTTON_MUL,
            Constants.CALC_BUTTON_SUB,
            Constants.CALC_BUTTON_SUM,
        )
    }

    class MaxCharSizeException(private val maxCharSize: Int) : AppException() {
        override fun getLocalizedMessage(): String {
            return "Reached max char size which is $maxCharSize"
        }
    }

    class DecideByZeroException : AppException() {
        override fun getLocalizedMessage(): String {
            return "Cannot Divide a number by ZERO"
        }
    }
}