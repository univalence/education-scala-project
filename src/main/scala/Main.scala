import scala.io.Source

import java.io.PrintWriter
import java.net.ServerSocket

object ServerMain {

  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(8182)

    try
      while (true) {
        val client = server.accept()

        try {
          val source = Source.fromInputStream(client.getInputStream)
          for (elem <- source.getLines().takeWhile(_.trim.nonEmpty))
            println(elem)
          println("")

          val printer = new PrintWriter(client.getOutputStream)
          printer.print("HTTP/1.1 200 OK\r\n")
          printer.print("Date: Tue, 6 Sep 2022 14:01:07 +0200\r\n")
          printer.print("Content-Type: application/json\r\n")
          printer.print("\r\n")
          printer.print("""{"response": "hello"}""")
          printer.flush()
        } finally client.close()
      }
    finally server.close()
  }

}