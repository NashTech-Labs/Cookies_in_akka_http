package com.knoldus.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.models.Models.{InfoRequest, InfoResponse}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object Models {

  final case class InfoResponse(userId: String, name: String, email: String)

  final case class InfoRequest(name: String, email: String)
}

object HttpProtocols extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val request: RootJsonFormat[InfoRequest] = jsonFormat2(InfoRequest)
  implicit val response: RootJsonFormat[InfoResponse] = jsonFormat3(InfoResponse)
}
