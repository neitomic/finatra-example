package sdn.finatra

import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.logging.modules.Slf4jBridgeModule
import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.filters.LoggingMDCFilter
import com.twitter.finatra.thrift.routing.ThriftRouter
import sdn.finatra.controllers.http.TweetController
import sdn.finatra.controllers.thrift.ThriftTweetControler
import sdn.finatra.injection.TweetModule

/**
  * Created by SangDang on 8/5/16.
  */
object TweetServerMain extends TweetServer

class TweetServer extends HttpServer with ThriftServer {
  override protected def disableAdminHttpServer: Boolean = true

  override def modules: Seq[Module] = Seq(Slf4jBridgeModule, TweetModule)

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

