package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.DirEntry

class Ls extends Command {
	
	override def apply(state: State): State = {
		val contents = state.wd.contents
		val niceOutput = createNiceOutput(contents)
		state.setMessage(niceOutput)
	}
	
	def createNiceOutput(contents: List[DirEntry]): String = {
		if (contents.isEmpty) ""
		else {
			val entry = contents.head
			val newLine = if (contents.tail.isEmpty) "" else "\n"
			entry.name + "[" + entry.getType + "]" + newLine + createNiceOutput(contents.tail)
		}
	}
}
