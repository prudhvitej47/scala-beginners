package com.prudhvi.exercises.part3fp

object WhatsAFunction extends App {

    /*val concat: Function2[String, String, String] = new Function2[String, String, String] {
        override def apply(v1: String, v2: String): String = v1 + v2
    }
    println(concat("Hello", " World"))
     */

    val concat: (String, String) => String = new Function2[String, String, String] {
        override def apply(v1: String, v2: String): String = v1 + v2
    }
    println(concat("Hello", " World"))

    // define a function that takes and Int and returns a function that takes an Int and returns an Int
    val specialFunction: Int => Function1[Int, Int] = new Function[Int, Function1[Int, Int]] {
        override def apply(x: Int): Function1[Int, Int] = new Function1[Int, Int] {
            override def apply(y: Int): Int =  x + y
        }
    }
    println(specialFunction(1)(2))  // curried functions

    val adder3 = specialFunction(3)
    println(adder3(4))
    println(adder3(10))
}
