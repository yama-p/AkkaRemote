package services

import akka.actor.{Actor, ActorLogging}
import jni.CpplibJNI
import services.common.{ReqData, SendData}

class RemoteActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case SendData(l: Long) =>
      log.info("Remote received {} from {}", l, sender)
      val jni = new CpplibJNI
      val str: String = jni.cpplib(l.toInt)
      sender ! ReqData(str)
    case any =>
      log.info("Remote received unknown message {} from {}", any, sender)
  }
}
