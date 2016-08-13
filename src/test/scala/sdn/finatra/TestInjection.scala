package sdn.finatra

import java.util.Properties

import com.google.inject.Guice
import sdn.finatra.modules.KafkaConsumerModule
import sdn.finatra.models.kafka.KafkaConsumerFactory

/**
  * Created by tiennt4 on 13/08/2016.
  */
object TestInjection {


  def main(args: Array[String]): Unit = {
    val properties = new Properties();
    properties.put("topic.name", "test");
    properties.put("batch.MinSize", "122");
    val injector = Guice.createInjector(new KafkaConsumerModule)
    val factory = injector.getInstance(classOf[KafkaConsumerFactory])
    val consumer = factory.create(properties)
    consumer.start()
//    @Inject def module : KafkaConsumerFactory
//
//    val consumer = module.create(new Properties())
//    consumer.start()
  }
}
