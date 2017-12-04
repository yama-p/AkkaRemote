package services

import akka.actor.{Actor, ActorLogging}
import jni.CpplibJNI
import services.common.{ResponseMsg, SendToRemote}

class RemoteActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case SendToRemote(l: Long) =>
      log.info(s"Remote received from $l")
      val jni = new CpplibJNI
      val str: String = jni.cpplib(l.toInt)
      sender ! ResponseMsg(str)
    case any =>
      log.info("Remote received unknown message {} from {}", any, sender)
  }
}
