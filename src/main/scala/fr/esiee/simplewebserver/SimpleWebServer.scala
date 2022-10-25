package fr.esiee.simplewebserver

import fr.esiee.simplewebserver.WebResponse
import fr.esiee.simplewebserver.WebRequest
import fr.esiee.simplewebserver.SimpleWebService
import fr.esiee.simplewebserver.Method

import java.io.{PrintWriter, StringWriter}
import java.net.ServerSocket
import java.net.Socket
import scala.io.Source
import scala.annotation.tailrec
import scala.util.Using
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.*

object SimpleWebServer {

  def create(sws_port : Int , sws_service : SimpleWebService) : SimpleWebServerBuilder = SimpleWebServerBuilder(sws_port , sws_service)
    //

  case class SimpleWebServerBuilder(port : Int, service : SimpleWebService) :
    // ce qu'on veut comme paramètre :
    // port : Option[Int], service : Option[SimpleWebService]

    def listenPort(port:Int): SimpleWebServerBuilder = copy(port= port) //copy(port=Option(port))
    def withService(service: SimpleWebService): SimpleWebServerBuilder = copy(service= service) //copy(service= Option(service))
    def runForever(): Unit = {
      Using(ServerSocket(port)) { server =>
        // while(True)
        @tailrec
        def recursiveRunForever(): Unit = {
          // pour s'assurer que cleint se fermera :
          Using(server.accept()) { client =>
            println(">>> Get request from a client")
            val request = createWebRequest(client)
            // lit la requête HTTP du client (navigateur)
            // et le transforme en WebRequete
            println("")
            val response = call(request, service)
            // récupère le Webrequest et fait l'opération associé
            // retourne donc un Webresponse
            println(">>> Sending response...")
            sendResponse(client, response)
            // convertie le Webresponse en réponse HTTP du server
          }.fold(
            error => println(s">>> client connection failure: ${error.getMessage}"),
            // si error, print message ci-dessus
            // sinon ci-dessous
            _ => ()
          )
          recursiveRunForever() // s'appelle récursivement
        }
        recursiveRunForever()
      }.get
    }

  // lit la requête HTTP du client (navigateur)
  // et le transforme en WebRequete
  def createWebRequest(client: Socket): WebRequest = {
    val source = Source.fromInputStream(client.getInputStream)

    val request = source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList

    // récupère les features qu'il faut
    val method : Method = request.head.takeWhile(_ != ' ') match {
      case "GET"  => Method.GET
      case "POST"   => Method.POST
      case "PUT" => Method.PUT
      case "DELETE" => Method.DELETE
    }
    val pathStart = request.head.indexOf(" ")+1
    val pathEnd = request.head.indexOf(" HTTP")
    val path : String = request.head.substring(pathStart, pathEnd)
    val header : List[String] = request.drop(1)
    //val content : String = ??

    WebRequest(method, path, header)
  }

  // récupère le Webrequest et fait l'opération associé
  // retourne donc un Webresponse
  def call(request : WebRequest, service : SimpleWebService): WebResponse={
    val method = request.method
    method match {
      case Method.GET => service.get(request)
      case Method.POST => service.post(request)
      case Method.PUT => service.put(request)
      case Method.DELETE => service.delete(request)
    }
  }

  // convertie le Webresponse en réponse HTTP du server
  def sendResponse(client : Socket, response : WebResponse): Unit = {
    val printer = new PrintWriter(client.getOutputStream)
    val formatterdate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val fDate = formatterdate.format(LocalDate.now)
    val cTime = LocalTime.now

    printer.print(s"HTTP/1.1 ${response.statusCode} ${response.status}\r\n")
    printer.print(s"Date: ${fDate} ${cTime}\r\n")
    printer.print(response.headers)
    printer.print(s"Content-Type: ${response.contentType}\r\n")
    printer.print("\r\n")
    printer.print(response.status)
    printer.print("\r\n")
    printer.print(response.content)

    printer.flush()
  }
}
