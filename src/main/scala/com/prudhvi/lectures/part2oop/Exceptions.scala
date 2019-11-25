package com.prudhvi.lectures.part2oop

object Exceptions extends App {

    val x: String = null
    // println(x.length) - throws nullpointerexception

    // aWeirdValue is of type Nothing
    //val aWeirdValue = throw new NullPointerException
    //val nullValue: String = throw new NullPointerException

    // throwable classes extend the Throwable class

    def getInt(withException: Boolean): Int = {
        if (withException) throw new RuntimeException("No int for you!")
        else 42
    }

    try {
        getInt(true)
    } catch {
        case e: RuntimeException => println(s"caught runtime exception: ${e.getLocalizedMessage}")
    } finally {
        println("finally")
    }

    // define your own exception
    class MyException extends Exception
    val exception = new MyException
    throw exception
}
