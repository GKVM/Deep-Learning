package com.qburst.deeplearning.modeling.io

import scala.io.Source

/**
  * Created by gopikrishnan on 5/3/16.
  */
object ReadCsvData {

  /** Function to load CSV.
    *
    * @param csv
    * @param rows
    * @return
    */
  def readCsv(csv: String, rows: List[Int]=List(-1)):  List[List[String]] = {
    val src = Source.fromFile(csv)
    def reading(csv: String): List[List[String]] = {
      src.getLines.map(x => x.split(",").toList)
        .toList
    }
    //src.close
    try {
      if(rows==List(-1)) reading(csv)
      else rows.map(reading(csv))
    } finally {
      src.close
    }
  }

  /** Create a map from image to labels.
    *
    * @param csv
    * @param rows
    * @return
    */
  def readImageToLabels(csv: String, rows: List[Int] = List(-1)): Map[String, Int] ={
    val src = readCsv(csv)
    val csvData = src.drop(1) //To drop header.
      .map(x => x match{
      case x:: Nil => (x(0).toString, x(1).toInt)
      case _ => (x(0).toString, x(1).toInt)
    }).toMap

    println(s"CSV data: ${csvData.mkString(",")}")
    csvData
  }
}
