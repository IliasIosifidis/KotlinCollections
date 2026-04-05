package collections

import calculator.Calculator
import calculator.LoggingCalculator
import calculator.SimpleCalculator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CalculatorTest {

    companion object{
        @JvmStatic
        fun calculatorsSource() = listOf(SimpleCalculator(), LoggingCalculator())
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testSum(calculator: Calculator){
        val result = calculator.sum(10,5)
        val expected = 15
        assertEquals(result, expected)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testSum2(calculator: Calculator){
        val result = calculator.sum(100,50)
        val expected = 150
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testSub(calculator: Calculator){
        val result = calculator.subtraction(10,9)
        val expected = 1
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testSub2(calculator: Calculator){
        val result = calculator.subtraction(77,11)
        val expected = 66
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testMulti(calculator: Calculator){
        val result = calculator.multiply(5, 8)
        val expected = 40
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testMulti2(calculator: Calculator){
        val result = calculator.multiply(60, 6)
        val expected = 360
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testDiv(calculator: Calculator){
        val result = calculator.division(10, 5)
        val expected = 2.0
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("calculatorsSource")
    fun testDiv2(calculator: Calculator){
        val result = calculator.division(77, 10)
        val expected = 7.7
        assertEquals(expected, result)
    }

}