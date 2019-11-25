package com.prudhvi.lectures.part3fp

object TuplesAndMaps extends App {

    // tuples - finite ordered "lists"
    val aTuple1 = new Tuple2(2, "hello, scala")
    val aTuple2 = Tuple2(2, "hello, scala")
    val aTuple = (2, "hello, scala")

    // tuples can group at most 22 elements of different types
    // as they are used in conjunction with Function0 - Function22

    println(aTuple._1)  // accessing element at index 1
    println(aTuple.copy(_2 = "goodbye java"))
    println(aTuple.swap)  // ("hello, scala", 2)


    // Maps = keys -> values
    val aMap: Map[String, Int] = Map()
    val phoneBook = Map(("Jim", 555), "Daniel" -> 231).withDefaultValue(-1)
    // a -> b is syntactic sugar for (a, b)
    println(phoneBook)


    // map ops
    println(phoneBook.contains("Jim"))
    println(phoneBook("Jim"))
    println(phoneBook("Mary"))   // returns -1

    // add a pairing
    val newPairing = "Mary" -> 123
    val newPhonebook = phoneBook + newPairing

    // functions on maps
    // map, flatMap, filter
    println(phoneBook.map{ pair: (String, Int) =>
        (pair._1.toLowerCase(), pair._2)
    })

    println(phoneBook.map{ pair: (String, Int) =>
        pair._1.toLowerCase() -> pair._2
    })

    println(phoneBook.map{ case (key: String, value: Int) =>
        (key.toLowerCase(), value + 1)
    })

    //println(phoneBook.filterKeys((x: String) => x.startsWith("J")))
    
    // conversion to other collections
    println(phoneBook.toList)
    println(List(("Daniel",231)).toMap)
    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0)))
}
