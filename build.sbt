organization := "com.versiontoday"
name := "image-recognition"
scalaVersion := "2.12.1"

scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-feature",
  "-language:_",
  "-unchecked",
  "-Ydelambdafy:method",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-target:jvm-1.8"
)

resolvers := Seq(
  "Twitter" at "http://maven.twttr.com",
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases")
)

libraryDependencies := Seq(
  "org.scalatest" %% "scalatest" % "3.0.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.6",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3",
  "com.google.cloud" % "google-cloud-vision" % "0.9.0-alpha"
)