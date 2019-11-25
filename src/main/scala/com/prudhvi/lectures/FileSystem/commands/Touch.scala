package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.{DirEntry, File}

class Touch(name: String) extends CreateEntry(name) {
	override def createEntryByType(state: State): DirEntry =
		File.empty(state.wd.path, name)
}
