package ir.alishayanpoor.hangmancalculator.domain.use_case

import ir.alishayanpoor.hangmancalculator.exception.AppException
import ir.alishayanpoor.hangmancalculator.utils.Calculator
import ir.alishayanpoor.hangmancalculator.utils.removeLastChar
import ir.alishayanpoor.hangmancalculator.utils.removeWhiteSpaces

class ArithmeticUseCase {
    @Throws(AppException::class)
    fun enterNewChar(old: String, newChar: String, maxCharSize: Int): String {
        val newString = old + newChar
        if (newString.length > maxCharSize)
            throw AppException("Reached max char size which is $maxCharSize")
        old.forEach { char ->
            if (Calculator.getArithmeticList().any { (it == char.toString() && it == newChar) })
                throw AppException("Currently supporting only one arithmetic operator")
        }
        return newString
    }

    @Throws(AppException::class)
    suspend fun calculateResult(rawText: String): String {
        if (rawText.removeWhiteSpaces().isBlank())
            throw AppException("There is nothing to calculate")
        return Calculator.calc(rawText)
    }

    @Throws(AppException::class)
    fun delete(rawText: String): String {
        if (rawText.isEmpty())
            throw AppException("Empty String; Cannot delete it")
        return rawText.removeLastChar()
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