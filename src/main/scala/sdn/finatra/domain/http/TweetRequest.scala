package sdn.finatra.domain.http

import sdn.finatra.domain
import sdn.finatra.domain.{Tweet, TweetId}
import com.twitter.finatra.annotations

/**
  * Created by SangDang on 8/5/16.
  */
case class TweetRequest(
                         message: String,
                         location: Option[domain.Location],
                         sensitive: Boolean = false
                       ) {
  def toTweet(id: String): Tweet = {
    Tweet(TweetId(id), message, location, sensitive)
  }
}
