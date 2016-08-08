package sdn.finatra

import com.google.inject.Stage
import com.twitter.finagle.ThriftRichClient
import com.twitter.inject.Test
import com.twitter.finatra.thrift.EmbeddedThriftServer
import com.twitter.inject.server.{EmbeddedTwitterServer, FeatureTest}
import sdn.finatra.domain.thrift.scala.{TLocation, TTweetRequest, TTweetService}
import com.twitter.util.Future
import sdn.finatra.domain.http.TweetRequest

/**
  * Created by SangDang on 8/8/16.
  */
class ThriftTweetFeatureTest extends FeatureTest {
  override val server = new EmbeddedThriftServer(twitterServer = new ThriftTweetServer,
    stage = Stage.PRODUCTION, flags = Map(
      "com.twitter.server.resolverMap" -> "some-thrift-service=nil!"
    ))

  lazy val tweetServerClient = server.thriftClient[TTweetService[Future]](clientId = "1")

  "Thrift Tweet Feature Test" should {
    "startup " in {
      server.assertHealthy()
    }
    "tweet success" in {
      val response = tweetServerClient.addTweet(TTweetRequest.apply(id = "123", "hello"))
      response.value.id should equal("123")
      response.value.message should equal("hello")
      response.value.location should equal(None)
      response.value.sensitive should equal(None)
    }


  }
}
