package fr.esiee.mywebserver
import com.google.gson.Gson
import fr.esiee.database.{Database, User}
import fr.esiee.simplewebserver.{SimpleWebService, WebRequest, WebResponse, WebParameter}

object MyWebServices {
  class MyWebService extends SimpleWebService {
    // utilisateur qui va réécrire les fonctions comme ils le souhaitent
    // ex ci-dessous, néanmoins fonction createOkwithBody n'est pas encore def*

    val database: Database = new Database

    def retrieveUsers(route: String, parameters: Seq[WebParameter]): Seq[User] =
      route match {
        case r if r.count(_ == '/') == 3 =>
          database.findFrom(r.split('/')(3)).toSeq
        case r if r.count(_ == '?') == 1 =>
          database.findFromParameters(parameters)
        case _ =>
          database.getUsers
      }

    def deleteUser(route: String): Seq[Unit] = {
      database.delete(route.split('/')(3)).toSeq
    }

    //def addUser(route: String, content : Seq[User]): Seq[Unit] = {
    //  database.update()
    //}


    override def get(request: WebRequest): WebResponse = {
      WebResponse(
        r_statusCode = 200,
        r_contentType = "text/html",
        r_content =  new Gson().toJson(retrieveUsers(request.path, request.parameters))
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
        r_content = new Gson().toJson(deleteUser(request.path))
      )
    }

  }
}
