package fr.esiee.database

import scala.collection.mutable
import fr.esiee.simplewebserver.WebParameter


class Database {

    //val database: mutable.Map[String, User] = mutable.Map.empty
    val database: mutable.Map[String, User] = mutable.Map(
        "1" -> User("1", "Mel", 21),
        "2" -> User("2", "Isa", 22),
        "3" -> User("3", "Axel", 21),
        "4" -> User("4", "Samy", 24)
    )

    /**
     * Add or replace a user.
     */
    def save(key: String, value: User): Unit = database.update(key, value)

    /**
     * Returns Some with a user if id has been found or None.
     */
    def findFrom(id: String): Option[User] = {
        println(s"in find from user with id : $id")
        database.get(id)
    }

    /**
     * Delete a user. It returns Some if id existed or None.
     */
    def delete(id: String): Option[Unit] = {
        println(s"deleting user with id : $id")
        database.remove(id).map(_ => ())
    }

    def getUsers: Seq[User] = {
        println(s"getting all the users : ")
        database.values.toSeq
    }

    def findFromParameters(parameters: Seq[WebParameter]): Seq[User] = {
        parameters.flatMap { parameter =>
            (parameter.key match {
                case "age" => database.filter(user => user._2.age  == parameter.value.toInt)
                case "name" => database.filter(user => user._2.name == parameter.value)
                case "id" => database.filter(user => user._2.id == parameter.value)
                case _ => Map.empty[String, User]
            }).values
        }
    }
}
