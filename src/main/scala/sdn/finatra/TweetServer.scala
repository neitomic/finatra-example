package sdn.finatra

import com.twitter.finagle.Http
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters.{LoggingMDCFilter, StatsFilter, ThriftMDCFilter}
import com.twitter.finatra.thrift.routing.ThriftRouter
import com.twitter.inject.server.TwitterServer
import sdn.finatra.controllers.http.TweetController
import sdn.finatra.controllers.thrift.ThriftTweetControler

import scala.concurrent.Await

/**
  * Created by SangDang on 8/5/16.
  */
class TweetServer extends HttpServer with ThriftServer {
  override protected def disableAdminHttpServer: Boolean = true

  override protected def defaultFinatraHttpPort: String = ":8080"

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[TweetController]
  }

  override def toString: String = {
    return "HttpServer "
  }

  override val defaultFinatraThriftPort: String = ":9090"

  override protected def configureThrift(router: ThriftRouter): Unit = {
    router
      .filter[LoggingMDCFilter]
      .add[ThriftTweetControler]
  }
}

