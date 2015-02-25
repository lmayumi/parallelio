package com.example

import scala.io.Source._
import java.io._
import java.util.concurrent.atomic._
import scala.concurrent._
import scala.concurrent.forkjoin.ForkJoinPool
import java.util.concurrent.TimeUnit

object NonBlockingIO {
  import Utils._
  val LOOP_COUNT = 100000
  
  def main(args: Array[String]): Unit = {
    val executor =  new ForkJoinPool()
    implicit val context = ExecutionContext.fromExecutor(executor)

    timeElapsedInSeconds {
      loopFor (LOOP_COUNT) {
        Future(read).foreach(write)
      }
      
      try {
        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.SECONDS)     
      } catch {
        case e: Exception => println("Something went wrong!")  
      }  
    }    
  }

}