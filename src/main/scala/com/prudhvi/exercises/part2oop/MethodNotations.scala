package com.prudhvi.exercises.part2oop

import scala.language.postfixOps

object MethodNotations extends App {

    class Person(val name: String, val age: Int, favoriteMovie: String) {
        def +(nickname: String): Person = new Person(s"$name ($nickname)", age, favoriteMovie)
        def unary_+ : Person = new Person(name, age + 1, favoriteMovie)
        def learns(subject: String) = s"$name learns $subject"
        def learnsScala: String = this learns "Scala"
        def apply(num: Int) = s"$name watched $favoriteMovie $num times"
    }

    val mary = new Person("Mary", 35, "Inception")

    println((mary + "the rockstar").name)
    println((+mary).age)

    println(mary learns "Scala")
    println(mary learnsScala)

    println(mary apply 2)
    println(mary.apply(2))
    println(mary (2))
}
