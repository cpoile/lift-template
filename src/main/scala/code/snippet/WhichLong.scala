package code.snippet

import net.liftweb._
import util._
import Helpers._

case class Holder(n: Long)

class WhichLong (number: Holder) {
  def render = {
    "tr *" #> (1 to number.n.toInt by 10).map(_.toString) andThen
      "#place" #> number.n
  }
}
