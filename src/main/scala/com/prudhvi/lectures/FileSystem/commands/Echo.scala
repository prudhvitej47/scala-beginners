package com.prudhvi.lectures.FileSystem.commands

import com.prudhvi.lectures.FileSystem.fileSystem.State
import com.prudhvi.lectures.FileSystem.files.{Directory, File}

import scala.annotation.tailrec

class Echo(args: Array[String]) extends Command {
	
	/* if no args then return same state
		   else if one arg then print to console
		   else if multiple args:
				operator = next to last argument
				if >
					echo to a file (may create a new file if not present)
				if >>
					append to a file
				else
					just echo everything to console
	*/
	
	override def apply(state: State): State = {
		
		if (args.isEmpty) state
		else if (args.length == 1) state.setMessage(args(0))
		else {
			val operator = args(args.length - 2)
			val filename = args(args.length - 1)
			val contents = createContent(args, args.length - 2)
			
			if (">>".equals(operator))
				doEcho(state, contents, filename, append = true)
			else if (">".equals(operator))
				doEcho(state, contents, filename, append = false)
			else
				// print everything
				state.setMessage(createContent(args, args.length))
		}
	}
	
	/*
		if path is empty then fail (currentDirectory)
		else if no more things to explore = path.tail.isEMpty
			find file to create/add content to
			if file not found create file
			else if the entry is actually a directory then fail
			else
				replace or append content to file
				replace the entry with the filename with the NEW file
		else
			find next directory to navigate
			call gRAE recursively
	 */
	def getRootAfterEcho(currentDirectory: Directory, path: List[String], contents: String, append: Boolean): Directory = {
		
		if (path.isEmpty) currentDirectory
		else if (path.tail.isEmpty) {
			val dirEntry = currentDirectory.findEntry(path.head)
			
			if (dirEntry == null)
				currentDirectory.addEntry(new File(currentDirectory.path, path.head, contents))
			else if (dirEntry.isDirectory) currentDirectory
			else {
				if (append)
					currentDirectory.replaceEntry(path.head, dirEntry.asFile.appendContents(contents))
				else currentDirectory.replaceEntry(path.head, dirEntry.asFile.setContents(contents))
			}
		} else {
			val nextDirectory = currentDirectory.findEntry(path.head).asDirectory
			val newNextDirectory = getRootAfterEcho(nextDirectory, path.tail, contents, append)
			
			if (newNextDirectory == nextDirectory) currentDirectory
			else currentDirectory.replaceEntry(path.head, newNextDirectory)
		}
	}
	
	// doesn't support creating in absolute/relative paths
	def doEcho(state: State, contents: String, filename: String, append: Boolean): State = {
		if (filename.contains(Directory.SEPARATOR))
			state.setMessage("echo: filename must not contain separators!")
		else {
			val newRoot: Directory = getRootAfterEcho(state.root, state.wd.getAllFoldersInPath :+ filename, contents, append)
			if (newRoot == state.root)
				state.setMessage(filename + " : no such file!")
			else
				State(newRoot, newRoot.findDescendant(state.wd.getAllFoldersInPath))
		}
	}
	
	// topIndex is non inclusive
	def createContent(args: Array[String], topIndex: Int): String = {
		@tailrec
		def createContentHelper(currentIndex: Int, accumulator: String): String = {
			if (currentIndex >= topIndex) accumulator
			else createContentHelper(currentIndex + 1, accumulator + args(currentIndex) + " ")
		}
		
		createContentHelper(0, "")
	}
}
