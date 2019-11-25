package com.prudhvi.lectures.part2oop

object OOBasics extends App {

    val person = new Person("Prudhvi", 22)
    println(person.age)
    person.greet()
    person.greet("Tej")

    val anotherPerson = new Person("Jayanthi")
    println(anotherPerson.greet())
}

class aPerson(name: String, age: Int) // constructor
// class parameters are not fields

// with this declaration you can use person.age
class Person(name: String, val age: Int) {
    //body
    def greet(name: String): Unit = {
        println(s"${this.name} says: Hi $name!")
    }

    // overloading
    def greet(): Unit = {
        println(s"Hi I am $name. My age is $age")
    }

    // multiple constructors
    // constructor calls the primary or auxiliary constructor
    def this(name: String) = this(name, 0)
    def this() = this("John Doe")
}