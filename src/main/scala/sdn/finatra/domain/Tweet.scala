package sdn.finatra.domain

import com.twitter.finatra.domain.WrappedValue

/**
  * Created by SangDang on 8/5/16.
  */
case class Tweet(id: TweetId
                 , message: String
                 , location: Option[Location]
                 , sensitive: Boolean = false) {

}
case class TweetId(id:String) extends WrappedValue[String]
case class Location(lat:Double, long: Double)