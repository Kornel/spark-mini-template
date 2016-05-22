import sbt.Keys._

name := "spark-mini"

scalaVersion := "2.10.5"

organization := "org.kornel.spark.mini"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

val sparkVersion = "1.6.0"

libraryDependencies ++= Seq(
  "com.databricks" %% "spark-avro" % "2.0.1",
  "net.ceedubs" %% "ficus" % "1.0.1",
  "com.databricks" %% "spark-avro" % "2.0.1" withSources(),
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided" withSources(),
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided" withSources(),
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided" withSources()
)

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.**" -> "shadeio.@1").inAll
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

