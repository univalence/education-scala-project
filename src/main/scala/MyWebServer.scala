import fr.esiee.simplewebserver.SimpleWebService
import fr.esiee.simplewebserver.WebResponse
import fr.esiee.simplewebserver.WebRequest
import fr.esiee.simplewebserver.SimpleWebServer

object MyWebServer {
  def main(args: Array[String]): Unit = {
    val service = new MyWebService

    SimpleWebServer
      .create()
      .listenPort(8080)
      .withService(service)
      .runForever()
  }
}

class MyWebService extends SimpleWebService {
  override def get(request: WebRequest): WebResponse = {
    WebResponse.createOkWithBody("hello", "text/html")
  }
}
