package sdn.finatra.models.hdfs

import sdn.finatra.models.schema.Schema

/**
  * Created by tiennt4 on 12/08/2016.
  */
trait HDFSFormatWriter {

  def writeFile(records : Seq[String], schema : Schema) : Boolean

}
