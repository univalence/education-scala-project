package org.sparklinedata.druid.testenv

import java.net.ServerSocket
import scala.collection.mutable.ArrayBuffer


trait FreePortFinder {

  val MIN_PORT_NUMBER = 8000
  val MAX_PORT_NUMBER = 49151

  def findFreePorts(numPorts: Int = 1): List[Int] = {
    val freePorts = ArrayBuffer[Int]()
    (MIN_PORT_NUMBER until MAX_PORT_NUMBER).foreach { i =>
      if (available(i)) {
        freePorts += i
      }
      if (freePorts.size == numPorts) {
        return freePorts.toList
      }
    }
    throw new RuntimeException(s"Could not find $numPorts ports between " +
      MIN_PORT_NUMBER + " and " + MAX_PORT_NUMBER)
  }

  def available(port: Int): Boolean = {
    var serverSocket: Option[ServerSocket] = None
    try {
      serverSocket = Some(new ServerSocket(port))
      serverSocket.get.setReuseAddress(true);
      return true
    } catch {
      case _: Throwable => return false
    } finally {
      serverSocket.map(_.close())
    }
  }
}