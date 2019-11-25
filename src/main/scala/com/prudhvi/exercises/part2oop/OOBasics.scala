package com.prudhvi.exercises.part2oop

object OOBasics extends App {
    val author = new Writer("Prudhvi", "Tej", 1997)
    val imposter = new Writer("Prudhvi", "Tej", 1997)
    val novel = new Novel("Rains of Castemere", 2020, author)

    println(novel.authorAge)
    println(novel.isWrittenBy(author))
    println(novel.isWrittenBy(imposter))
    println(novel.copy(2022).authorAge)

    val counter = new Counter(0)
    println(counter.inc().count)
    println(counter.inc().inc(5).count)
}

class Novel(name: String, year: Int, author: Writer) {
    // age of author at the year of release
    def authorAge: Int = year - author.year

    def isWrittenBy(author: Writer) = author == this.author

    def copy(newYearOfRelease: Int): Novel = new Novel(name, newYearOfRelease, author)
}

class Writer(firstName: String, surname: String, val year: Int) {
    def fullName: String = firstName + " " + surname
}


class Counter(val count: Int) {
    def inc(): Counter = new Counter(count + 1)

    def inc(n: Int): Counter = {
        if (n <= 0) this
        else inc.inc(n - 1)
    }

    def dec = new Counter(count - 1)

    def dec(n: Int): Counter = {
        if (n <= 0) this
        else dec.dec(n - 1)
    }

    /*def currentCount: Int = n

    def increment() = n += 1

    def increment(incrementBy: Int) = n += incrementBy

    def decrement() = n -= 1

    def decrement(decrementBy: Int) = n -= decrementBy*/
}