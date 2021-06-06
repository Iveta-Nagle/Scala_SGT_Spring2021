package com.assignment.weather

import scala.xml.XML
import org.json4s.Xml.toJson

case class Station(name: String, stationEUCode: String)

object ExtractData extends App {

  val folderName = "./src/resources"
  val dataPath = "./src/resources/EE_meta.xml"

  def getFilePath(folderName: String, stationName: String, stationEUCode: String, prefix: String, suffix: String):String = {
    val name = stationName + "_"
    s"$folderName/$name$stationEUCode$prefix$suffix"
  }

  val dataXML = XML.loadFile(dataPath)
  val stationNodes = dataXML \ "country" \ "station"

  def nodeToStation(node: scala.xml.Node): Station = {
    val name = (node \ "station_info" \ "station_name").text
    val stationEUCode = (node \ "station_european_code").text
    Station(name = name, stationEUCode)
  }

  val stations = stationNodes.map(node => nodeToStation(node))
  //val stationNames = stations.map(s => s.name)
  //stationNames.foreach(println)

  val filePaths = stations.map(station => getFilePath(folderName, station.name, station.stationEUCode, prefix = "_meta", suffix = ".json"))
  filePaths.foreach(println)

  val stationInfoNodes = dataXML \ "country" \ "station" \ "station_info"
  val stationInfo = stationInfoNodes.map(node => toJson(node))
  //println(stationInfo.slice(0,1))



}
