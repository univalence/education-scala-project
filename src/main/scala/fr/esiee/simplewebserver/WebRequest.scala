package fr.esiee.simplewebserver

enum Method :
  case GET, POST, PUT, DELETE

class WebRequest(r_method : Method, r_path : String, r_headers : List[String], r_content : List[String] = List[String]()) {
  val path : String = r_path
  val method : Method = r_method
  val headers : List[String] = r_headers
  val content : List[String] = r_content
}
