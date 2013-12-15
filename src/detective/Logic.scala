package detective

import dss.Fact
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
    straight(facts ::: Data.facts, Data.rules, "HIRE") match {
      case Some(fact) => s"You should hire ${fact.value}"
      case None => "Add more details about detective"
    }
  }

  def addFact(text:String):Unit = Data.facts = createFactOfPhrase(text) :: Data.facts
  
  def removeFact(fact: Fact):Unit = Data.facts = Data.facts.filter(_!=fact)

  def addRule(text:String):Unit = Data.facts = createFactOfPhrase(text) :: Data.facts

  def removeRule(rule: Rule):Unit = Data.rules = Data.rules.filter(_!=rule)
  
}
