package com.prudhvi.lectures.part1basics

object StringOps extends App {

    val str: String = "Hello, I am learning Scala"

    println(str.charAt(2))
    println(str.substring(9, 11))
    println(str.split(',').toList)
    println(str.startsWith("Hello"))
    println(str.replace("Scala", "Scala!"))
    println(str.toLowerCase())
    println(str.length)

    println(str.reverse)
    // taking characters out the string
    println(str.take(2)) // prints 'He'

    val aNumberString = "45"
    println(aNumberString.toInt)

    // append operator
    println("a" +: aNumberString)
    println("a" +: aNumberString +: "z")

    // String interpolators
    val name = "David"
    val age = 45
    val greeting = s"Hello, my name is $name and I am $age years old"
    val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
    println(greeting)
    println(anotherGreeting)

    // F-Interpolators - (2.2f means 2 characters total,minimum and 2 decimals precision)
    val speed = 1.2f
    val myth = f"$name can eat $speed%2.2f burgers per minute"
    println(myth)

    val newSpeed = 2 // types should match
    val anotherMyth = f"$name can eat $newSpeed%3d burgers per minute"
    println(anotherMyth)

    // raw interpolator - prints literally
    println(raw"This is a \n newline")
    // this can be escaped by
    val escaped = "This is a \n newline"
    println(raw"$escaped")
}
