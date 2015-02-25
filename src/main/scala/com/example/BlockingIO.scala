package com.example

import scala.concurrent._

object BlockingIO {
  
  def main(args: Array[String]): Unit = {
    performBlockingIO
  }

  import Utils._
  val LOOP_COUNT = 100000

  def performBlockingIO: Unit = {
    timeElapsedInSeconds {
      loopFor (LOOP_COUNT) {
        write(read) 
      }
    }
  }    
}
