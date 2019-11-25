package com.prudhvi.lectures.part1basics

object Recursion extends App {

    def factorial(n: Int): Int = {
        if (n == 1) 1
        else {
            println("Computing the factorial of " + n + " - I first need factorial of " + (n-1))
            val result = n * factorial(n-1)
            println("Computed the factorial of " + n)
            result
        }
    }

    // println(factorial(5))

    // println(factorial(5000))) will give stack overflow error

    // preventing additional calls
    def anotherFactorial(n: Int): BigInt = {
        def factorialHelper(x: Int, accumulator: BigInt): BigInt = {
            if (x <= 1) accumulator
            else factorialHelper(x - 1, x * accumulator) // TAIL RECURSION- use recursive call as last expression
        }

        factorialHelper(n, 1)
    }

    //println(anotherFactorial(5000))


    def concatString(aString: String, n: Int): String = {
        def concatHelper(str: String, x: Int): String = {
            if (x < 1) str
            else concatHelper(str + aString, x - 1)
        }
        concatHelper(aString, n)
    }
    println(concatString("hello", 5))


    def isPrime(n: Int): Boolean = {
        def primeNumberDetector(n: Int, x: Int): Boolean = {
            if (x == 1) true
            else if (n % x == 0) false
            else primeNumberDetector(n, x - 1)
        }

        primeNumberDetector(n, n/2)
    }
    println(isPrime(2003))

    def fibonacci(n: Int): Int = {
        def fibonacciCalculator(x: Int, prev: Int, accumulator: Int): Int = {
            if (x == n) accumulator
            else fibonacciCalculator(x + 1, accumulator, prev + accumulator)
        }

        fibonacciCalculator(1, 0, 1)
    }
    println(fibonacci(6))
}
