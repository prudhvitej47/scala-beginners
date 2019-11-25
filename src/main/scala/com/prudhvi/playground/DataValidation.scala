package com.prudhvi.playground

import cats.data.{Validated, ValidatedNel}
import cats.implicits._

object DataValidation extends App {
	
	// Version 0
	case class UserDTO(email: String, password: String)
	
	def isValidEmail(email: String): Boolean = false
	
	def isValidPassword(password: String): Boolean = false
	
	class UserValidationException extends Exception("User validation exception")
	
	def validateUserVersion0(user: UserDTO): UserDTO = {
		if (isValidEmail(user.email) && isValidPassword(user.password)) {
			user
		} else {
			throw new UserValidationException
		}
	}
	
	
	// Version 1
	case class Email(value: String)
	
	object Email {
		def apply(value: String): Option[Email] = Some(value).filter(isValidEmail).map(new Email(_))
	}
	
	case class Password(value: String)
	
	object Password {
		def apply(value: String): Option[Password] = Some(value).filter(isValidPassword).map(new Password(_))
	}
	
	case class User(email: Email, password: Password)
	
	object User {
		def apply(email: Email, password: Password): User = new User(email, password)
		def fromUserDTO(userDTO: UserDTO): Option[User] = for {
			email <- Email(userDTO.email)
			password <- Password(userDTO.password)
		} yield User(email, password)
	}
	
	def validateUserVersion1(userDTO: UserDTO): Option[User] = User.fromUserDTO(userDTO)
	
	
	// Version 2
	// How do we know that it is the user validation that is failed
	// Either(Either can be either Right(correct) or Left(error) case)
	val userError = "User validation error"
	def validateUserVersion2(userDTO: UserDTO): Either[String, User] = User.fromUserDTO(userDTO).toRight(userError)
	
	
	// case when we care what exactly failed ?
	val emailError = "invalid email"
	val passwordError = "invalid password"
	def validateUserVersion3(userDTO: UserDTO): Either[String, User] = (
		Email(userDTO.email).toRight(emailError),
		Password(userDTO.password).toRight(passwordError)
	) match {
		case (Right(email), Right(password)) => Right(User(email, password))
		case (Left(error), Right(_)) => Left(error)
		case (Right(_), Left(error)) => Left(error)
		case (Left(e1), Left(e2)) => Left(e1 ++ e2)
	}
	
	
	// Version 4
	// what if we have 3 more fields...then the number of possible error combination makes the code really verbose
	def validateUserVersion4(userDTO: UserDTO): Either[String, User] = for {
		email <- Email(userDTO.email).toRight(emailError)
		password <- Password(userDTO.password).toRight(passwordError)
	} yield User(email, password)
	
	validateUserVersion4(UserDTO("prudhvi", "xsa")) match {
		case Left(error) => println(error)
		case Right(user) => println(user.email)
	}
	
	
	// Version 5
	// if email and password are invalid we would only be informed about the email.
	// How can we keep the syntax concise and let the errors aggregate?
	
	// toValid - if Option is Some then return the right value or else the left value
	// mapN - maps tuple f:(A0, A1) => B
	
	def validateUserVersion5(userDTO: UserDTO): Validated[String, User] = (
		Email(userDTO.email).toValid(emailError),
		Password(userDTO.password).toValid(passwordError)
	).mapN((email, password) => User(email, password))
	
	
	// As a side note Validated also supports transformations to Option and Either
	val x = validateUserVersion5(UserDTO("prudhvi", "xyz")).toOption
	val y = validateUserVersion5(UserDTO("prudhvi", "xyz")).toEither
	
	
	// Version 6
	// Using Strings as Errors only works for Toy examples,
	// what if we expect our errors to be domain specific and we want to add checks that depend on other checks passing?
	// Let’s start by defining our domain errors:
	
	sealed trait UserError
	final case object PasswordValidationError extends UserError
	sealed trait EmailError extends UserError
	final case object InvalidEmailError extends EmailError
	final case object BlackListedUserError extends EmailError
	
	val blackListedUsers = Seq("prudhvi")
	
	// condNel(test,a:A,e:E) - If the condition is satisfied, return the given A as valid NEL, otherwise return the given E as invalid NEL.
	def validatedEvilness(email: Email): ValidatedNel[UserError, Email] = {
		Validated.condNel(!blackListedUsers.contains(email.value), email, BlackListedUserError)
	}
	
	def validateUserVersion6(userDTO: UserDTO) = (
		Email(userDTO.email).toValidNel(InvalidEmailError).andThen(validatedEvilness),
		Password(userDTO.password).toValidNel(PasswordValidationError)
	).mapN(User(_, _))
}
