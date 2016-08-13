package sdn.finatra.models.kafka

import java.util.Properties

/**
  * Created by tiennt4 on 13/08/2016.
  */
trait KafkaConsumerFactory {
  def create(properties: Properties) : KafkaHadoopConsumer
}
