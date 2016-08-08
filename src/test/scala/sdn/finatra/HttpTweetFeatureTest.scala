package sdn.finatra

import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.Test

/**
  * Created by SangDang on 8/5/16.
  */
class HttpTweetFeatureTest extends Test {
  val server = new EmbeddedHttpServer(twitterServer = new TweetServer)
  "Post Tweeet" in {
    val result = server.httpPost(
      path = "/tweet"
      , postBody =
        """
               {
                  "message": "Hello",
                  "location": {
                    "lat": 90.0,
                    "long": 120.0
                  },
                  "sensitive": false
                }"""
      , andExpect = Created
      , withLocation = "/tweet/123"
      , withJsonBody =
        """
               {
                  "id":"123",
                  "message": "Hello",
                  "location": {
                    "lat": 90.0,
                    "long": 120.0
                  },
                  "sensitive": false
                }"""
    )

  }

  "Missing field should return BadRequest " in {
    val result = server.httpPost(
      path = "/tweet"
      , postBody =
        """
               {
                  "location": {
                    "lat": 90.0,
                    "long": 120.0
                  },
                  "sensitive": false
                }"""
      , andExpect = BadRequest
    )
  }
}
