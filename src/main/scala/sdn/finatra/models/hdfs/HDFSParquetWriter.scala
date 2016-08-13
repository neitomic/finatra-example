package sdn.finatra.models.hdfs
import sdn.finatra.models.schema.Schema

/**
  * Created by tiennt4 on 12/08/2016.
  */
class HDFSParquetWriter extends HDFSFormatWriter{
  override def writeFile(records: Seq[String], schema: Schema): Boolean = {
    println("I am writing...")
    true
  }
}
