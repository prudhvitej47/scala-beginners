package com.prudhvi.playground

import java.io.FileReader

import scala.annotation.tailrec
import scala.language.postfixOps
import scala.util.{Failure, Success, Try}

object ScalaPlayground extends App {
    
    val m = Array.ofDim[Int](3, 3)
    m(0)(0) = 5
    
    // higher order functions - takes a function as a parameter or returns a function as a result
    
    def variableArguments(args: Int*): Int = {
        var n = 0
        for (arg <- args) {
            n += arg
        }
        n
    }
    
    // function literals or anonymous functions......a function that does not have a name
    // every function is a value
    val increment: Int => Int = (x: Int) => x + 1
    println(increment(5))
    
    def foo(x: Int, f: Int => Int): Int = {
        f(x)
    }
    
    val n = foo(5, increment)
    // anonymous functions
    val n_1 = foo(5, (x: Int) => x + 1)
    
    
    // anonymous functions with multiple parameters
    val myfc1 = (str1: String, str2: String) => str1 + str2
    // using wildcard instead of name as str1 and str2 occurs only once
    val myfc2 = (_:String) + (_:String)
    
    
    // partial functions
    def speed(distance: Float, time: Float): Float = {
        distance / time
    }
    
    // scala allows to partially call speed with a subset of
    val partialSpeed: Float => Float = speed(5, _)
    println(partialSpeed(2.5f))
    
    
    // currying
    def multiply(n1: Int)(n2: Int): Int = {
        n1 * n2
    }
    
    val multiply2 = multiply(2)(_)
    println(multiply2(4))
    
    
    case class Context()
    
    // benefits of currying and partial functions
    def send(context: Context, message: Array[Byte]): Unit = {
        // Send message
    }
    
    // other way
    def sendImpl(message: Array[Byte])(implicit context: Context): Unit = {
    
    }
    
    implicit val context: Context = new Context
    val bytes = "hello world!".getBytes()
    sendImpl(bytes)
    
    
    // Case class is a value object. By default case class is Immutable
    case class Point(x: Int, y: Int)
    // the value of x and y cannot be changed
    
    // case classes are compared by value and not by reference. eg.: point1 == point2
    
    
    // objects are singleton
    object Person
    
    // traits are similar to interfaces
    trait Car {
        val color: String
        def drive(): Point
    }
    // trait methods can have default implementations
    // traits cannot be instantiated
    
    
    // Visibility
    // protected - members are accessible only from sub classes
    // private - members are accessible only from current class
    
    
    // loops
    for (a <- 1 to 10) {
        println(a)
    }
    
    // like - a for loop inside a for loop
    for (a <- 1 until 4; b <- 0 to 2) {
        println(a, b)
    }
    
    val elements = List(1,2,3)
    for (element <- elements if element % 2 == 0) {
        println(element)
    }
    
    
    // for comprehensions - to create a sequence of form: for() yield element
    val sub = for (element <- elements if element % 2 == 0) yield element
    
    
    // pattern matching
    abstract class Notification
    case class Email(sender: String, title: String, body: String) extends Notification
    case class Sms(caller: String, message: String) extends Notification
    
    def showNotification(notification: Notification) = notification match {
        case Email(sender, title, _) => s"You have received email from $sender with title $title"
        case Sms(caller, _) => s"You have received a message from $caller"
    }
    
    
    // try-catch
    try {
        "123s".toLong
    } catch {
        case nfe: NumberFormatException => println("Not a long value: Exception: " + nfe.getLocalizedMessage)
    }
    
    // Try is a container for success and failures
    val tried = Try(new FileReader("notes.md")).map(f => f.read())
    
    tried match {
        case Success(n) => println(s"Success: $n")
        case Failure(exception) => println(exception.getLocalizedMessage)
    }
    
    
    // map, flatmap in collections
    
    // val result: List[B] = List[A].map(f: A => B)
    // when a map operation is applied on collection of type A, returns a collection of type B
    
    // flatmap - flattens the hierarchy level by one each time it is applied
    // takes in a function that returns a sequence
    val listToBeFlatten = List(1, 2, 3, 4)
    val addSubUnity = (x: Int) => List(x - 1, x + 1)
    
    // returns List[List[Int]] - List(List(0, 2), List(1, 3), List(2, 4)....)
    println(listToBeFlatten.flatMap(addSubUnity))
    
    
    case class CustomMap[T, U](list: List[T]) {
        def customMap(f: T => U): List[U] = {
            val initialList = List.empty[U]
            
            @tailrec
            def customMapHelper(in: List[T], out: List[U]): List[U] = {
                in match {
                    case Nil => out
                    case head :: Nil => f(head) :: out
                    case head :: tail => customMapHelper(tail, f(head) :: out)
                }
            }
            
            customMapHelper(list, initialList) reverse
        }
        
        def customFlatMap(f: T => List[U]): List[U] = {
            val initialFlatMap = List.empty[U]
            
            @tailrec
            def customFlatMapHelper(in: List[T], out: List[U]): List[U] = {
                in match {
                    case Nil => out
                    case head :: Nil => out ::: f(head)
                    case head :: tail => customFlatMapHelper(tail, out ::: f(head))
                }
            }
            
            customFlatMapHelper(list, initialFlatMap)
        }
    }
    
    def convertFloatToInt(x: Float): Int = x.toInt
    val floatList = List(1.0f, 2.3f, 4.5f)
    println(CustomMap[Float, Int](floatList).customMap(convertFloatToInt))
    
    println(CustomMap(listToBeFlatten).customFlatMap(addSubUnity))
    
    
    
    val optString: Option[String] = Some("123")
    optString.map(str => println(str))
    
    
}