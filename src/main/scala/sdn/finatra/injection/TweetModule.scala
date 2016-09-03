package sdn.finatra.injection

import com.google.inject.AbstractModule
import sdn.finatra.services.{DummyTweetService, TweetService}

/**
  * Created by tiennt4 on 02/09/2016.
  */
object TweetModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[TweetService]).to(classOf[DummyTweetService])
  }
}
