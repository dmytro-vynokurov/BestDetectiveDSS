package detective

import dss.Fact._
import dss.Rule._
import dss.Rule

/**
 * User: Dmytro Vynokurov
 * Date: 12/15/13
 * Time: 10:41 AM
 */
object Data {
  var rules: List[Rule] =
    (("DETECTIVE" ==> "SMART") :: ("DETECTIVE" ==> "BRITAIN") |= ("HIRE" ==> "HOLMES")) ::
      Nil
}
