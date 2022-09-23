package com.knoldus.services

import com.knoldus.models.Models.{InfoRequest, InfoResponse}

import java.util.UUID

class Service {

  def processRequest(info: InfoRequest): InfoResponse = {
    val id = UUID.randomUUID().toString
    InfoResponse(id, info.name, info.email)
  }
}
