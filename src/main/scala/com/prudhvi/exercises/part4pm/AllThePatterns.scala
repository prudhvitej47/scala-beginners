package com.prudhvi.exercises.part4pm

import com.prudhvi.exercises.part2oop.{ConsGeneric, EmptyGeneric, MyListGeneric}

object AllThePatterns extends App {

    // 1- constants
    val x: Any = "Scala"
    val constants = x match {
        case 1 => "a number"
        case "Scala" => "THE Scala"
        case true => "The Truth"
        case AllThePatterns => "A singleton object"
    }
    println(constants)

    // 2 -match anything
    // 2.1 - wildcard
    val matchAnything = x match {
        case _ =>
    }

    // 2.2 - Variables
    val matchAVariable = x match {
        case something => s"I've found $something"
    }

    // 3 - Tuples
    val aTuple = (1, 2)
    val matchTuple = aTuple match {
        case (1, 2) =>
        case (something, 2) => s"I've found $something"
    }

    val nestedTuple = (1, (2, 3))
    val matchNestedTuple = nestedTuple match {
        case (_, (2, v)) =>
    }

    // 4 -case classes - constructor pattern
    // pattern matching can be nested with case classes as well
    val aList: MyListGeneric[Int] = ConsGeneric(1, ConsGeneric(2, EmptyGeneric))
    val matchAList = aList match {
        case EmptyGeneric =>
        case ConsGeneric(head, ConsGeneric(subhead, subtail)) =>
    }

    // 5 - list patterns
    val aStandardList = List(1, 2, 3, 42)
    val standardListMatch = aStandardList match {
        case List(1, _, _, _) => //extractor for list
        case List(1, _*) =>  // list of arbitrary length
        case 1 :: List(_) => // infix pattern
        case List(1, 2, 3) :+ 42 =>   // also an infix pattern
    }


    // 6 - type specifiers
    val unknown: Any = 2
    val unknownMatch = unknown match {
        case list: List[Int] =>  // explicit type specifiers
        case _  =>
    }

    // 7 - name binding
    val nameBindingMatch = aList match {
        case nonEmptyList @ ConsGeneric(_, _) => {nonEmptyList.toString}// name binding which makes us use the name later(here)
        case ConsGeneric(1, rest @ ConsGeneric(2, _)) =>  // name binding inside nested patterns
    }


    // 8 - Multi patterns
    /*val multipattern: String = aList match {
        case EmptyGeneric | ConsGeneric(0, _) => ""
    }*/

    // 9 -if guards
    val secondElementSpecial = aList match {
        case ConsGeneric(_, ConsGeneric(specialElement, _)) if specialElement % 2 == 0 =>
    }

    // trick questions
    val numbers = List(1,2,3)
    val numbersMatch = numbers match {
        case listOfStrings: List[String] => "a list of strings"
        case listOfInts: List[Int] => "a list of ints"
    }
    println(numbersMatch)



    val tuple = (1,2,3)
    val (a,b,c) = tuple
    // multiple value definition based on pattern matching

    val list = List(1,2,3,4)
    val head :: tail = list
    println(head)
    println(tail)

    // partial function
    val mappedList = list.map{
        case v if v % 2 == 0 => v + " is even"
        case 1 => "the one"
        case _ => "something else"
    } // partial function literal

    val mappedList2 = list.map{ x => x match {
            case v if v % 2 == 0 => v + " is even"
            case 1 => "the one"
            case _ => "something else"
        }
    }
    println(mappedList)
}
