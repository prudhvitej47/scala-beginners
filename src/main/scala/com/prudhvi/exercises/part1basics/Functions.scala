package com.prudhvi.exercises.part1basics

object Functions extends App {

    // tasks
    def aGreetingFunction(name: String, age: Int) = {
        println("Hi, my name is " + name + " and I am " + age + " years old")
    }
    aGreetingFunction("Prudhvi", 22)

    def factorial(n: Int) : Int = {
        if (n == 1) n
        else n * factorial(n-1)
    }
    println(factorial(5))

    def fibonacci(n: Int): Int = {
        if (n <= 1) 1
        else fibonacci(n-1) + fibonacci(n-2)
    }

    def isPrimeNumber(n: Int): Boolean = {
        def isPrimeUntil(t: Int): Boolean = {
            if (t <= 1) true
            else n%t != 0 && isPrimeUntil(t-1)
        }

        isPrimeUntil(n/2)
    }
    println(isPrimeNumber(5))
}
