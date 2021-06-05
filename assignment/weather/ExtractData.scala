package com.assignment.weather

import scala.xml.XML

object ExtractData extends App {

  val dataPath = "./src/resources/EE_meta.xml"

  val dataXML = XML.loadFile((dataPath))

  val stationNameNodes = dataXML \ "country" \ "station" \ "station_info" \ "station_name"
  val stationNames =stationNameNodes.map(node => node.text)
  stationNames.foreach(println)

}
