package sdn.finatra.controllers.http

import com.google.inject.Inject
import com.twitter.finatra.http.Controller
import sdn.finatra.domain.http.TweetRequest
import sdn.finatra.services.TweetService


/**
  * Created by SangDang on 8/5/16.
  */
class TweetController @Inject()(
                                 tweetService: TweetService
                               ) extends Controller {
  post("/tweet") { postedTweet: TweetRequest =>
    response.created(tweetService.index(postedTweet.toTweet("123"))).location("/tweet/123")
  }


}
