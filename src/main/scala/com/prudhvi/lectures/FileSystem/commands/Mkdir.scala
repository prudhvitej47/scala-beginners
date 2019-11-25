package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.{DirEntry, Directory}

class Mkdir(name: String) extends CreateEntry(name) {
	override def createEntryByType(state: State): DirEntry =
		Directory.empty(state.wd.path, name)
}
