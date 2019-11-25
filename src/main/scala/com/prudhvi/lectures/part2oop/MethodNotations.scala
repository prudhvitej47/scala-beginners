package com.prudhvi.lectures.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

    class Person(val name: String, val age: Int, favoriteMovie: String) {
        def likes(movie: String): Boolean = this.favoriteMovie == movie
        def +(person: Person): String = s"${this.name} hangs out with ${person.name}"
        // put space after method name otherwise compiler thinks : is a part of method name
        def unary_! : String = s"$name what the heck?!"
        def isAlive: Boolean = true
        def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"
    }

    val mary = new Person("Mary", 35, "Inception")
    println(mary.likes("Inception"))

    // Infix or operator notation - works only with methods having one parameter
    println(mary likes "Inception") // equivalent

    // operators
    val tom = new Person("Tom", 25,"Fight Club")
    println(mary + tom)

    // all operators are methods
    println(1 + 2)
    // 1 has a method + with parameter 2
    println(1.+(2))

    // prefix notation
    val x = -1
    val y = 1.unary_-
    // unary_ prefix

    println(!mary)
    println(mary.unary_!)

    // postfix notation
    println(mary.isAlive)
    println(mary isAlive)

    println(mary.apply())
    println(mary ()) // equivalent - whenever we mention () compiler checks for apply method in the class
}
