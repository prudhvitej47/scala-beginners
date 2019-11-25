package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.Directory

class Rm(name: String) extends Command {
	
	override def apply(state: State): State = {
		// 1. get working dir
		val wd = state.wd
		
		// 2. get absolute path
		val absPath =
			if (name.startsWith(Directory.SEPARATOR)) name
			else if (wd.isRoot) wd.path + name
			else wd.path + Directory.SEPARATOR + name
		
		// 3. do some checks
		if (Directory.ROOT_PATH.equals(absPath))
			state.setMessage("Nuclear war not supported yet!")
		else
			doRemove(state, absPath)
	}
	
	def doRemove(state: State, absPath: String): State = {
		def rmHelper(currentDir: Directory, paths: List[String]): Directory = {
			if (paths.isEmpty) currentDir
			else if (paths.tail.isEmpty) currentDir.removeEntry(paths.head)
			else {
				val nextDirectory = currentDir.findEntry(paths.head)
				// sending same instance resulting in failure
				if (!nextDirectory.isDirectory) currentDir
				else {
					val newNextDirectory = rmHelper(nextDirectory.asDirectory, paths.tail)
					// sending same instance resulting in failure
					if (newNextDirectory == nextDirectory) currentDir
					else currentDir.replaceEntry(paths.head, newNextDirectory)
				}
			}
		}
		
		// 4. find the entry to remove
		// 5. update structure
		
		val tokens = absPath.substring(1).split(Directory.SEPARATOR).toList
		val newRoot: Directory = rmHelper(state.root, tokens)
		
		if (newRoot == state.root)
			state.setMessage(absPath + ": No such file or directory!")
		else
			State(newRoot, newRoot.findDescendant(state.wd.path.substring(1)))
	}
}
