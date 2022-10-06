val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Parser combinator", // TODO: name your project
    version := "0.1.0-SNAPSHOT",
    developers := List( // TODO: replace the following developer by your team developers
      Developer(
        id    = "ChristopheTA",
        name  = "Christophe TA",
        email = "christophe.ta@outlook.fr",
        url   = url("https://github.com/ChristopheTA")
        ),
      Developer(
        id    = "john",
        name  = "doe",
        email = "johndoe@gmail.com",
        url   = url("https://github.com/johndoe")
        ),
      Developer(
        id    = "theovanrooij",
        name  = "Théo Van Rooij",
        email = "theo.vanrooij@edu.esiee.fr",
        url   = url("https://github.com/theovanrooij")
      ),
      Developer(
        id    = "Thomas",
        name  = "Jaillon",
        email = "thomas.jaillon@edu.esiee.fr",
        url   = url("https://github.com/jaillont")
      )

    ),
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test)
  )
