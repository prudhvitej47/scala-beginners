package com.prudhvi.lectures.part1basics

object ValuesVaraiblesTypes extends App {
    // val cannot be reassigned. They are immutable
    val x: Int = 42
    println(x)

    // types of val are optional. Compiler can infer types
    val y = 42
    println(y)

    val aString: String = "hello"
    val anotherString = "world"
    println(aString + anotherString)

    val aBoolean = false
    val aChar = 'a'

    val anInt: Int = x
    val aShort : Short = 12345
    val aLong : Long = 1234567891123123231L
    val aFloat : Float = 2.0f
    val aDouble : Double = 3.14

    // variables. They are mutable
    var aVariable : Int = 4
    aVariable = 5
    aVariable += 1
}
