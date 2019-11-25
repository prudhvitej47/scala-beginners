package com.prudhvi.exercises.part3fp

object HOFsCurries extends App {
	
	def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = {
		 x => y => f(x, y)
	}
	
	def fromCurry(f: (Int => Int => Int)): (Int, Int) => Int = {
		(x, y) => f(x)(y)
	}
	
	/*def compose(f: Int => Int, g: Int => Int):  Int => Int = {
		x => f(g(x))
	}
	
	def andThen(f: Int => Int, g: Int => Int):  Int => Int = {
		x => g(f(x))
	}*/
	
	def compose[A,B,T](f: A => B, g: T => A):  T => B = {
		x => f(g(x))
	}
	
	def andThen[A,B,C](f: A => B, g: B => C):  A => C = {
		x => g(f(x))
	}
	
	def supperAdder2: (Int => Int => Int) = toCurry(_ + _)
	def add4 = supperAdder2(4)
	println(add4(3))
	
	val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
	val simpleAdder = fromCurry(superAdder)
	println(simpleAdder(2,8))
	
	val add2 = (x: Int) => x + 2
	val times3 = (x: Int) => x * 3
	val composed = compose(add2, times3)
	println(composed(4))
	
	val ordered = andThen(add2, times3)
	println(ordered(4))
	
}
