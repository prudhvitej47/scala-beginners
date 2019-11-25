package com.prudhvi.lectures.FileSystem.fileSystem

import java.util.Scanner

import com.prudhvi.lectures.FileSystem.commands.Command
import com.prudhvi.lectures.FileSystem.files.Directory

object FileSystem extends App {
	
	val root = Directory.ROOT
	
	
	var state = State(root, root)
	val scanner = new Scanner(System.in)
	
	while(true) {
		state.show()
		val input = scanner.nextLine()
		
		state = Command.from(input).apply(state)
	}
	
	// [1,2,3,4].foldLeft(0)((x, y) => x + y)
	// 0 (op) 1 => 1
	// 1 (op) 2 => 3
	// .... 6 (op) 4 => 10
	/*io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
		currentState.show()
		Command.from(newLine).apply(currentState)
	})*/
}
