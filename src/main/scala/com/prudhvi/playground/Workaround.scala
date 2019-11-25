package com.prudhvi.playground

object Workaround extends App {
	
	/**
	 * Although under normal circumstances the objects in Scala are indeed singletons, this can no longer be the case
	 * when reflection comes into play. From the reflection perspective, an object is nothing more than any other class
	 * with a private constructor.
	*/

	object Test
	
	def test(t: Test.type): Boolean = t match {
		case Test => true
		case _ => false
	}
	
	val c = Test.getClass.getDeclaredConstructor()
	
	val t1 = c.newInstance()
	val t2 = c.newInstance()
	
	println(t1 == Test)
	println(t2 == Test)
	println(t1 == t2)
	
	val t3 = c.newInstance()
	val t4 = c.newInstance()
	
	println(t3 == Test)
	println(t4 == Test)
	println(t3 == t4)
	
	println(test(t1))
	println(test(t2))
	println(test(t3))
	println(test(t4))
	println(test(Test))
}
