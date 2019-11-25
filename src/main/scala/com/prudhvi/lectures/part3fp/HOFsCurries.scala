package com.prudhvi.lectures.part3fp

object HOFsCurries extends App {
    val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null

    // higher order functions (HOF)

    // function that applies a function n times over a given value x
    // nTimes(f,3,x) = f(f(f(x)))
    def nTimes(f: Int => Int, n: Int, x: Int): Int = {
        if (n <= 0) x
        else nTimes(f, n - 1, f(x))
    }
    def plusOne(value: Int): Int = value + 1
    println(nTimes(plusOne, 3, 5))

    // nTimesBetter(f, n) = f(f(f....f(x))))
    def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
        if (n <= 0) (x: Int) => x
        else (x: Int) => nTimesBetter(f, n - 1)(f(x))
    }

    val plus10 = nTimesBetter(plusOne, 10)
    println(plus10(1))

    // curried functions
    // arrow sign is right associative
    val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
    val add3 = superAdder(3)
    println(add3(1))

    // functions with multiple parameter lists
    def curriedFormatter(c: String)(x: Double): String = c.format(x)
    val standardFormat: Double => String = curriedFormatter("%4.2f")
    val preciseFormat: Double => String = curriedFormatter("%10.8f")

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))
}
