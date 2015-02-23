package com.example

import scala.io.Source._
import java.io._
import java.util.concurrent.atomic._
import scala.concurrent._
import java.util.concurrent.Executors

object Utils {

  def loopFor(count: Int) (block: => Unit): Unit = {
    var counter = new AtomicInteger(0)
    do {
      block
    } while (counter.incrementAndGet() < count)
  }
  
  def timeElapsedInSeconds(block: => Unit): Double = {
    val start = System.nanoTime()
    block
    val end = System.nanoTime()
    val elapsedTimeSeconds = (end - start) / 1000000000.0
    println(s"Time Elapsed: $elapsedTimeSeconds seconds.")  
    elapsedTimeSeconds
  }
  
  def read: Int = {
    val nums: Iterator[String] = fromFile("src/main/scala/com/example/Input.txt").getLines
    val sum = nums.foldLeft(0) { (acc, str) => (str.toDouble).toInt + acc }
    sum
  }
  
  def write(sum: Int): Unit = {
    val writer = new PrintWriter(new File("src/main/scala/com/example/Output.txt" ))
    writer.write("The sum is: " + sum)
    writer.close() 
  }  
}