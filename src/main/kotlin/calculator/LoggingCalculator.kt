package calculator

class LoggingCalculator: Calculator {
    override fun sum(a:Int, b: Int): Int{
        println("Operation sum $a, $b}")
        return a + b
    }
    override fun subtraction(a: Int, b: Int): Int{
        println("Operation sub $a, $b}")
        return a - b
    }
    override fun multiply(a: Int, b: Int): Int{
        println("Operation mul $a, $b}")
        return a * b
    }
    override fun division(a: Int, b: Int): Double {
        println("Operation div $a, $b}")
        return (a.toDouble() / b.toDouble())
    }
}