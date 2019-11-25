package com.prudhvi.exercises.part3fp

import scala.util.{Random, Try}

object HandlingFailures extends App {
	
	val hostanme = "localhost"
	val port = "8080"
	
	def renderHTML(page: String) = println(page)
	
	class Connection {
		def get(url: String): String = {
			val random = new Random()
			if (random.nextBoolean()) {
				"<html>...</html>"
			} else {
				throw new RuntimeException("Connection flaky yo!!")
			}
		}
		
		def getSafe(url: String): Try[String] = Try(get(url))
	}
	
	object HttpService {
		val random = new Random(System.nanoTime())
		
		def getConnection(host: String, port: String): Connection = {
			if (random.nextBoolean()) new Connection
			else throw new RuntimeException("Your connection got mugged player!!")
		}
		
		def getSafeConnection(hostName: String, port: String): Try[Connection] = Try(getConnection(hostName, port))
	}
	
	val possibleConnection = HttpService.getSafeConnection(hostanme, port)
	val possibleHtml = possibleConnection.flatMap(connect => connect.getSafe("/home"))
	possibleHtml.foreach(renderHTML)
	
	
	HttpService.getSafeConnection(hostanme, port)
    	.flatMap(connection => connection.getSafe("/home"))
    	.foreach(renderHTML)
}
