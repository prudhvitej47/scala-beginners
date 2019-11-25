package com.prudhvi.lectures.FileSystem.files

import com.prudhvi.lectures.FileSystem.fileSystem.FileSystemException

class File(override val parentPath: String, override val name: String, val contents: String)
	extends DirEntry(parentPath, name) {
	
	override def asDirectory: Directory =
		throw new FileSystemException("File cannot be converted to directory!")
	
	def asFile: File = this
	
	def isDirectory: Boolean = false
	
	def isFile: Boolean = true
	
	def setContents(newContents: String): DirEntry =
		new File(parentPath, name, newContents)
	
	def appendContents(appendContents: String): DirEntry =
		setContents(contents + "\n" + appendContents)
	
	def getType: String = "File"
}

object File {
	
	def empty(parentPath: String, name: String): File =
		new File(parentPath, name, "")
}
