package fr.esiee.database

import scala.collection.mutable
import fr.esiee.simplewebserver.WebParameter

// case class User(id: String, name: String, age: Int)

class Database[V] {

    val database: mutable.Map[String, V] = mutable.Map.empty

    /**
     * Add or replace a user.
     */
    def save(key: String, value: V): Unit = database.update(key, value)

    /**
     * Returns Some with a user if id has been found or None.
     */
    def findFrom(key: String): Option[V] = {
        database.get(key)
        println(s"in find from $key")
    }

    /**
     * Delete a user. It returns Some if id existed or None.
     */
    def delete(key: String): Option[Unit] =
      database.remove(key).map(_ => ())

    def getUsers: Seq[V] = database.values.toSeq


    // à définir
    /**def findFromParameters(parameters: Seq[WebParameter]): Seq[V] = {
        parameters.flatMap { parameter =>
            (parameter.key match {
                case "age" =>
                case "name" =>
                case "id" =>
                case _ => Map.empty[String, V]
            }).values
        }
    */
}
