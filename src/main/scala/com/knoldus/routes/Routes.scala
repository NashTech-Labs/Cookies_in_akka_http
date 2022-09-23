package com.knoldus.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, StatusCodes}
import akka.http.scaladsl.model.headers.HttpCookie
import akka.http.scaladsl.server.Directives.{complete, path}
import akka.http.scaladsl.server.Directives.{entity, _}
import com.knoldus.models.Models.{InfoRequest, InfoResponse}
import com.knoldus.services.Service
import com.knoldus.models.HttpProtocols._

class Routes {

  val service = new Service
  val routes: Route = {
    ignoreTrailingSlash {
      path("health" / "check") {
        get {
          complete(StatusCodes.OK, "Testing Successful !!!")
        }
      } ~ path("request") {
        (post & entity(as[InfoRequest])) {
          request =>
            val response: InfoResponse = service.processRequest(request)
            getResponse(response) match {
              case (httpResponse, Some(cookie)) => setCookie(cookie) {
                complete(httpResponse)
              }
              case (httpResponse, _) => complete(httpResponse)

            }
        }
      }
    }
  }

  private def getResponse(infoResponse: InfoResponse) = {
    val CookieMaxAge = 15811200

    val jwtCookie: HttpCookie = HttpCookie("auth", s"${infoResponse.userId}-${infoResponse.name}-${infoResponse.email}", maxAge =  Some(CookieMaxAge))
    val httpEntity = HttpEntity(ContentTypes.`application/json`, infoResponse.toString)
    (HttpResponse(status = StatusCodes.OK, entity = httpEntity), Some(jwtCookie))

  }

}
