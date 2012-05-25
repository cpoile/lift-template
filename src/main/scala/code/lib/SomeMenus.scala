package code.lib

import net.liftweb._
import common.{Full, Empty, Box}
import http.SessionVar
import sitemap._
import Loc._

object UserThing {
  object currentUserId extends SessionVar[Box[Long]](Empty)

  lazy val menu = Menu.i("User") / "user" >> If(loggedIn _, "not logged in")

  def loggedIn = currentUserId.isDefined
}