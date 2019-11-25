package com.prudhvi.lectures.part3fp

object MapFlatMapFilterFor extends App {
    // calling apply method in List class
    val list = List(1, 2, 3)
    println(list.head)
    println(list.tail)

    // map
    println(list.map(_ + 1))
    println(list.map(_ + " is a number"))

    // filter
    println(list.filter(_ % 2 == 0))

    // flatMap
    val toPair = (x: Int) => List(x, x + 1)
    println(toPair(2))
    //println(list.flatMap(x => List(x, x + 1)))
    println(list.flatMap(toPair))

    // printing all combinations between two lists
    val numbers = List(1, 2, 3, 4)
    val chars = List('a', 'b', 'c', 'd')
    val colors = List("black", "white")

    println(chars.flatMap(c => numbers.map(c + "" + _)))

    val combinations = chars.flatMap(c => numbers.flatMap(n => colors.map("" + c + n + "-" + _)))

    // foreach
    list.foreach(println)

    // for comprehensions
    val forCombinations = for {
        n <- numbers if n % 2 == 0
        c <- chars
        color <- colors
    } yield "" + c + n + "-" + color
    println(forCombinations)


    // syntax overload
    println(list.map{ x =>
        x * 2
    })
}
