import fr.esiee.simplewebserver.SimpleWebServer
import fr.esiee.mywebserver.MyWebServices.MyWebService

object MyWebServer {
  def main(args: Array[String]): Unit = {
    val service = new MyWebService

    SimpleWebServer
      .create(50000, service)
  }
}
