package fr.esiee.simplewebserver

import java.net.Socket
import scala.io.Source

class WebRequest {


  def readGetRequestFrom(client: Socket): List[String] = {
    val source = Source.fromInputStream(client.getInputStream)
    source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList
  }
  
  
}
