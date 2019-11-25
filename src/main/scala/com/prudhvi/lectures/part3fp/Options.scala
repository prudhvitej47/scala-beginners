package com.prudhvi.lectures.part3fp

object Options extends App {

    val myFirstOption: Option[Int] = Some(4)
    val noOption: Option[Int] = None
    println(myFirstOption)

    def unsafeMethod(): String = null
    // val result = Some(null) // WRONG
    val result = Option(unsafeMethod())
    println(result)
    // whole point of Option is we don't need to code a null check ourselves

    // chained methods
    def backupMethod(): String = "A valid method"
    val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))


    def betterUnsafeMethod(): Option[String] = None
    def betterBackupMethod(): Option[String] = Some("A valid method")
    val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()


    // functions on Options
    println(myFirstOption.isEmpty)
    println(myFirstOption.get)   // unsafe if the option is null

    // map, flatMap, filter
    println(myFirstOption.map(_ * 2))
    println(myFirstOption.filter(x => x > 10))
    println(myFirstOption.flatMap(x => Option(x * 10)))

    // for-comprehensions
}
