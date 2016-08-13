package sdn.finatra.models.schema

/**
  * Created by tiennt4 on 12/08/2016.
  */
class LocalFileSchemaLoader extends SchemaLoader{

  override def loadSchema(name: String): Schema = {
    println("I am loading schema...")
    new Schema
  }

}
