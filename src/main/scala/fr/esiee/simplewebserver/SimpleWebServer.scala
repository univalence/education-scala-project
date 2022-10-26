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

  def create(sws_port : Int , sws_service : SimpleWebService) : SimpleWebServerBuilder = SimpleWebServerBuilder(port=None , service=None)
    //

  case class SimpleWebServerBuilder(port : Option[Int], service : Option[SimpleWebService]) :
    // ce qu'on veut comme paramètre :
    // port : Option[Int], service : Option[SimpleWebService]

    def listenPort(port:Int): SimpleWebServerBuilder = copy(port=Option(port)) //copy(port=Option(port))
    def withService(service: SimpleWebService): SimpleWebServerBuilder = copy(service= Option(service)) //copy(service= Option(service))
    def runForever(): Unit = {
      Using(ServerSocket(port.get)) { server =>
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
            val response = call(request, service.get)
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
  def parseParameter(parameter: String): WebParameter = {
    val param = parameter.split('=') // récupération des clés et leur valeur
    WebParameter(param.head, param.last) // format clé-valeur
  }

  def getParameters(parameters: Option[String]): Seq[WebParameter] = {
    parameters
      .map(_.split('&').map(parseParameter).toSeq) // quand on a plusieurs paramètres
      .getOrElse(Seq.empty[WebParameter]) // sinon
  }


  // lit la requête HTTP du client (navigateur)
  // et le transforme en WebRequete
  def createWebRequest(client: Socket): WebRequest = {
    val source = Source.fromInputStream(client.getInputStream)

    val request_HTTP = source
      .getLines()
      .takeWhile(_.trim.nonEmpty)
      .toList

    val request = request_HTTP.head.split(' ') // pour récuperer la request
    val apiRoute = request(1).split('?') // split sur le "?" pour savoir si c'est une recherche

    // récupère les features qu'il faut
    val method: Method = request(0) match {
      case "GET" => Method.GET
      case "POST" => Method.POST
      case "PUT" => Method.PUT
      case "DELETE" => Method.DELETE
    }

    WebRequest(
      r_method = method, // type de la requête (GET, POST, PUT, DELETE)
      r_path = request(1), // route (user)
      r_parameters = getParameters(if (apiRoute.length > 1) apiRoute.lastOption else None), // obtention des paramètres de la requête
      r_headers = request_HTTP.drop(1),
      r_content = None // pour POST, json comportant ce que nous voulons POST
    )

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
    printer.print(s"\n${response.content}")

    printer.flush()
  }
}
