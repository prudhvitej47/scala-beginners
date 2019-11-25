package com.prudhvi.exercises.part3fp

object TuplesAndMaps extends App {
	
	// what happens if keys are "JIM" and "jim"
	val anotherMap = Map("JIM" -> 12, "jim" -> 25)
	println(anotherMap)
	
	// println(anotherMap.map((pair: (String, Int)) => (pair._1.toLowerCase(), pair._2)))
	// will result in wrong result
	println(anotherMap.map{
		pair: (String, Int) => (pair._1.toLowerCase(), pair._2)
	})
	
	/*
		Oversimplified social network based on Maps
		Person = String
		- add a person to network
		- remove
		- friend (mutual)
		- unfriend
		
	 */
	
	def add(person: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
		network + (person -> Set())
	}
	
	// make A and B friends
	def friend(personA: String, personB: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
		val friendListA = network(personA)
		val friendListB = network(personB)
		
		network + (personA -> (friendListA + personB)) + (personB -> (friendListB + personA))
	}
	
	def unfriend(personA: String, personB: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
		val friendListA = network(personA)
		val friendListB = network(personB)
		
		network + (personA -> (friendListA - personB)) + (personB -> (friendListB - personA))
	}
	
	def remove(person: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
		def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
			if (friends.isEmpty) networkAcc
			else removeAux(friends.tail, unfriend(person, friends.head, networkAcc))
		}
		
		removeAux(network(person), network) - person
	}
	
	val empty: Map[String, Set[String]] = Map()
	var network = add("John", add("Mary", add("Bob", empty)))
	network = friend("Bob", "Mary", network)
	network = friend("Bob", "John", network)
	println(network)
	
	println(unfriend("Mary", "John", network))
	println(unfriend("Mary", "Bob", network))
	println(remove("Bob", network))
	
	def nFriends(person: String, network: Map[String, Set[String]]): Int = {
		if (!network.contains(person)) 0
		else network(person).size
	}
	
	println(nFriends("Bob", network))
	
	def mostFriends(network: Map[String, Set[String]]): String = {
		network.maxBy(pair => pair._2.size)._1
	}
	
	println(mostFriends(network))
	
	def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
		/*network.filter{
			pair => network(pair._1).size == 0
		}.size*/
		
		/*network.count{
			pair => network(pair._1).size == 0
		}*/
		
		network.filterKeys(k => network(k).isEmpty).size
	}
	
	println(nPeopleWithNoFriends(remove("Bob", network)))
	
	// mutual connection between set of friends
	def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
		def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
			if (discoveredPeople.isEmpty) false
			else {
				val person = discoveredPeople.head
				if (person == target) true
				else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
				else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
			}
		}
		
		bfs(b, Set(), network(a) + a)
	}
	
	println(socialConnection(network, "Mary", "Bob"))
	println(socialConnection(network, "Mary", "Rob"))
}
