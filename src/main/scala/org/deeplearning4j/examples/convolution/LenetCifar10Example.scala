package org.deeplearning4j.examples.convolution

import org.nd4j.linalg.api.buffer.DataBuffer
import org.nd4j.linalg.factory.Nd4j
import org.slf4j.{Logger, LoggerFactory}

/**
  * Created by gopikrishnan on 4/29/16.
  */
object LenetCifar10Example {

  lazy val log: Logger = LoggerFactory.getLogger(LenetCifar10Example.getClass)

  def main(args: Array[String]) {
    Nd4j.dtype = DataBuffer.Type.DOUBLE
    Nd4j.factory().setDType(DataBuffer.Type.DOUBLE)
    Nd4j.ENFORCE_NUMERICAL_STABILITY = true

    val nChannels = 1
    val outputNum = 10
    val batchSize = 1000
    val nEpochs = 1
    val iterations = 1
    val seed = 123

    log.info("Load data....")


  }
}
