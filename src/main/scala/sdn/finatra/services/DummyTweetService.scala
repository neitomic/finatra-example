package sdn.finatra.services
import com.google.inject.Singleton
import sdn.finatra.domain.Tweet

/**
  * Created by tiennt4 on 02/09/2016.
  */
@Singleton
class DummyTweetService extends TweetService {
  override def index(tweet: Tweet): Tweet = tweet
}
