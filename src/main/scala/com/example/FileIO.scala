package com.example

import scala.concurrent._

object FileIO {
  
  def main(args: Array[String]): Unit = {
    
    performBlockingIO
    
    import ExecutionContext.Implicits.global
    performParallelIO
  }

  import Utils._
  val LOOP_COUNT = 100000
  
  def performParallelIO(implicit context: ExecutionContext): Unit = {
    timeElapsedInSeconds {
      loopFor (LOOP_COUNT) {
        Future(read).foreach(write)
      }
    }
  }
  
  def performBlockingIO: Unit = {
    timeElapsedInSeconds {
      loopFor (LOOP_COUNT) {
        write(read) 
      }
    }
  }    
}
