package com.prudhvi.lectures.part1basics

object CallingByNameValue extends App {

    def callByValue(x: Long): Unit = {
        println("calling by value " + x)
        println("calling by value " + x)
    }

    def callByName(x: => Long): Unit = {
        println("calling by name " + x)
        println("calling by name " + x)
    }

    callByValue(System.nanoTime())

    // evaluates the expression every time it is called
    callByName(System.nanoTime())
    callByName(5)


    def infinite(): Int = 1 + infinite()
    def printFirst(x: Int, y: => Int): Unit = println(x)

    // printFirst(infinite(), 34)   this will crash
    printFirst(34, infinite())
}
