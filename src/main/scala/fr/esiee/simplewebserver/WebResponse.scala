package fr.esiee.simplewebserver

class WebResponse(r_statusCode : Int, r_headers : String = "", r_contentType : String = "", r_content : String = "") {
  val statusCode : Int = r_statusCode
  val status : String =
    statusCode match {
      case 200 => "OK"
      case 404 => "Not Found"
      case 405 => "Method Not Allowed"
      case 500 => "Internal Server Error"
    }
  val headers : String = r_headers
  val contentType : String = r_contentType
  val content : String = r_content

  //def createOkWithBody(message : String, message_type : String) = {
  //  statusCode = 200
  //  status = "OK"
  //  content = message
  //  contentType = message_type
  //}
}