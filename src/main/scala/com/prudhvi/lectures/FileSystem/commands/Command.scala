package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State

// commands have property to change the state of the world
trait Command {
	def apply(state: State): State
}

object Command {
	val MKDIR = "mkdir"
	val TOUCH = "touch"
	val LS = "ls"
	val PWD = "pwd"
	val CD = "cd"
	val RM = "rm"
	val ECHO = "echo"
	val CAT = "cat"
	
	def emptyCommand: Command = new Command {
		// return the same state
		override def apply(state: State): State = state.setMessage("")
	}
	
	def incompleteCommand(name: String): Command = new Command {
		override def apply(state: State): State =
			state.setMessage(s"$name: too few arguments found!")
	}
	
	def tooManyArgumentsForCommand(name: String): Command = new Command {
		override def apply(state: State): State = state.setMessage(s"$name: too many arguments found!")
	}
	
	def from(input: String): Command = {
		val tokens = input.trim.split(" ")
		
		if (input.trim.isEmpty || tokens.isEmpty) emptyCommand
		else tokens(0) match {
			case MKDIR =>
				if (tokens.length < 2) incompleteCommand(MKDIR)
				else if (tokens.length == 2) new Mkdir(tokens(1))
				else tooManyArgumentsForCommand(MKDIR)
			case TOUCH =>
				if (tokens.length < 2) incompleteCommand(TOUCH)
				else if (tokens.length == 2) new Touch(tokens(1))
				else tooManyArgumentsForCommand(TOUCH)
			case LS =>
				if (tokens.length > 1) {
					tooManyArgumentsForCommand(LS)
				} else new Ls
			case PWD =>
				new Pwd
			case CD =>
				if (tokens.length > 2) tooManyArgumentsForCommand(CD)
				else new Cd(tokens(1))
			case RM =>
				if (tokens.length > 2) tooManyArgumentsForCommand(RM)
				else new Rm(tokens(1))
			case ECHO =>
				if (tokens.length < 2) incompleteCommand(ECHO)
				else new Echo(tokens.tail)
			case CAT =>
				if (tokens.length < 2) incompleteCommand(CAT)
				else new Cat(tokens(1))
			case _ =>
				new UnknownCommand
		}
	}
}
