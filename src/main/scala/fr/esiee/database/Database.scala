package fr.esiee.database

import scala.collection.mutable

class Database[V] {

    val database: mutable.Map[String, V] = mutable.Map.empty

    /**
     * Add or replace a user.
     */
    def save(key: String, value: V): Unit = database.update(key, value)

    /**
     * Returns Some with a user if id has been found or None.
     */
    def findFrom(key: String): Option[V] = database.get(key)

    /**
     * Delete a user. It returns Some if id existed or None.
     */
    def delete(key: String): Option[Unit] =
      database.remove(key).map(_ => ())
}
