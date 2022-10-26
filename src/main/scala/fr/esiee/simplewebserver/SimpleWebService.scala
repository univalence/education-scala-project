package fr.esiee.simplewebserver
trait SimpleWebService:

  def get(request: WebRequest): WebResponse = {
    WebResponse(405)
  }
  def post(request: WebRequest): WebResponse = {
    WebResponse(405)
  }
  def put(request: WebRequest): WebResponse = {
    WebResponse(405)
  }
  def delete(request: WebRequest): WebResponse = {
    WebResponse(405)
  }
