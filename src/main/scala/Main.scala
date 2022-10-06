import java.io.InputStream
import java.net.ServerSocket
import java.net.Socket


object Server {
  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(80)
    System.out.println("Démarrage du serveur sur 127.0.0.1:80.\r\nAttente d’une connexion...")
    val client = server.accept
    System.out.println("Un client s’est connecté.")
    val in: InputStream = client.getInputStream
    val out: OutputStream = client.getOutputStream

    System.out.println("Démarrage du serveur sur 127.0.0.1:80.\r\nAttente d’une connexion...")
  }
}

class Server {}

import java.io.InputStream
import java.io.OutputStream
import java.net.Socket


