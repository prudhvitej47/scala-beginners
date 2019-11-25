package com.prudhvi.lectures.part1basics

object Functions extends App {

    def aFunction(a: String, b: Int): String = {
        a + " " + b
    }

    println(aFunction("Hello", 3))

    // compiler figures out what is the return type
    def anotherFunction(a: String, b: Int) = {
        a + " " + b
    }

    def aParameterLessFunction(): Int = 42
    println(aParameterLessFunction())
    println(aParameterLessFunction)

    // compiler cannot figure out what is the return type of a recursive function
    def aRepeatedFunction(aString: String, n: Int): String = {
        if (n == 1) aString
        else aString + aRepeatedFunction(aString, n-1)
    }

    println(aRepeatedFunction("hello", 3))

    // when you need loops use recursion


    def aFunctionWithSideEffects(aString: String): Unit = println("Hello there")

    def aBigFunction(n: Int): Int = {
        def aSmallerFunction(a: Int, b: Int): Int = a + b

        aSmallerFunction(n, n-1)
    }
    println(aBigFunction(3))
}
