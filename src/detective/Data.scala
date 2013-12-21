package detective

import dss.Util._

/**
 * User: Dmytro Vynokurov
 * Date: 12/15/13
 * Time: 10:41 AM
 */
object Data {
  var rules =
    for{line<-
     """if DETECTIVE is SMART and DETECTIVE is from BRITAIN then you should HIRE HOLMES
        if DETECTIVE is MAN and DETECTIVE is from BELGIUM then you should HIRE POIROT"""
       .split('\n').toList
    }yield createRuleOfPhrase(line)
}