package com.prudhvi.lectures.part3fp

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

	// create a success and failure
	val aSuccess = Success(3)
	val aFailure = Failure(new RuntimeException("Super failure"))

	println(aSuccess)
	println(aFailure)

	def unsafeMethod() = throw new RuntimeException("No string for you buster")

	// Try objects via the apply method
	val potentialFailure = Try(unsafeMethod())
	println(potentialFailure)

	// syntax sugar
	val anotherPotentialFailure = Try {
		// code that might throw error
	}
	
	// utilities
	println(potentialFailure.isFailure)
	
	// orElse
	def backUpMethod() = "A valid result"
	val fallbackTry = Try(unsafeMethod()).orElse(Try(backUpMethod()))
	println(fallbackTry)
	
	def betterUnsafeMethod: Try[String] = Failure(new RuntimeException)
	def betterBackupMethod: Try[String] = Success("A Huge Success")
	val betterFallback = betterUnsafeMethod.orElse(betterBackupMethod)
	println(betterFallback)
	
	
	// map, flatMap, filter
	println(aSuccess.map(_ * 2))
	println(aSuccess.flatMap(x => Success(x * 10)))
	println(aSuccess.filter(_ > 10))   // gives a Failure if no such element exists
}
