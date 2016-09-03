package sdn.finatra.controllers.thrift

import com.google.inject.Inject
import com.twitter.finagle.Service
import com.twitter.finatra.thrift.Controller
import com.twitter.inject.Logging
import com.twitter.util.Future
import sdn.finatra.domain.thrift.scala.TTweetService.AddTweet.{Args, Result}
import sdn.finatra.domain.thrift.scala.{TLocation, TTweetResponse, TTweetService}
import sdn.finatra.domain.{Location, Tweet, TweetId}
import sdn.finatra.services.TweetService

/**
  * Created by SangDang on 8/8/16.
  */
class ThriftTweetControler @Inject()(
                                      tweetService: TweetService
                                    ) extends Controller with TTweetService.BaseServiceIface with Logging {
  override def addTweet: Service[Args, Result] = {

    new Service[Args, Result] {
      override def apply(args: Args): Future[Result] = {
        //        println("addTweet "+ args.request.toString)
        info("addTweet " + args.request.toString)
        Future.value(
          Result(Option {
            val tweet = Tweet(TweetId(args.request.id),
              args.request.message,
              args.request.location match {
                case Some(tLocation) => Option(Location(tLocation.lat, tLocation.long))
                case None => None
              }
            )
            val resp = tweetService.index(tweet)

            TTweetResponse(resp.id.id, resp.message,
              resp.location match {
                case Some(location) => Option(TLocation(location.lat, location.lat))
                case None => None
              }
            )
          })
        )
      }
    }

  }

}

