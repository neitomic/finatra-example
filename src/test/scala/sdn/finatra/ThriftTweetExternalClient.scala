package sdn.finatra

import java.net.InetSocketAddress

import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.inject.Test
import sdn.finatra.domain.thrift.scala.{TTweetRequest, TTweetResponse, TTweetService}
import com.twitter.util.{Await, Future}

/**
  * Created by SangDang on 8/8/16.
  * External client to call to TweetService.
  * ThriftTweetServer have to start before run this test.
  */
object TweetServiceClient {
  def buildClient(address: InetSocketAddress) = {
    val clientService = ClientBuilder()
      .hosts(Seq(address))
      .codec(ThriftClientFramedCodec())
      .hostConnectionLimit(1)
      .build()
    new TTweetService.FinagledClient(clientService)
  }
}

class ThriftTweetExternalTest extends Test {

  val client = TweetServiceClient.buildClient(new InetSocketAddress("localhost", 9090))
  "tweet success" in {
    val runningExternalTest: Boolean = System.getenv("test.external").toBoolean
    if (runningExternalTest) {
      val response: Future[TTweetResponse] = client.addTweet(TTweetRequest.apply(id = "123", "hello"))
      val result = Await.result(response)
      result.id should equal("123")
      result.message should equal("hello")
      result.location should equal(None)
      result.sensitive should equal(None)
      println("ok")
    } else {
      println("canceled")
      info("disable running external thrift client test")
    }
  }


}
