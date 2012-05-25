import sbt._
import Keys._

object TemplateBuild extends Build {
	lazy val root = Project(id = "Lift-Template",
                            base = file("."),
                            settings = Project.defaultSettings)
}