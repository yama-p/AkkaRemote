package services

import java.io.File

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object RemoteApp extends App {
  val configFile = getClass.getClassLoader.getResource("application.conf").getFile
  val config = ConfigFactory.parseFile(new File(configFile))
  val system = ActorSystem("remote-system", config)
  val remote = system.actorOf(Props[RemoteActor], name="remote")
  system.log.info("Remote is ready")
}
