package fr.esiee.simplewebserver

import fr.esiee.simplewebserver.WebRequest
import fr.esiee.simplewebserver.WebResponse


trait SimpleWebService:
  def get(request: WebRequest): WebResponse = {
    WebResponse(r_statusCode = 405)
  }
  def post(request: WebRequest): WebResponse = {
    WebResponse(r_statusCode = 405)
  }
  def put(request: WebRequest): WebResponse = {
    WebResponse(r_statusCode = 405)
  }
  def delete(request: WebRequest): WebResponse = {
    WebResponse(r_statusCode = 405)
  }
