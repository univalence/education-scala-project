val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
<<<<<<< HEAD
<<<<<<< HEAD
    name := "Parser Combinator", // TODO: name your project
=======
    name := "Parser_combinator", // TODO: name your project
>>>>>>> julesA
=======
    name := "Parser_combinator", // TODO: name your project
>>>>>>> julesA
    version := "0.1.0-SNAPSHOT",
    developers := List( // TODO: replace the following developer by your team developers
      Developer(
<<<<<<< HEAD
        id    = "ptitchev",
        name  = "Jules Chevenet",
        email = "jules.chevenet@edu.esiee.fr",
        url   = url("https://github.com/ptitchev/education-scala-project")
=======
        id    = "JAndretti",
        name  = "Andretti Jules",
        email = "Jules.andretti@edu.esiee.fr",
        url   = url("https://github.com/ptitchev/education-scala-project/tree/julesA")
>>>>>>> main
      )
    ),
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test)
  )
