package com.prudhvi.lectures.FileSystem.files

import com.prudhvi.lectures.FileSystem.fileSystem.FileSystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
	extends DirEntry(parentPath, name) {
	
	def hasEntry(name: String): Boolean = {
		findEntry(name) != null
	}
	
	def getAllFoldersInPath: List[String] = {
		// /a/b/c/d => List("a", "b", "c", "d")
		path.substring(1).split(Directory.SEPARATOR).toList.filter(x => !x.isEmpty)
	}
	
	// will return a folder if the path can be found starting from this directory
	def findDescendant(givenPath: List[String]): Directory = {
		if (givenPath.isEmpty) this
		else findEntry(givenPath.head).asDirectory.findDescendant(givenPath.tail)
	}
	
	def findDescendant(relativePath: String): Directory = {
		if (relativePath.isEmpty) this
		else findDescendant(relativePath.split(Directory.SEPARATOR).toList)
	}
	
	def removeEntry(entryName: String): Directory = {
		// this will fail the code as returning the same instance
		if (!hasEntry(entryName)) this
		else new Directory(parentPath, name, contents.filter(x => !x.name.equals(entryName)))
	}
	
	// returns a revised directory structure for this directory after adding
	def addEntry(newEntry: DirEntry): Directory =
		new Directory(parentPath, name, contents :+ newEntry)
	
	// returns a file or folder if it is present in the contents of the directory
	def findEntry(entryName: String): DirEntry = {
		@tailrec
		def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
			if (contentList.isEmpty) null
			else if (contentList.head.name.equals(name)) contentList.head
			else findEntryHelper(name, contentList.tail)
		}
		
		findEntryHelper(entryName, contents)
	}
	
	def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
		new Directory(parentPath, name, contents.filter(entry => !entry.name.equals(entryName)) :+ newEntry)
	
	def isRoot: Boolean = parentPath.isEmpty
	
	def asDirectory: Directory = this
	
	def asFile: File =
		throw new FileSystemException("Directory cannot be converted to file!")
	
	def isDirectory: Boolean = true
	
	def isFile: Boolean = false
	
	def getType: String = "Directory"
}

object Directory {
	val SEPARATOR = "/"
	val ROOT_PATH = "/"
	
	def ROOT: Directory = Directory.empty("", "")
	
	def empty(parentPath: String, name: String): Directory =
		new Directory(parentPath, name, List())
	
	
}