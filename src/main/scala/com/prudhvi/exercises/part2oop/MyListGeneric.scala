package com.prudhvi.exercises.part2oop

import java.util.NoSuchElementException

abstract class MyListGeneric[+A] {
    def head: A
    def tail: MyListGeneric[A]
    def isEmpty: Boolean
    
    // B is superclass of A
    def add[B >: A](element: B): MyListGeneric[B]
    def printElements: String
    override def toString: String = "[" + printElements + "]"
    
    // B is subclass of A
    def map[B](transformer: MyTransformer[A, B]): MyListGeneric[B]
    def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B]
    def filter[B](predicate: MyPredicate[A]): MyListGeneric[A]
    
    // concatenation
    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]
    
    // HOFS
    def foreach(f: A => Unit)
    def sort(f: (A, A) => Int): MyListGeneric[A]
    def zipWith[B, C](list: MyListGeneric[B], zip: (A, B) => C): MyListGeneric[C]
    
    // [1,2,3].fold(0)(+) = [1,3,6]
    // 1 is added to 0 = 1
    // 2 is added with 1 = 3
    // 3 is added with 3
    def fold[B](start: B)(operator: (B, A) => B): B
}

// Nothing class extends java.lang.Object(String, list, Set,...), Null
// Nothing also extends Int, Unit, Boolean and Float
// in fact Nothing is subclass of everything
// empty list
case object EmptyGeneric extends MyListGeneric[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](element: B): MyListGeneric[B] = new ConsGeneric(head, EmptyGeneric)

    def printElements: String = ""
    
    def map[B](transformer: MyTransformer[Nothing, B]): MyListGeneric[B] = EmptyGeneric
    def flatMap[B](transformer: MyTransformer[Nothing, MyListGeneric[B]]): MyListGeneric[B] = EmptyGeneric
    def filter[B](predicate: MyPredicate[Nothing]): MyListGeneric[Nothing] = EmptyGeneric
    
    def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list
    
    def foreach(f: Nothing => Unit): Unit = {}
    def sort(f: (Nothing, Nothing) => Int): MyListGeneric[Nothing] = EmptyGeneric
    def zipWith[B, C](list: MyListGeneric[B], zip: (Nothing, B) => C): MyListGeneric[C] = {
        if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
        else EmptyGeneric
    }
    def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ConsGeneric[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
    def head: A = h
    def tail: MyListGeneric[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](element: B): MyListGeneric[B] = new ConsGeneric(element, this)

    def printElements: String = {
        if (t.isEmpty) "" + h
        else h + ", " + t.printElements
    }
    
    def map[B](transformer: MyTransformer[A, B]): MyListGeneric[B] = new ConsGeneric(transformer.transform(h), t.map(transformer))
    def flatMap[B](myTransformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B] = {
        myTransformer.transform(h) ++ t.flatMap(myTransformer)
    }
    def filter[B](predicate: MyPredicate[A]): MyListGeneric[A] = {
        if (predicate.test(h)) new ConsGeneric(h, t.filter(predicate))
        else t.filter(predicate)
    }
    
    // concatenation
    def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = new ConsGeneric(h, t ++ list)
    
    // HOFs
    def foreach(f: A => Unit): Unit = {
        f(h)
        t.foreach(f)
    }
    
    // HOFs - insertion sort
    def sort(compare: (A, A) => Int): MyListGeneric[A] = {
        def insert(a: A, sortedList: MyListGeneric[A]): MyListGeneric[A] = {
            if (sortedList.isEmpty) EmptyGeneric
            else if (compare(a, sortedList.head) <= 0) new ConsGeneric(a, sortedList)
            else new ConsGeneric(sortedList.head, insert(a, sortedList.tail))
        }
        
        val sortedTail = tail.sort(compare)
        // insert head into the sorted tail
        insert(h, sortedTail)
    }
    
    def zipWith[B, C](list: MyListGeneric[B], zip: (A, B) => C): MyListGeneric[C] = {
        if (list.isEmpty) throw new RuntimeException("Lists do not have same length")
        else new ConsGeneric(zip(h, list.head), t.zipWith(list.tail, zip))
    }
    
    def fold[B](start: B)(operator: (B, A) => B): B = {
        val newStart = operator(start, h)
        t.fold(newStart)(operator)
    }
}

// every subclass of T will have a different implementation of this
trait MyPredicate[-T] {
    def test(element: T): Boolean
}


// transforms type A to type B. Every subclass of A will have a different implementation of this
trait MyTransformer[-A, B] {
    def transform(element: A): B
}

object ListTestGeneric extends App {
    val listOfIntegers: MyListGeneric[Int] = new ConsGeneric(3, new ConsGeneric(2, new ConsGeneric(1, EmptyGeneric)))
    val cloneListOfIntegers: MyListGeneric[Int] = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, EmptyGeneric)))
    val anotherListOfIntegers: MyListGeneric[Int] = new ConsGeneric(4, new ConsGeneric(5, EmptyGeneric))
    val listOfStrings: MyListGeneric[String] = new ConsGeneric("Hello", new ConsGeneric("Scala", EmptyGeneric))

    println(listOfStrings.toString)
    println(listOfIntegers.toString)

    // refer case classes and case objects
    println(listOfIntegers == cloneListOfIntegers)
    
    
    println(listOfIntegers.map(new MyTransformer[Int, Int] {
        override def transform(element: Int): Int = element * 2
    }))
    
    println(listOfIntegers.filter(new MyPredicate[Int] {
        override def test(element: Int): Boolean = element % 2 == 0
    }))
    
    println(listOfIntegers.flatMap(new MyTransformer[Int, MyListGeneric[Int]] {
        override def transform(element: Int): MyListGeneric[Int] = new ConsGeneric(element, new ConsGeneric(element + 1, EmptyGeneric))
    }))
    
    // HOFs
    //listOfIntegers.foreach(x => println(x))
    listOfIntegers.foreach(println)
    println(listOfIntegers.sort((x, y) => y - x))
    
    //println(anotherListOfIntegers.zipWith(listOfStrings, (i: Int, str: String) => i + " " + str))
    // if you don't mention type params for zipWith compiler won't know what types are B and C
    println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + " " + _))
    
    //println(cloneListOfIntegers.fold[Int](0)((x: Int, y: Int) => x + y))
    println(cloneListOfIntegers.fold[Int](0)(_ + _))
}