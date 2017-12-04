package services

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object RemoteApp extends App {
  val configFile = getClass.getClassLoader.getResource(s"remote.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))
  val system = ActorSystem("remote-app", config)
  val remote = system.actorOf(Props[RemoteActor], name="remote")
  system.log.info(s"$config")
  system.log.info("Remote is ready")
}
