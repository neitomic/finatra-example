package sdn.finatra.modules

import com.google.inject.AbstractModule
import com.google.inject.assistedinject.FactoryModuleBuilder
import sdn.finatra.models.hdfs.{HDFSFormatWriter, HDFSParquetWriter}
import sdn.finatra.models.kafka.{KafkaConsumerFactory, KafkaHadoopConsumer}
import sdn.finatra.models.schema.{LocalFileSchemaLoader, SchemaLoader}

/**
  * Created by tiennt4 on 13/08/2016.
  */
class KafkaConsumerModule extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[HDFSFormatWriter]).to(classOf[HDFSParquetWriter])
    bind(classOf[SchemaLoader]).to(classOf[LocalFileSchemaLoader])
    install(new FactoryModuleBuilder()
      .build(classOf[KafkaConsumerFactory] ))
  }
}
