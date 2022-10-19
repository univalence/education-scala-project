package fr.esiee.simplewebserver
import java.net.ServerSocket
import java.net.Socket
import scala.io.Source
import java.io.{PrintWriter, StringWriter}
import scala.util.Using

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class WebResponse {

  def createOkWithBody(content : text, type_rep : xxx):
  

  def sendResponseFrom(client: Socket, now: ZonedDateTime): Unit = {
    val printer = new PrintWriter(client.getOutputStream)
    val dateTimeFormatter: DateTimeFormatter =
      DateTimeFormatter.RFC_1123_DATE_TIME

    printer.print("HTTP/1.1 200 OK\r\n")
    printer.print(s"Date: ${dateTimeFormatter.format(now)}\r\n")
    printer.print("Content-Type: text/html \r\n")
    printer.print("\r\n")
    printer.print("""{"response": "hello"}""")
    printer.print("\r\n")
    printer.flush()
  }
}
