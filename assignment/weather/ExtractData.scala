package com.assignment.weather

import scala.xml.XML
import org.json4s.Xml.toJson

import com.github.ivetan.Utilities.saveLines

object ExtractData extends App {

  val folderName = "./src/resources"
  val dataPath = "./src/resources/EE_meta.xml"

  def getFilePath(folderName: String, stationName: String, stationEUCode: String, prefix: String, suffix: String):String = {
    val name = stationName + "_"
    s"$folderName/$name$stationEUCode$prefix$suffix"
  }

  val dataXML = XML.loadFile(dataPath)
  val stationNodes = dataXML \ "country" \ "station"

  def fromXMLtoFile(node: scala.xml.Node): Station = {
    Station(
      stationName = (node \ "station_info" \ "station_name").text,
      stationEUCode = (node \ "station_european_code").text,
      stationLocalCode = (node \ "station_local_code").text,
      stationDescription = (node \ "station_description").text,
      stationNutsLevel0 = (node \ "station_nuts_level0").toString.toInt,
      stationNutsLevel1 = (node \ "station_nuts_level1").toString.toInt,
      stationNutsLevel2 = (node \ "station_nuts_level2").toString.toInt,
      stationNutsLevel3 = (node \ "station_nuts_level3").toString.toInt,
      lauLevel1Code = (node \ "lau_level1_code").text,
      lauLevel2Name = (node \ "lau_level2_name").text,
      sabeCountryCode = (node \ "sabe_country_code").text,
      sabeUnitCode = (node \ "sabe_unit_code").text,
      sabeUnitName = (node \ "sabe_unit_name").text,
      stationStartDate = (node \ "station_start_date").text,
      stationLatitudeDecDegrees = (node \ "station_latitude_decimal_degrees").toString.toDouble,
      stationLongitudeDecDegrees = (node \ "station_longitude_decimal_degrees").toString.toDouble,
      stationLatitudeDms = (node \ "station_latitude_dms").text,
      stationLongitudeDms = (node \ "station_longitude_dms").text,
      stationAltitude = (node \ "station_altitude").toString().toInt,
      typeOfStation = (node \ "type_of_station").text,
      stationTypeOfArea = (node \ "station_type_of_area").text,
      stationCharactOfZone = (node \ "station_characteristic_of_zone").text,
      stationSubcRurBackg = (node \ " station_subcategory_rural_background").text,
      monitoringObj = (node \ "monitoring_obj").text,
      meteorologicalParameter = (node \ "meteorological_parameter").text

    )
  }

  val stations = stationNodes.map(node => fromXMLtoFile(node))

  val destJSONFilePaths = stations.map(station => getFilePath(folderName, station.stationName, station.stationEUCode, prefix = "_meta", suffix = ".json"))
  //destJSONFilePaths.foreach(println)

  val destTSVFilePaths = stations.map(station => getFilePath(folderName, station.stationName, station.stationEUCode, prefix = "_yearly", suffix = ".tsv"))
  //destTSVFilePaths.foreach(println)

  val stationInfoNodes = dataXML \ "country" \ "station" \ "station_info"
  val stationInfo = stationInfoNodes.map(node => toJson(node).toString)

  saveLines(stationInfo.slice(0,1), destJSONFilePaths.head)

  val station1 = stationInfo.slice(0,1)




}
