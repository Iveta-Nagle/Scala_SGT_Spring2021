package com.assignment.weather

case class Station(stationName: String, stationEUCode: String,
                   stationLocalCode: String, stationDescription: String,
                   stationNutsLevel0: Int, stationNutsLevel1: Int,
                   stationNutsLevel2: Int, stationNutsLevel3: Int,
                   lauLevel1Code: String, lauLevel2Name: String,
                   sabeCountryCode: String, sabeUnitCode: String, sabeUnitName: String,
                   stationStartDate: String, stationLatitudeDecDegrees: Double,
                   stationLongitudeDecDegrees: Double, stationLatitudeDms: String,
                   stationLongitudeDms: String, stationAltitude: Int,
                   typeOfStation: String, stationTypeOfArea: String,
                   stationCharactOfZone: String, stationSubcRurBackg: String,
                   monitoringObj: String, meteorologicalParameter: String
                  )
