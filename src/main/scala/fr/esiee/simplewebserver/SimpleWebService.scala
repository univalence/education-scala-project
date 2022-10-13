package fr.esiee.simplewebserver

trait SimpleWebService:
  def get(request: WebRequest): WebResponse
  def post(request: WebRequest): WebResponse
  def put(request: WebRequest): WebResponse
  def delete(request: WebRequest): WebResponse
