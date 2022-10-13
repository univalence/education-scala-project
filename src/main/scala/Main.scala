import java.net.ServerSocket
import java.net.Socket
import scala.io.Source
import java.io.{PrintWriter, StringWriter}
import scala.util.Using

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object WebServerMain {

  val dateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.RFC_1123_DATE_TIME

  val port = 8182

  def main(args: Array[String]): Unit =
    Using(new ServerSocket(port)) { server =>
      println(s">>> Listening on port $port...")

      while (true)
        Using(server.accept()) { client =>
          println(">>> Get request from a client")
          readGetRequestFrom(client).foreach(println)

          println("")
          println(readGetRequestFrom(client).head)

          println("")
          println(">>> Sending response...")
          sendResponseFrom(client, ZonedDateTime.now())
        }.fold(
          error => println(s">>> client connection failure: ${error.getMessage}"),
          _ => ()
        )
    }.get

  def readGetRequestFrom(client: Socket): List[String] = {
    val source = Source.fromInputStream(client.getInputStream)

    source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList
  }

  def sendResponseFrom(client: Socket, now: ZonedDateTime): Unit = {
    val printer = new PrintWriter(client.getOutputStream)

    printer.print("HTTP/1.1 200 OK\r\n")
    printer.print(s"Date: ${dateTimeFormatter.format(now)}\r\n")
    printer.print("Content-Type: text/html\r\n")
    printer.print("\r\n")
    printer.print("<b> Hello</b>")

    printer.flush()
  }
}

object WebClientMain {
  def main(args: Array[String]): Unit =
    Using(new Socket("localhost", WebServerMain.port)) { server =>
      println(s">>> connected to server ${server.getInetAddress}")

      println(">>> sending request...")
      val printer = new PrintWriter(server.getOutputStream)
      printer.print("Hello Server")

      println(">>> waiting for a response...")
      val responseStream = Source.fromInputStream(server.getInputStream)
      for (elem <- responseStream.getLines())
        println(elem)
    }.get
}