package com.cloudera.ps.kuduHackathon

import twitter4j.{Status, Query, TwitterFactory, Twitter}
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by bimal on 3/21/16.
 */


class twitterSource {
  def getTweets(q:String) : List[TweetPojo] = {
    try {
      // (1) config work to create a twitter object
      val cb = new ConfigurationBuilder()
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("2Hxekv3iMZHWDvkNZ8l6uZvhp")
        .setOAuthConsumerSecret("JF8G77u9wM1vc2J3tEnIB5eJRmy2ApCSj9FtLyXaPWJUe9rm7A")
        .setOAuthAccessToken("142516285-gghD8jwMagvGnFQ2Fx4qOTPkNqNIo5reR4F1bqy4")
        .setOAuthAccessTokenSecret("IAgESwN0mzd3RtbKlMffspJfaWHA9PvCN9BExvZxEAv32")
      val tf = new TwitterFactory(cb.build())
      val twitter = tf.getInstance()

      val qry = new Query("source:twitter4j trump")

      // (2) use the twitter object to get your friend's timeline
      val result = twitter.search(qry)
      val tweets = result.getTweets

      for (status:Status <- tweets) {
        println (status.getUser.getScreenName + status.getFavoriteCount + status.getRetweetCount + status.getText)
      }

    } catch
    {
      case e: Exception => println(e)
    }
  }
}

