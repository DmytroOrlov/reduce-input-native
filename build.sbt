lazy val `reduce-input` = project.in(file("."))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    scalaVersion := "2.11.11",
    version := "1.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % Test
  )
