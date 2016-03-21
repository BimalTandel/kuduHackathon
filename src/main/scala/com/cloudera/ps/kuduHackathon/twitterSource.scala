package com.cloudera.ps.kuduHackathon


import twitter4j.{Status, Query, TwitterFactory, Twitter}
import twitter4j.conf.ConfigurationBuilder
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

/**
 * Created by bimal on 3/21/16.
 */


class TwitterSource {

  //implicit def javaIteratorToScalaIterator[A](it : java.util.Iterator[A]) = new Wrapper(it)

  def getTweets(q:String) : List[TweetPojo] = {
      // (1) config work to create a twitter object
      val cb = new ConfigurationBuilder()
      cb.setDebugEnabled(true)
        .setOAuthConsumerKey("2Hxekv3iMZHWDvkNZ8l6uZvhp")
        .setOAuthConsumerSecret("JF8G77u9wM1vc2J3tEnIB5eJRmy2ApCSj9FtLyXaPWJUe9rm7A")
        .setOAuthAccessToken("142516285-gghD8jwMagvGnFQ2Fx4qOTPkNqNIo5reR4F1bqy4")
        .setOAuthAccessTokenSecret("IAgESwN0mzd3RtbKlMffspJfaWHA9PvCN9BExvZxEAv32")
      val tf = new TwitterFactory(cb.build())
      val twitter = tf.getInstance()

      val qry = new Query(q)

      // (2) use the twitter object to get your friend's timeline
      println("the search string is " + qry)
      val result = twitter.search(qry)
      val tweets = result.getTweets
      var tweetPojos = new ListBuffer[TweetPojo]
      val c = tweets.count(x => true)
      println ("Number of tweets are" + c)
      for (status <- tweets) {
        val rtweetPojo = new TweetPojo(status.getUser.getScreenName, status.getText, status.getRetweetCount, status.getFavoriteCount )
        tweetPojos += rtweetPojo

        println (status.getUser.getScreenName + status.getFavoriteCount + status.getRetweetCount + status.getText)
      }

      val returnobj = tweetPojos.toList
      returnobj

  }
}

