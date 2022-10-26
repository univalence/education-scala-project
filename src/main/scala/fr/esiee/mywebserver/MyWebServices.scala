package fr.esiee.mywebserver

import fr.esiee.database.Database
import fr.esiee.simplewebserver.{SimpleWebService, WebRequest, WebResponse, WebParameter}

object MyWebServices {
  class MyWebService extends SimpleWebService {
    // utilisateur qui va réécrire les fonctions comme ils le souhaitent
    // ex ci-dessous, néanmoins fonction createOkwithBody n'est pas encore def*

    val database: Database[Int] = new Database[Int]


    override def get(request: WebRequest): WebResponse = {
      WebResponse(
        r_statusCode = 200,
        r_contentType = "text/html",
        r_content = "<b> Hello world! <b/>"
      )
    }

    override def post(request: WebRequest): WebResponse = {
      WebResponse(
        r_statusCode = 200,
        r_contentType = "text/html",
        r_content = "<b> Hello world! <b/>"
      )
    }

    override def put(request: WebRequest): WebResponse = {
      WebResponse(
        r_statusCode = 200,
        r_contentType = "text/html",
        r_content = "<b> Hello world! <b/>"
      )
    }

    override def delete(request: WebRequest): WebResponse = {
      WebResponse(
        r_statusCode = 200,
        r_contentType = "text/html",
        r_content = "<b> Hello world! <b/>"
      )
    }

  }
}
