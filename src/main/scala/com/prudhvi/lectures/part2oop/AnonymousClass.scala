package com.prudhvi.lectures.part2oop

object AnonymousClass extends App {

    abstract class Animal {
        def eat: Unit
    }

    val funnyAnimal = new Animal {
        override def eat: Unit = println("hahahahaha")
    }

    println(funnyAnimal.getClass) // prints AnonymousClass$$anon$1

    // equivalent
    /*
    class AnonymousClass$$anon$1 extends Animal {
        override def eat: Unit = println("overriden")
    }
    val funnyAnimal = new AnonymousClass$$anon$1
    */

    class Person(name: String) {
        def sayHi: Unit = println(s"Hi, my name is $name. How can I help?")
    }

    val jim = new Person("Jim") {
        override def sayHi: Unit = println(s"Hi, my name is Jim. How can I help?")
    }
}
