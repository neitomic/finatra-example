package sdn.finatra.controllers.http

import com.twitter.finatra.http.Controller
import sdn.finatra.domain.http.TweetRequest


/**
  * Created by SangDang on 8/5/16.
  */
class TweetController extends Controller {
  post("/tweet") { postedTweet: TweetRequest =>
    response.created(postedTweet.toTweet("123")).location("/tweet/123")
  }
}
