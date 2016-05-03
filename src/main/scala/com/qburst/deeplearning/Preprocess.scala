package com.qburst.deeplearning

import java.io.File
import javax.imageio.ImageIO
import Vectorize._

/** Used to preprocess images by
  *   cropping
  *   resizing and
  *   applying grayscale.
  *
  * Created by gopikrishnan on 5/2/16.
  */
object Preprocess {

  val imagePath = "/tmp/"
  val imageFormat = "jpg"
  val imageName = "20"

  def main(args: Array[String]) {
    val myImg = ImageIO.read(new File(s"$imagePath$imageName.$imageFormat"))

    val myImgSquare = MakeSquare.makeSquare(myImg)
    ImageIO.write(myImgSquare, imageFormat, new File(s"$imagePath$imageName-squared.$imageFormat"))

    val myImage64 = Resize.resizeImage(myImg, 64, 64)
    ImageIO.write(myImage64, imageFormat, new File(s"$imagePath$imageName-resized.$imageFormat"))

    val myGrayImage = Grayscaling.makeGray(myImage64)
    ImageIO.write(myGrayImage, imageFormat, new File(s"$imagePath$imageName-gray.$imageFormat"))

    val image = myImg.makeSquare.resizeImage(64, 64)
    ImageIO.write(image, imageFormat, new File(s"$imagePath$imageName-pre.$imageFormat"))

    val imgVector = image.imageToGray
    println(imgVector)
  }
}
