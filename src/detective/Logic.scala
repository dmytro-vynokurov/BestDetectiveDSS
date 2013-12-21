package detective

import dss.Rule
import dss.StraightFinder._
import dss.Util._
/**
 * User: Dmytro Vynokurov
 * Date: 12/15/13
 * Time: 2:37 PM
 */
object Logic {
  def findDetective(requirementsText:String) = {
    val requirements:List[String] = requirementsText.split('\n').toList
    val facts = for(requirement<-requirements)yield createFactOfPhrase(requirement) 
    straight(facts, Data.rules, "HIRE") match {
      case Some(fact) => s"You should hire ${fact.value}"
      case None => "Add more details about detective"
    }
  }

  def addRule(text:String):Unit = Data.rules = createRuleOfPhrase(text) :: Data.rules

  def removeRule(rule: Rule):Unit = Data.rules = Data.rules.filter(_!=rule)
  
}
