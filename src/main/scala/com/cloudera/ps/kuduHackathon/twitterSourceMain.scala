package com.cloudera.ps.kuduHackathon

/**
 * Created by bimal on 3/21/16.
 */
object twitterSourceMain {
  def main(args: Array[String]) {
    if (args.length != 1) {
      println("Job requires arguments: search string")
      throw new IllegalArgumentException("Invalid argument list exception")
    }
    try {
      val ts = new TwitterSource
      val tsout = ts.getTweets(args(0))
      println ("These are the tweets")
      tsout.foreach(x => println(x.toString))
    } catch {
      case e: Exception => println(e)
    }
  }
}