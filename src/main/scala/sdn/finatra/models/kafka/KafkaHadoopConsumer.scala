package sdn.finatra.models.kafka

import java.util.Arrays.ArrayList
import java.util.Properties

import collection.JavaConversions._
import com.google.inject.Inject
import com.google.inject.assistedinject.Assisted
import org.apache.kafka.clients.consumer.KafkaConsumer
import sdn.finatra.models.hdfs.HDFSFormatWriter
import sdn.finatra.models.schema.SchemaLoader

import scala.collection.mutable

/**
  * Created by tiennt4 on 12/08/2016.
  */

class KafkaHadoopConsumer @Inject() (
                           writer: HDFSFormatWriter,
                           schemaLoader: SchemaLoader,
                           @Assisted properties: Properties
                         ) extends Thread {
  val topic = properties.getProperty("topic.name")
  val minBashSize = properties.getProperty("batch.MinSize").toInt
  val schema = schemaLoader.loadSchema(topic)
  properties.put("enable.auto.commit", "false");
  properties.put("auto.commit.interval.ms", "1000");
  properties.put("session.timeout.ms", "30000");
  properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
  properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

  val consumer = new KafkaConsumer[String, String](properties)
  consumer.subscribe(topic)

  override def run(): Unit = {
    val buffer = mutable.Buffer[String]()
    while (true) {
      val records = consumer.poll(1000)
      val iter = records.values().iterator()
      while (iter.hasNext) {
        val record = iter.next().records()
        val tmp = record.map(r => r.value())
        buffer ++= tmp
      }
      if(buffer.length >= minBashSize) {
        //Should check write result
        writer.writeFile(buffer, schema)
        consumer.commit(true)
        buffer.clear()
      }
    }
  }
}
