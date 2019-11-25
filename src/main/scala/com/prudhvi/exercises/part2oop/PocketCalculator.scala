package com.prudhvi.exercises.part2oop

object PocketCalculator extends App {

    // Out of memory exception
    //val array = Array.ofDim(Int.MaxValue)

    // Stack overflow exception
    //def Infinite: Int = 1 + Infinite
    //val infinite = Infinite

    class OverflowException extends RuntimeException
    class UnderflowException extends RuntimeException
    class MatchCalculationException extends RuntimeException("Division by 0")

    object PocketCalculator {
        def add(x: Int, y: Int): Int = {
            val result = x + y

            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
            else result
        }

        def subtract(x: Int, y: Int): Int = {
            val result = x - y

            if (x > 0 && y < 0 && result < 0) throw new OverflowException
            else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def multiply(x: Int, y: Int): Int = {
            val result = x * y

            if (x > 0 && y > 0 && result < 0) throw new OverflowException
            else if (x < 0 && y < 0 && result < 0) throw new OverflowException
            else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
            else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
            else result
        }

        def divide(x: Int, y: Int): Int = {
            val result = x / y

            if (y == 0) throw new MatchCalculationException
            else result
        }
    }

    //println(PocketCalculator.add(Int.MaxValue, 10))
}
