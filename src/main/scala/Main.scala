import scala.io.Source

import java.io.PrintWriter
import java.net.ServerSocket

//imports ajoutés par Méline
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time._

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
          //val date = LocalDate.parse("01/01/2020", DateTimeFormatter.ofPattern("MM/dd/yyyy"))
          //date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
          //print(date.toString)

          val formatterdate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
          val fDate = formatterdate.format(LocalDate.now)
          val cTime = LocalTime.now

          printer.print("HTTP/1.1 200 OK\r\n")
          printer.print("Date: Tue, 6 Sep 2022 14:01:07 +0200\r\n")
          printer.print("Content-Type: application/json\r\n")
          printer.print("\r\n")
          //printer.print("""{"response": "hello"}""")
          printer.print("HTTP/1.1 200 OK\r\n{'response': 'hello'}\r\n")
          printer.print(s"Nous sommes aujourd'hui le :  ${fDate}")
          printer.print("\r\n")
          printer.print(s"Il est actuellement : ${cTime}")
          printer.print("\r\n")

          printer.flush()
        } finally client.close()
      }
    finally server.close()
  }

}