package com.qburst.deeplearning.modeling.processing

import java.awt.Color
import java.awt.image.BufferedImage

import org.imgscalr.Scalr

/** Contains utilities
  *
  * Created by gopikrishnan on 5/3/16.
  */
object ImageUtilities {
  implicit class extendingImageClass(img: BufferedImage) {

    private def pixelsToGray(red: Int, green: Int, blue: Int): Int = (red + green + blue) / 3
    private def pixelsToColor(red: Int, green: Int, blue: Int): Vector[Int] = Vector(red, green, blue)

    private def imageToVector[A](f: (Int, Int, Int) => A): Vector[A] = {
      val w = img.getHeight
      val h = img.getWidth
      for {
        w1 <- (0 until w).toVector
        h1 <- (0 until h).toVector
      } yield {
        val col = img.getRGB(w1, h1)
        val red = (col & 0xff0000) / 65536
        val green = (col & 0xff00) / 256
        val blue = (col & 0xff)
        f(red, green, blue)
      }
    }

    /** Used to convert image to grayscale. (Optional)
      *
      * @return
      */
    def makeGray = {
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
        val graycol = pixelsToGray(red, green, blue)

        img.setRGB(w1, h1, new Color(graycol, graycol, graycol).getRGB)
      }
      img
    }

    /** Vectorize grayscale image.
      *
      * @return
      */
    def imageToGray: Vector[Int] = imageToVector(pixelsToGray)

    /** Vectorize image with color.
      *
      * @return
      */
    def imageToColor: Vector[Int] = imageToVector(pixelsToColor).flatten

    /** Used to crop an image to square shape.
      *
      * @return
      */
    def makeSquare = {
      val w = img.getWidth
      val h = img.getHeight
      val dim = List(w, h).min

      img match {
        case x if w == h => img
        case x if w > h => Scalr.crop(img, (w - h) / 2, 0, dim, dim)
        case x if w < h => Scalr.crop(img, 0, (h - w) / 2, dim, dim)
      }
    }

    /** Used to resize image.
      *
      * @param width
      * @param height
      * @return
      */
    def resizeImage(width: Int, height: Int) = {
      Scalr.resize(img, Scalr.Method.BALANCED, width, height)
    }
  }
}
