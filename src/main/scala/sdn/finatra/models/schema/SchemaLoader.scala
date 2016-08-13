package sdn.finatra.models.schema

/**
  * Created by tiennt4 on 12/08/2016.
  */
trait SchemaLoader {
  def loadSchema(name : String) : Schema
}
