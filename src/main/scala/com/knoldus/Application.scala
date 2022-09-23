package com.knoldus

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.Materializer
import com.knoldus.routes.Routes

object Application extends App {

  implicit val actorSystem = ActorSystem("Cookies_System")
  implicit val materializer = Materializer

  val routes = new Routes
  Http(actorSystem).bindAndHandle(routes.routes, "localhost", 8081)

}
