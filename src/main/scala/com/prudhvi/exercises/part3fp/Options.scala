package com.prudhvi.exercises.part3fp

import scala.util.Random

object Options extends App {

    val config: Map[String, String] = Map(
        "host" -> "176.67.23.11",
        "port" -> "80"
    )

    class Connection {
        def connect = "Connected"  // connect to some server
    }

    object Connection {
        val random = new Random(System.nanoTime())

        def apply(host: String, port: String): Option[Connection] = {
            if (random.nextBoolean()) Some(new Connection)
            else None
        }
    }

    val host = config.get("host")
    val port = config.get("port")
    val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
    val connectionStatus = connection.map(c => c.connect)
    println(connectionStatus)
    connectionStatus.foreach(println)

    // or

    config.get("host")
        .flatMap(host => config.get("port")
            .flatMap(port => Connection(host, port)))
            .map(c => c.connect)
        .foreach(println)

    // for-comprehension
    val forConnectionStatus = for {
        host <- config.get("host")
        port <- config.get("port")
        conn <- Connection(host, port)
    } yield conn.connect
}
