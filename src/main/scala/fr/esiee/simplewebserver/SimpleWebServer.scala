package fr.esiee.simplewebserver

object SimpleWebServer {

  def create()
  
  def listenPort(port : ) :val port = port
  def withService(service)
  def runForever()

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
