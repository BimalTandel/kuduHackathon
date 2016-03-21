package com.cloudera.ps.kuduHackathon

import twitter4j.{Status, Query, TwitterFactory, Twitter}
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by bimal on 3/21/16.
 */
object twitterSource {
  def main(args: Array[String]) {
    // Current code supports only Eligibility Json data
    if (args.length != 1) {
      println("Job requires arguments: <arg1>")
      throw new IllegalArgumentException("Invalid argument list exception")
    }
    try {
      // (1) config work to create a twitter object
      val cb = new ConfigurationBuilder()
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("YOUR KEY HERE")
        .setOAuthConsumerSecret("YOUR SECRET HERE")
        .setOAuthAccessToken("YOUR ACCESS TOKEN")
        .setOAuthAccessTokenSecret("YOUR ACCESS TOKEN SECRET")
      val tf = new TwitterFactory(cb.build())
      val twitter = tf.getInstance()

      val qry = new Query("source:twitter4j trump")

      // (2) use the twitter object to get your friend's timeline
      val result = twitter.search(qry)
      val tweets = result.getTweets
      for (status <- tweets) {
        status.
      }

    } catch
    {
      case e: Exception => println(e)
    }
  }
}

