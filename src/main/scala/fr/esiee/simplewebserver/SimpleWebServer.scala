package fr.esiee.simplewebserver

import java.io.{PrintWriter, StringWriter}
import java.net.ServerSocket
import java.net.Socket
import scala.io.Source
import scala.annotation.tailrec
import scala.util.Using

object SimpleWebServer {

  def create(): SimpleWebServerBuilder(port : Option[Int], service : Option[SimpleWebService])

  case class SimpleWebServerBuilder(port :Int, service : SimpleWebService) :
    def listenPort(port:Int): SimpleWebServerBuilder = copy(port=port)
    def withService(service: SimpleWebService): SimpleWebServerBuilder = copy(service=service)




    def runForever(): Unit = {

      @tailrec
      def recursiveRunForever(): Unit = {
        Using(server.accept()) { client =>
          println(">>> Get request from a client")
          val request = readGetRequestFrom(client)
          println("")
          val response = call(requete, service)
          println(">>> Sending response...")
          sendResponseFrom(client, response, ZonedDateTime.now())
        }.fold(error => println(s">>> client connection failure: ${error.getMessage}"),
          _ => ()
        )
        recursiveRunForever()
      }

      Using(ServerSocket(port).accept()) { server =>
        recursiveRunForever()
      }.get

    }
  //case class ImmutableListenPort(port: Int):
  //  def port(port: Int): ImmutableListenPort = copy(port)


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
