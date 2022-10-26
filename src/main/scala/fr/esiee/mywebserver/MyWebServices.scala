package fr.esiee.mywebserver

import fr.esiee.database.Database
import fr.esiee.simplewebserver.{SimpleWebService, WebRequest, WebResponse, WebParameter}

object MyWebServices {
  class MyWebService extends SimpleWebService {
    // utilisateur qui va réécrire les fonctions comme ils le souhaitent
    // ex ci-dessous, néanmoins fonction createOkwithBody n'est pas encore def*

    val database: Database[Int] = new Database[Int]

    def retrieveUsers(path: String, parameters: Seq[WebParameter]): Seq[V] =
      path match {
        case r if r.count(_ == '/') == 3 =>
          Database.findFrom(r.split('/')(3)).toSeq
        case r if r.count(_ == '?') == 1 =>
          Database.findFromParameters(parameters)
        case _ =>
          Database.getUsers
      }

    override def get(request: WebRequest): WebResponse = {
      WebResponse(r_statusCode = 200, r_contentType = "text/html", r_content = "<b> Hello world! <b/>")
    }

    override def post(request: WebRequest): WebResponse = {
      WebResponse(r_statusCode = 200, r_contentType = "text/html", r_content = "<b> Hello world! <b/>")
    }

    override def put(request: WebRequest): WebResponse = {
      WebResponse(r_statusCode = 200, r_contentType = "text/html", r_content = "<b> Hello world! <b/>")
    }

    override def delete(request: WebRequest): WebResponse = {
      WebResponse(r_statusCode = 200, r_contentType = "text/html", r_content = "<b> Hello world! <b/>")
    }

  }
}
