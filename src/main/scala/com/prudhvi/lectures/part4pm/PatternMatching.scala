package com.prudhvi.lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

    // switch on steroids
    val random = new Random
    val x = random.nextInt(10)

    val description = x match {
        case 1 => "single"
        case 2 => "double"
        case 3 => "triple"
        case 4 => "quadruple"
        case _ => "other"
    }
    println(x)
    println(description)


    // decompose values
    case class Person(name: String, age: Int)
    val bob = Person("Bob", 18)

    val greeting = bob match {
        case Person(n, a) if a < 21 => s"Hi my name is $n and my age is $a and I can't drink in USA"
        case Person(n, a) => s"Hi my name is $n and my age is $a"
        case _ => "I don't know who I am"
    }
    println(greeting)


    sealed class Animal
    case class Dog(breed: String) extends Animal
    case class Parrot(greeting: String) extends Animal

    val animal: Animal = Dog("Terra Nova")

    /*animal match {
        case Dog(breed) => println(s"Matched a dog of breed $breed")
    }
    // will get an error as for sealed classes we need to add all cases of patterns
     */


    // match everything
    val isEven = x match {
        case n if n % 2 == 0 => true
        case _ => false
    }  // overkill

    val isEvenNormal = x % 2 == 0
    println(isEven)
}
