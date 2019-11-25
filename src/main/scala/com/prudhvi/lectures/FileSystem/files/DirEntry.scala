package com.prudhvi.lectures.FileSystem.files

abstract class DirEntry(val parentPath: String, val name: String) {
	
	def path: String = {
		if (Directory.ROOT_PATH.equals(parentPath))
			parentPath + name
		else parentPath + Directory.SEPARATOR + name
	}
	
	// will return itself for directory
	def asDirectory: Directory
	
	def asFile: File
	
	def isDirectory: Boolean
	
	def isFile: Boolean
	
	def getType: String
}
