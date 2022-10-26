package fr.esiee.simplewebserver
import fr.esiee.simplewebserver.WebParameter

enum Method :
  case GET, POST, PUT, DELETE

class WebRequest(r_method : Method, r_path : String, r_parameters: Seq[WebParameter], r_headers : List[String], r_content : Option[String]) {
  val method : Method = r_method
  val path : String = r_path
  val parameters : Seq[WebParameter] = r_parameters
  val headers : List[String] = r_headers
  val content : Option[String] = r_content
}
