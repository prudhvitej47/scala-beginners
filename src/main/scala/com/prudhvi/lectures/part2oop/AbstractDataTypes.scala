package com.prudhvi.lectures.part2oop

object AbstractDataTypes extends App {

    // abstract classes cannot be instantiated
    abstract class Animal {
        // non abstract
        val creatureType: String = "wild"

        // abstract
        def eat: Unit
    }

    class Dog extends Animal {
        override val creatureType: String = "Canine"
        override def eat: Unit = println("dog crunch")
    }

    // traits - abstract classes in Scala
    trait Carnivore {
        // non abstract
        val prefferedMeal: String = "fresh meat"

        // abstract
        def eat(animal: Animal): Unit
    }

    trait ColdBlooded

    // extends Animal and Carnivore - injecting traits
    class Crocodile extends Animal with Carnivore with ColdBlooded {
        override val creatureType: String = "croc"

        // abstract members do not require override keyword
        def eat = println("crocodile nomnomnom")
        def eat(animal: Animal) = println(s"I am a croc and I am eating a ${animal.creatureType}")
    }

    val dog = new Dog
    val crocodile = new Crocodile
    crocodile.eat
    crocodile.eat(dog)

    // Traits vs Abstract classes
    // abstract classes and traits can have abstract as well as non abstract types
    // 1- traits do not have constructor parameters
    // 2 - multiple traits may be inherited by same class
    // 3 - traits describe behavior


}
