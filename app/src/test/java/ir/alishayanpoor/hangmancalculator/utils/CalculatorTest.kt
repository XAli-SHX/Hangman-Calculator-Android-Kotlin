package ir.alishayanpoor.hangmancalculator.utils

import com.google.common.truth.Truth
import org.junit.Test
import kotlin.math.absoluteValue
import kotlin.random.Random

class CalculatorTest {

    @Test
    fun `Calculate raw text with divide, correct result`() {
        val num1 = Random(121231231).nextDouble().absoluteValue
        val num2 = Random(121231231).nextDouble().absoluteValue + 1
        Truth.assertThat(Calculator.calc("${num1}/${num2}"))
            .isEqualTo((num1 / num2).toString().removeFloatingPoint())
    }

    @Test
    fun `Calculate raw text with multiply, correct result`() {
        val num1 = Random(121231231).nextDouble().absoluteValue
        val num2 = Random(121231231).nextDouble().absoluteValue
        Truth.assertThat(Calculator.calc("${num1}*${num2}"))
            .isEqualTo((num1 * num2).toString().removeFloatingPoint())
    }

    @Test
    fun `Calculate raw text with sub, correct result`() {
        val num1 = Random(121231231).nextDouble().absoluteValue
        val num2 = Random(121231231).nextDouble().absoluteValue
        Truth.assertThat(Calculator.calc("${num1}-${num2}"))
            .isEqualTo((num1 - num2).toString().removeFloatingPoint())
    }

    @Test
    fun `Calculate raw text with add, correct result`() {
        val num1 = Random(121231231).nextDouble().absoluteValue
        val num2 = Random(121231231).nextDouble().absoluteValue
        Truth.assertThat(Calculator.calc("${num1}+${num2}"))
            .isEqualTo((num1 + num2).toString().removeFloatingPoint())
    }

    @Test
    fun `Divide Big numbers, correct result`() {
        val bigNum1: Double = Double.MAX_VALUE / 3
        val bigNum2: Double = Double.MAX_VALUE / 2
        assert(
            Calculator.div(Pair(bigNum1, bigNum2)) == bigNum1 / bigNum2
        )
    }

    @Test
    fun `Multiply Big numbers, correct result`() {
        val bigNum1: Double = Double.MAX_VALUE / 100000
        val bigNum2: Double = Double.MAX_VALUE / 100000
        assert(
            Calculator.mul(Pair(bigNum1, bigNum2)) == bigNum1 * bigNum2
        )
    }

    @Test
    fun `Sub Big numbers, correct result`() {
        val bigNum1: Double = Double.MAX_VALUE / 3
        val bigNum2: Double = Double.MAX_VALUE / 2
        assert(
            Calculator.sub(Pair(bigNum1, bigNum2)) == bigNum1 - bigNum2
        )
    }

    @Test
    fun `Add Big numbers, correct result`() {
        val bigNum1: Double = Double.MAX_VALUE / 3
        val bigNum2: Double = Double.MAX_VALUE / 2
        assert(
            Calculator.add(Pair(bigNum1, bigNum2)) == bigNum1 + bigNum2
        )
    }

    @Test
    fun `Divide numbers, correct result`() {
        for (i in 0..200) {
            for (j in 1..200) {
                assert(
                    Calculator.div(Pair(i.toDouble(), j.toDouble())) == i.toDouble() / j.toDouble()
                )
            }
        }
    }

    @Test
    fun `Multiply numbers, correct result`() {
        for (i in 0..200) {
            for (j in 0..200) {
                assert(
                    Calculator.mul(Pair(i.toDouble(), j.toDouble())) == i.toDouble() * j.toDouble()
                )
            }
        }
    }

    @Test
    fun `Sub numbers, correct result`() {
        for (i in 0..200) {
            for (j in 0..200) {
                assert(
                    Calculator.sub(Pair(i.toDouble(), j.toDouble())) == i.toDouble() - j.toDouble()
                )
            }
        }
    }

    @Test
    fun `Add numbers, correct result`() {
        for (i in 0..200) {
            for (j in 0..200) {
                assert(
                    Calculator.add(Pair(i.toDouble(), j.toDouble())) == i.toDouble() + j.toDouble()
                )
            }
        }
    }

}