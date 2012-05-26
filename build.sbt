organization := "Lift"

name := "Lift-Template"

version := "0.1"

scalaVersion := "2.9.1"

seq(webSettings :_*)

// for less-sbt
seq(lessSettings:_*)

//run with ~container:start using a context path localhost:8080/test to make sure noting breaks 
//env in Compile :=  Some(file(".") / "jetty-env.xml" asFile) 
 
// If using JRebel
scanDirectories in Compile := Nil

logLevel := Level.Info
//Level.Info.Debug

(resourceManaged in (Compile, LessKeys.less)) <<= baseDirectory(_ /"src" / "main" / "webapp" / "css")

(LessKeys.filter in (Compile, LessKeys.less)) := "bootstrap.less"

// I don't know how to make these three more compact...
resolvers += "Sonatype OSS Snapshot Repository" at "https://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "Sonatype scala-tools repo" at "https://oss.sonatype.org/content/groups/scala-tools/"

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

transitiveClassifiers := Seq("sources","javadocs")

libraryDependencies ++= {
  val liftVersion = "2.5-SNAPSHOT" // Put the current/latest lift version here
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default"
//    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
//    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
//    "net.liftweb" %% "lift-testkit" % liftVersion % "compile->default"
    )
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  "org.mortbay.jetty" % "jetty" % "6.1.26" % "test, container",
  //"org.eclipse.jetty" % "jetty-webapp" % "8.1.0.v20120127" % "test, container",
  "ch.qos.logback" % "logback-classic" % "1.0.3" % "compile->default",
  "junit" % "junit" % "4.10" % "test->default", 
  "org.specs2" %% "specs2" % "1.9" % "test"
//  "com.h2database" % "h2" % "1.2.147"
//  "org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
//  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
//  "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "compile->default", // Logging
//  "commons-lang" % "commons-lang" % "2.0" % "compile->default",
//  "com.jolbox" % "bonecp" % "0.7.1.RELEASE" % "compile->default"  
)