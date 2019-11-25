package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State

class Pwd extends Command {
	
	override def apply(state: State): State =
		state.setMessage(state.wd.path)
}
