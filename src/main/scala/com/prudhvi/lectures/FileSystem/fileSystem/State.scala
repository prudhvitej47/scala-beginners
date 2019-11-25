package com.prudhvi.lectures.FileSystem.fileSystem

import Console._

import com.prudhvi.lectures.FileSystem.files.Directory

// contains present and root directory, output from previous command
class State(val root: Directory, val wd: Directory, val output: String) {
	
	def show(): Unit = {
		if (!output.isEmpty) println(s"$RESET$YELLOW$output$RESET")
		print(State.SHELL_TOKEN)
	}
	
	def setMessage(message: String): State =
		State(root, wd, message)
}

object State {
	val SHELL_TOKEN = "$ "
	
	def apply(root: Directory, wd: Directory, output: String = ""): State =
		new State(root, wd, output)
}
