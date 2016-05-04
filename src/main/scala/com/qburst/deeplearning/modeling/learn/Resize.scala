package com.qburst.deeplearning.modeling.learn

import java.awt.image.BufferedImage

import org.imgscalr.Scalr

/** Can be used to resize images.
  *
  * Created by gopikrishnan on 5/2/16.
  */

object Resize {
  val imagePath = "data/images/train/"
  val imageFormat = "jpg"
  val imageName = "20"
  def resizeImage(img: BufferedImage, width: Int, height: Int) = {
    Scalr.resize(img, Scalr.Method.BALANCED, width, height)
  }

//  val myImg = ImageIO.read(new File(s"$imagePath$imageName.$imageFormat"))
//
//  val myImage64 = resizeImage(myImg, 64, 64)
//  //todo Try 32, 64, 128, 256
//
//  ImageIO.write(myImage64, imageFormat, new File(s"$imagePath$imageName-resized.$imageFormat"))
}
