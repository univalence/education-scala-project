val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "My beautiful project", // TODO: name your project
    version := "0.1.0-SNAPSHOT",
    developers := List( // TODO: replace the following developer by your team developers
      Developer(
        id = "flightmodee",
        name = "ABDOUCHE Samy",
        email = "samy.abdouche@edu.esiee.fr",
        url = url("https://github.com/flightmodee")
      ),
      Developer(
      id = "cai-i",
      name = "CAI Isabelle",
      email = "isabelle.cai@edu.esiee.fr",
      url = url("https://github.com/cai-i")
      ),
      Developer(
      id = "axelcoch",
      name = "COCHET Axel",
      email = "axel.cochet@edu-esiee.fr",
      url = url("https://github.com/axelcoch")
      ),
      Developer(
      id = "MelineDang",
      name = "DANG Méline",
      email = "meline.dang@hotmail.fr",
      url = url("https://github.com/MelineDang")
    )
    ),
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq("org.scalameta" %% "munit" % "0.7.29" % Test)
  )
