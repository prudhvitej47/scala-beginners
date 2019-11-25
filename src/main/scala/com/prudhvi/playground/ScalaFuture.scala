package com.prudhvi.playground

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ScalaFuture {
	
	Future{"Hi"}.map(s => println(s + " world!"))
	
	def job(n: Int): Future[Int] = Future {
		Thread.sleep(1000)
		println(n)
		n + 1
	}
	
	val f: Future[List[Int]] = for {
		f1 <- job(1)
		f2 <- job(2)
		f3 <- job(3)
		f4 <- job(4)
	} yield List(f1, f2, f3, f4)
	f.map(z => println(s"Done. ${z.size} jobs run"))
	Thread.sleep(5000)
	
}
