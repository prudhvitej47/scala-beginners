package com.prudhvi.lectures.part3fp

object AnonymousFunctions extends App {

    /*val doubler = new Function1[Int, Int] {
        override def apply(v1: Int): Int = v1 * 2
    }*/

    // anonymous function or lambda
    // val doubler = (x: Int) => x * 2
    val doubler: Int => Int = x => x * 2

    // val adder = (a: Int, b: Int) => a + b
    val adder: (Int, Int) => Int = (a, b) => a + b

    // no params
    val doSomething = () => 3
    println(doSomething) // function
    println(doSomething()) // function call

    // curly braces wih lambda
    // val stringToInt: String => Int = string => string.toInt
    val stringToInt = { str: String =>
        str.toInt
    }

    val niceIncrementer: Int => Int = _ + 1  // equivalent to x => x + 1
    val niceAdder: (Int, Int) => Int = _ + _  // equivalent to (a, b) => a + b

    // special function of WhatsAFunction in exercises
    // val specialFunction: Int => (Int => Int) = x => (y => x + y)
    val specialFunction = (x: Int) => (y: Int) => x + y
    println(specialFunction(10)(1))
}
