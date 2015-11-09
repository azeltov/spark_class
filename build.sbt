name := "WordReceiver"

version := "1.0"

scalaVersion := "2.10.5"

// Change this to another test framework if you prefer
  libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core"      % "1.4.0" % "provided",
  "org.apache.spark"  %% "spark-sql"       % "1.4.0" % "provided",
  "org.apache.spark"  %% "spark-streaming" % "1.4.0" % "provided",
  "org.scalatest"     %% "scalatest"       % "2.2.4" % "test"
)