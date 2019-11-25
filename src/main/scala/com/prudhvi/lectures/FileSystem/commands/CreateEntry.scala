package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.{DirEntry, Directory}

abstract class CreateEntry(name: String) extends Command {
	
	override def apply(state: State): State = {
		val wd = state.wd
		if (wd.hasEntry(name))
			state.setMessage(s"File $name already exists!")
		else if (name.contains(Directory.SEPARATOR)) {
			state.setMessage(s"$name should not contain separators!")
		} else if (checkName(name)) {
			state.setMessage(s"$name should not contain illegal characters")
		} else {
			doCreateEntry(name, state)
		}
	}
	
	def doCreateEntry(name: String, state: State): State = {
		def updateStructure(currDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
			if (path.isEmpty) {
				// add newEntry in currDirectory
				currDirectory.addEntry(newEntry)
			} else {
				val oldEntry = currDirectory.findEntry(path.head).asDirectory
				// replacing old entry with new entry
				currDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
			}
		}
		
		val wd = state.wd
		
		// 1. all the directories in full path
		val allDirsInPath = wd.getAllFoldersInPath
		
		// 2. create new directory in working directory
		val newEntry: DirEntry = createEntryByType(state)
		
		// 3. update the whole directory structure starting from root (directory structure is IMMUTABLE)
		val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
		
		// 4. find new working directory instance given wd's full path, in the new working directory
		val newWd = newRoot.findDescendant(allDirsInPath)
		
		State(newRoot, newWd)
	}
	
	def createEntryByType(state: State): DirEntry
	
	def checkName(name: String): Boolean = {
		"[!&()><]".r.findAllIn(name).nonEmpty
	}
	
}
