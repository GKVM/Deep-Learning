package com.qburst.deeplearning

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.util
import javax.imageio.ImageIO

/** Can be used to convert a grayscale image to
  * Created by gopikrishnan on 5/2/16.
  */
object Grayscaling {

  val imagePath = "data/images/train/"
  val imageFormat = "jpg"
  val imageName = "20"

  def pixels2gray(red: Int, green : Int, blue: Int): Int  = (red + green + blue)/3

  def makeGray (img : BufferedImage): BufferedImage = {
    val w = img.getWidth
    val h = img.getHeight
    for {
      w1 <- (0 until w).toVector
      h1 <- (0 until h).toVector
    } yield {
      val col = img.getRGB(w1, h1)
      val red =  (col & 0xff0000) / 65536
      val green = (col & 0xff00) / 256
      val blue = (col & 0xff)
      val graycol = pixels2gray(red, green, blue)

      img.setRGB(w1, h1, new Color(graycol, graycol, graycol).getRGB)
    }
    img
  }

//  val myImg = ImageIO.read(new File(s"$imagePath$imageName.$imageFormat"))
//  val myGrayImage = makeGray(myImg)
//  ImageIO.write(myGrayImage, imageFormat, new File(s"$imagePath$imageName-gray.$imageFormat"))
}
