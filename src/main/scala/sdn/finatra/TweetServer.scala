package sdn.finatra

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters.{LoggingMDCFilter, StatsFilter, ThriftMDCFilter}
import com.twitter.finatra.thrift.routing.ThriftRouter
import sdn.finatra.controllers.http.TweetController
import sdn.finatra.controllers.thrift.ThriftTweetControler

/**
  * Created by SangDang on 8/5/16.
  */
class TweetServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[TweetController]
  }
}

class ThriftTweetServer extends ThriftServer {
  override val defaultFinatraThriftPort: String = ":9090"

  override protected def configureThrift(router: ThriftRouter): Unit = {
    router
      .filter[LoggingMDCFilter]
      .add[ThriftTweetControler]
  }
}

object ThriftTweetServerMain extends ThriftTweetServer{
}

