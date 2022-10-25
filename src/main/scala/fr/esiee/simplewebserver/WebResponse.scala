package fr.esiee.simplewebserver

//enum Accepted_type :
//  case plain, html, json

class WebResponse(r_statusCode : Int, r_headers : String = "", r_contentType : String = "", r_content : String = "") {
  val statusCode : Int = r_statusCode
  val status : String =
    statusCode match {
      case 200 => "OK"
      case 404 => "Not Found"
      case 405 => "Method Not Allowed"
      case 500 => "Internal Server Error"
      case _ => "Status Code not Defined"
    }
  val headers : String = r_headers
  val contentType : String = r_contentType //match {
  //  case plain => "plain/text"
  //  case html => "text/html"
  //  case json => "application/json"
  //}
  val content : String= r_content
}