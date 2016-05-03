package com.qburst.deeplearning

import org.imgscalr._
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/** This object is used to crop an image into square shape.
  *
  * Created by gopikrishnan on 5/2/16.
  */

object MakeSquare {
  val imagePath = "data/images/train/"
  val imageFormat = "jpg"
  val imageName = "20"

  def makeSquare(img: BufferedImage): BufferedImage = {
    val w = img.getWidth
    val h = img.getHeight
    val dim = List(w, h).min

    img match {
      case x if w == h => img
      case x if w > h => Scalr.crop(img, (w - h) / 2, 0, dim, dim)
      case x if w < h => Scalr.crop(img, 0, (h - w) / 2, dim, dim)
    }

//    val myImg = ImageIO.read(new File(s"$imagePath$imageName.$imageFormat"))
//     val myImgSquare = makeSquare(img)
//    ImageIO.write(myImgSquare, imageFormat, new File(s"$imagePath$imageName-squared.$imageFormat"))
  }
}
