package com.prudhvi.lectures.part1basics

object Expressions extends App {
    val x = 1 + 2
    println(x)

    println(3 == x)

    // Instructions vs Expressions
    // Instructions - telling compiler what to do (changing variable, print this, etc)
    // Expressions - commputing a value

    // if expression
    val condition = true
    val conditionValue = if(condition) 5 else 3
    println(conditionValue)
    println("-----------------")

    var i = 0
    var aWhile = while (i < 5) {
        println(i)
        i += 1
    }
    println(aWhile)

    var aVariable = 2
    val weirdValue = (aVariable = 3)  // Unit == void
    println(weirdValue)

    // side effects : println(), while, reassigning

    // code blocks - it is an expression
    // values defined in code blocks cannot be used outside
    val aCodeBlock = {
        val y = 2
        val z = y + 1

        if (z > 2) "Hello" else "goodbye"
    }
    println(aCodeBlock)

    // do not use loops in Scala code as they return a Unit

    val someValue = {
        2 < 3
    }
    val someOtherValue = {
        if (someValue) 259 else 999
        42
    }

    println(someValue, someOtherValue)
}
