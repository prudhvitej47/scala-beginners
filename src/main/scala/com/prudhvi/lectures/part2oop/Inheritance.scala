package com.prudhvi.lectures.part2oop

object Inheritance extends App {

    class Animal {
        val creatureType = "wild"

        def eatAgain: String = eat
        // allows usage in subclass only
        protected def eat = "nomnom"
    }

    class Cat extends Animal

    class Dog(override val creatureType: String) extends Animal {
        //override val creatureType: String = "domestic"

        def crunch: Unit = {
            println(eat)
            println("crunchcrunch")
        }
    }

    val cat = new Cat
    //println(cat.eat)
    println(cat.eatAgain)
    println(cat.creatureType)

    val dog = new Dog("K9")
    dog.crunch
    println(dog.creatureType)

    class Person(name: String, age: Int) {
        // auxillary constructor calls main constructor
        def this(name: String) = this(name, 0)
    }

    // constructor of parent class is called first so this is not allowed
    //class Adult(name: String, idCard: String) extends Person
    class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
    class Infant(name: String, age: Int, idCard: String) extends Person(name)

    // overriding
    class Lion extends Animal {
        override def eat: String = "crunch, crunch"
    }
    val lion = new Lion
    println(lion.eat)



    sealed class newAnimal {
        val creatureType = "wild"
        def eat = println("nomnom")
    }

    class Rabbit(override val creatureType: String) extends newAnimal {
        override def eat: Unit = println("rabbit crunch")
    }

    // type substitution: polymorphism
    val unknownAnimal: newAnimal = new Rabbit("domestic")
    unknownAnimal.eat

    // on using final on creatureType you cannot use override
    // on using final on newAnimal you cannot use extends
    // sealing the class - can extend in this file but can extend in another file
}
