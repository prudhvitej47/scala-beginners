package com.prudhvi.lectures.part2oop

object CaseClasses extends App {

    case class Person(name: String, age: Int)

    val jim = new Person("Jim", 22)

    // class parameters are fields
    println(jim.name)
    println(jim.age)

    // sensible toString
    println(jim.toString)
    // equivalent toString
    println(jim)

    // for different references equal to method returns false
    // but in case classes it returns true
    val jim2 = new Person("Jim", 22)
    println(jim == jim2)

    // have handy copy method
    val jim3 = jim.copy(age = 45)
    println(jim3.age)

    // case classes have companion objects
    val thePerson = Person
    val mary = Person("Mary", 33)

    // case objects
    // case objects do not get companion objects
    case object UnitedKingdom {
        def name: String = "The United Kingdom of Great Britain and Northern Ireland"
    }
    println(UnitedKingdom.name)
}
