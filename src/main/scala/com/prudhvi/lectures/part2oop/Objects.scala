package com.prudhvi.lectures.part2oop

object Objects {

    // scala does not have class level functionality
    // scala does not know "static"

    object Person {
        // "static"/class level functionality
        val N_EYES = 2
        def can_fly: Boolean = false

        def apply(mother: Person, father: Person): Person = new Person("Bobbie")
    }

    class Person(name: String) {
        // Instance level functionality
    }

    // objects and classes with same name and same scope are called COMPANIONS


    //Scala applications - Scala object with
    // def main(args: Array[String]): Unit
    def main(args: Array[String]): Unit = {
        println(Person.N_EYES)
        println(Person.can_fly)

        // scala object is a singleton instance - when we define an object
        // we define its type as well as its instance
        val mary = Person
        val john = Person

        // mary and john point to the same instance
        println(mary == john)

        val todd = new Person("Todd")
        val jamie = new Person("Jamie")
        println(todd == jamie)

        val bobbie = Person (todd, jamie)
    }
}
