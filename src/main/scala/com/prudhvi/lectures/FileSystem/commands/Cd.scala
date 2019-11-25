package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.{DirEntry, Directory}

import scala.annotation.tailrec

class Cd(dir: String) extends Command {
	
	override def apply(state: State): State = {
		
		// 1. find root
		val root = state.root
		val wd = state.wd
		
		// 2. find the absolute path of directory we want to go to
		val absPath =
			if (dir.startsWith(Directory.SEPARATOR)) dir
			else if (wd.isRoot) wd.path + dir
			else wd.path + Directory.SEPARATOR + dir
		
		// 3. find the directory we want to go to, given the path
		val destDir = doFindEntry(root, absPath)
		
		// 4. change the state given the new directory
		if (destDir == null || !destDir.isDirectory)
			state.setMessage(s"$dir: No such directory!")
		else
			State(root, destDir.asDirectory)
	}
	
	def doFindEntry(root: Directory, absPath: String): DirEntry = {
		@tailrec
		def findEntryHelper(currentDirectory: Directory, paths: List[String]): DirEntry = {
			if (paths.isEmpty || paths.head.isEmpty) currentDirectory
			else if (paths.tail.isEmpty) currentDirectory.findEntry(paths.head)
			else {
				val nextDir = currentDirectory.findEntry(paths.head)
				if (nextDir == null || !nextDir.isDirectory) null
				else findEntryHelper(nextDir.asDirectory, paths.tail)
			}
		}
		
		// eliminate/collapse relative tokens - ..
		// /a/b/. => ["a", "b", "."] => ["a", "b"]
		// /a/.. => ["a", ".."] => []
		// /a/b/.. => ["a", "b", ".."] => ["a"]
		
		@tailrec
		def collapsibleRelativeTokens(path: List[String], result: List[String]): List[String] = {
			if (path.isEmpty) result
			else if (".".equals(path.head)) collapsibleRelativeTokens(path.tail, result)
			else if ("src/main".equals(path.head)) {
				// not going into parent of root
				if (result.isEmpty) null
				else collapsibleRelativeTokens(path.tail, result.init)
			} else collapsibleRelativeTokens(path.tail, result :+ path.head)
		}
		
		val tokens: List[String] = absPath.substring(1).split(Directory.SEPARATOR).toList
		val newTokens = collapsibleRelativeTokens(tokens, List())
		
		if (newTokens == null) null
		else findEntryHelper(root, newTokens)
	}
}
