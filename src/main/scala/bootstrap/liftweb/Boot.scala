package bootstrap.liftweb

import scala.runtime.RichLong

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import code.lib.UserThing
import code.snippet.Holder


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def displaySometimes: Boolean = (millis / 1000L / 60L) % 2 == 0

  def isEven(n: Long) = (n % 2 == 0)

  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    def sitemap(): SiteMap = SiteMap(

      Menu.i("Home") / "index", UserThing.menu,
      Menu.i("foo") / "foo" >> If(displaySometimes _, S ? "Can't see that page now"),
      Menu.i("info") / "info" / "index" submenus(
        Menu.i("about us") / "info" / "about" >> Hidden >> LocGroup("bottom"),
        Menu.i("Contact") / "contact" >> If(User.loggedIn _, "Not logged in"),
        Menu.i("Feedback") / "feedback" >> LocGroup("bottom")
        ),
      Menu.param[Holder]("Long", "Long", s => Helpers.asLong(s).filter(isEven).map(n => Holder(n)), lng => lng.toString) / "long",
      Menu("page_name", S ? "Display") / "path",


      Menu.i("Static") / "static" / **


    )

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap())

    // Use jQuery 1.4
    //LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
  }
}

object User {
  def loggedIn : Boolean = true
}