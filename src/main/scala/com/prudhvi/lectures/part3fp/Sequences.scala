package com.prudhvi.lectures.part3fp

import scala.util.Random

object Sequences extends App {

    // Seq
    val aSequence = Seq(1, 3, 2, 4)
    println(aSequence)
    println(aSequence.reverse)
    println(aSequence(2)) // returns element at index 2 - calling apply method
    println(aSequence ++ Seq(5, 6, 7))  // ++ is concatenation
    println(aSequence.sorted)

    // Ranges
    val aRange: Seq[Int] = 1 to 10
    aRange.foreach(println)

    (1 until 5).foreach{ _ =>
        println("Hello")
    }  // 5 not inclusive


    // lists
    val aList = List(1, 2, 3)
    val prepended1 = 42 :: aList
    val prepended2 = 42 +: aList :+ 89
    println(prepended2)

    // list of 5 strings
    val apple5 = List.fill(5)("apple")
    println(apple5)
    println(aList.mkString("-"))


    // arrays - allocates memory for elements without having to provide values
    // if we do not provide any value it gives it a default value - (o for int, null for string)
    val numbers = Array(1,2,3,4)
    val threeElements = Array.ofDim[Int](3)
    threeElements.foreach(println)

    // mutation
    numbers(2) = 0  // syntax sugar for numbers.update(2, 0)
    println(numbers.mkString(" "))

    // arrays and sequences
    val numbersSeq: Seq[Int] = numbers   // implicit conversion
    println(numbersSeq)

    // vectors
    val vector: Vector[Int] = Vector(1, 2, 3)
    println(vector)

    // vectors vs lists
    val maxRuns = 1000
    val maxCapacity = 1000000
    def getWriteTime(collection: Seq[Int]): Double = {
        val r = new Random
        val times = for {
            it <- 1 to maxRuns
        } yield {
            val currentTime = System.nanoTime()
            collection.updated(r.nextInt(maxCapacity), 0)
            System.nanoTime() - currentTime
        }

        // returning the average
        times.sum + 1.0 / maxRuns
    }

    val numbersList = (1 to maxCapacity).toList
    val numbersVector = (1 to maxCapacity).toVector

    // keeps reference in tail but if we try to update element in middle of list it is not efficient
    println(getWriteTime(numbersList))

    // to update a vector - depth of tree is small but needs to replace the entire 32 element chunk
    println(getWriteTime(numbersVector))
}
