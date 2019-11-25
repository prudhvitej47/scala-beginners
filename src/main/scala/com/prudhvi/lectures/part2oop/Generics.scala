package com.prudhvi.lectures.part2oop

object Generics extends App {

    class MyList[A] {

    }

    class MyMap[Key, Value]

    trait newList[A]

    val listOfIntegers = new MyList[Int]
    val listOfStrings = new MyList[String]

    // generic methods
    object MyList {
        def empty[A]: MyList[A] = ???
    }

    val emptyListOfIntegers = MyList.empty[Int]

    // variance problem
    class Animal
    class Cat extends Animal
    class Dog extends Animal

    // List[Cat] extends List[Animal] - COVARIANCE
    class CovariantList[+A] {
        // B is supertype of A
        // if to a list of A, I add a B (which is supertype of A) then it turns into list of B
        // A - Cat
        // B - Animal
        def add[B >: A](element: B): CovariantList[B] = ???
    }
    val animal: Animal = new Cat()
    val animalList: CovariantList[Animal] = new CovariantList[Cat]

    // adding a Dog to List[Cat] makes it a List[Animal]

    // INVARIANCE - animalList cannot have List[Dog]
    class InvariantList[A]
    //val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]  - ERROR
    val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

    // CONTRAVARIANCE
    class ContraVariantList[-A]
    val contraVariantList: ContraVariantList[Cat] = new ContraVariantList[Animal]

    // bounded types
    // class cage accepts types of A that are only the subtypes of Animal
    class Cage[A<: Animal](animal: A)
    val cage = new Cage(new Dog)

    class Car
    // val newCage = new Cage(new Car) - Wrong

    // >: Animal -> accepts type that is supertype of Animal

}
