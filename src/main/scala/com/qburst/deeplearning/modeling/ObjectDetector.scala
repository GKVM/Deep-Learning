package com.qburst.deeplearning.modeling

import com.qburst.deeplearning.modeling.io.ReadCsvData._
import com.qburst.deeplearning.modeling.processing.Images._
/**
  * Created by gopikrishnan on 5/3/16.
  */
object ObjectDetector {
  def main(args: Array[String]) {

    val directory = "/home/gopikrishnan/Dataset/Custom dataset"


    //Training data.
    val photoMap = readImageToLabels(s"$directory/train.csv")

    val images = getImageIds(s"$directory").slice(0, 20000)
    val dataMap = processImages(images, resizeImageDimension = 64)

  }
}
