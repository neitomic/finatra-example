package sdn.finatra

import com.twitter.finagle.http.Status._
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.thrift.ThriftClient
import com.twitter.inject.Test
import com.twitter.inject.server.FeatureTest
import com.twitter.util.Future
import sdn.finatra.domain.thrift.scala.{TTweetRequest, TTweetService}

/**
  * Created by SangDang on 8/5/16.
  */
class TweetFeatureTest extends FeatureTest {
  val server = new EmbeddedHttpServer(twitterServer = new TweetServer) with ThriftClient

  "[HTTP] [Tweet]" should {
    "post tweet successful" in {
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

    "failed if missing message" in {
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


  "[Thrift] [Tweet]" should {
    lazy val tweetServerClient = server.thriftClient[TTweetService[Future]](clientId = "1")
    "add tweet successful" in {
      val response = tweetServerClient.addTweet(TTweetRequest.apply(id = "123", "hello"))
      response.value.id should equal("123")
      response.value.message should equal("hello")
      response.value.location should equal(None)
      response.value.sensitive should equal(None)
    }


  }
}
