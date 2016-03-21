package com.cloudera.ps.kuduHackathon

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

import org.kududb.spark.KuduDStreamFunctions.GenericKuduDStreamFunctions

/**
 * Created by bimal on 3/21/16.
 */
object streamTwitterMain {
  def main (args: Array[String]) = {
    try {
      val tmplong = args(0).toLong
    } catch {
      case e: Exception => println("Expecting Long datatype for microBatchInSeconds " + e)
    }
    val conf = new SparkConf().setAppName("streamTwitterMain")

    val microBatchInSeconds = args(0).toLong
    val ssc = new StreamingContext(conf, Seconds(microBatchInSeconds))

    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("2Hxekv3iMZHWDvkNZ8l6uZvhp")
      .setOAuthConsumerSecret("JF8G77u9wM1vc2J3tEnIB5eJRmy2ApCSj9FtLyXaPWJUe9rm7A")
      .setOAuthAccessToken("142516285-gghD8jwMagvGnFQ2Fx4qOTPkNqNIo5reR4F1bqy4")
      .setOAuthAccessTokenSecret("IAgESwN0mzd3RtbKlMffspJfaWHA9PvCN9BExvZxEAv32")
    val tf = new TwitterFactory(cb.build())
    val twitterAuth = Some(tf.getInstance().getAuthorization())

    val tweetStream = TwitterUtils.createStream(ssc, twitterAuth, Seq("trump"), StorageLevel.MEMORY_ONLY)

    val pojoStream = tweetStream.map(x => {
      val tweetPojo = new TweetPojo(x.getUser.getScreenName, x.getText, x.getRetweetCount, x.getFavoriteCount)
      tweetPojo
    })

//    pojoStream.kuduForeachPartition()

//    val t = pojoStream.map(x => ("0", x))

  }
}
