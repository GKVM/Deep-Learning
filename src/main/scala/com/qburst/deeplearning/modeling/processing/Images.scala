package com.qburst.deeplearning.modeling.processing

import java.io.File
import javax.imageio.ImageIO

import scala.util.matching.Regex

import com.qburst.deeplearning.modeling.processing.ImageUtilities._

/**
  * Created by gopikrishnan on 5/3/16.
  */
object Images {

  val patternMatcherFileName = new Regex("[0-9]")

  /** Get a list of images to load and process.
    *
    * @param photoDirectory
    * @param photoMap
    */
  def getImageIds(photoDirectory: String,
                  photoMap: Map[String, Int] = Map("-1" -> -1),
                  photoIds: List[String] = List("-1")
                 ): List[String] = {
    val directory = new File(photoDirectory)

    //Print the name of each file in the directory.
    println(s"List of files in directory: ${directory.listFiles.map{x=>x.getName}.mkString(",")}")
    //Prints the entire directory of each image.
    println(s"List of files in directory: ${directory.listFiles.mkString(",")}")

    val imagesPath = directory.listFiles.map(x=>x.toString).toList

//    if(photoMap == Map ("-1" -> 1)){
//      println("Image names not  specified")
      imagesPath
//    }else{
//      val imagesMap = imagesPath.map(x =>
//        patternMatcherFileName.findAllIn(x).mkString.toInt -> x).toMap
//      val imageNameList = imagesMap.map{x=>x._2}.toList
//      println(s"List of images in file: ${imageNameList.mkString(", ")}")
//      imageNameList
//    }
  }

  /** Read and process images into a photoID -> vector map
    *
    * @param imageNameList          list of images to read-in.  created from getImageIds function.
    * @param resizeImageDimension  dimension to rescale square images to
    * @param noPixels               number of pixels to maintain.mainly used to sample image to drastically reduce  runtime while testing features.
    * @return
    */
  def processImages(imageNameList: List[String],
                    resizeImageDimension: Int = 128,
                    noPixels: Int = -1):
  Map[Int , Vector[Int]] ={
    imageNameList.map(x=>{
      patternMatcherFileName.findAllIn(x).mkString.toInt -> {
        val img0 = ImageIO.read(new File(x))
          .makeSquare
          .resizeImage(resizeImageDimension, resizeImageDimension)
          .imageToGray
        if(noPixels != -1)
          img0.slice(0, noPixels)
        else
          img0
      }
    }).filter(x => x._2 != ()).toMap
  }
}
