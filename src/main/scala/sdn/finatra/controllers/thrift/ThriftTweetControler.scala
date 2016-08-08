package sdn.finatra.controllers.thrift

import com.twitter.finagle.Service
import com.twitter.finatra.thrift.Controller
import com.twitter.util.Future
import sdn.finatra.domain.thrift.scala.TTweetService.AddTweet.{Args, Result}
import sdn.finatra.domain.thrift.scala.{TTweetResponse, TTweetService}

/**
  * Created by SangDang on 8/8/16.
  */
class ThriftTweetControler extends Controller with TTweetService.BaseServiceIface {
  override def addTweet: Service[Args, Result] = {
    new Service[Args, Result] {
      override def apply(args: Args): Future[Result] = {
        Future.value(
          Result(Option(TTweetResponse(args.request.id, args.request.message)))
        )
      }
    }

  }

}

