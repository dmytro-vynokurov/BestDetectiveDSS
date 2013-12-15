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
  var facts =
    ("HOLMES" o "SMART") ::
    ("HOLMES" o "OLD") ::
    ("HOLMES" o "HASASSISTANT") ::
    ("POIROT" o "SMART") ::
    Nil

  var rules:List[Rule] =
    (("HOLMES" o "SMART")::("HOLMES" o "BRITAIN") |= ("HIRE" o "HOLMES")) ::
    Nil

}
