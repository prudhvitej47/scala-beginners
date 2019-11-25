package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State

class UnknownCommand extends Command {
	
	override def apply(state: State): State =
		state.setMessage("Command not found!")
}
