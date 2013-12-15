package dss

/**
 * User: Dmytro Vynokurov
 * Date: 12/14/13
 * Time: 9:23 PM
 */
object Util {
  def removeSmallLetters(phrase:String):String = for{symbol<-phrase;if !(symbol.toInt >= 97 && symbol.toInt <= 122)}yield symbol
  def getCapitalCaseWords(phrase:String):List[String] = removeSmallLetters(phrase).split("\\W").toList.filter(!_.isEmpty)
  def createFactOfPhrase(phrase:String):Fact = {
    val words = getCapitalCaseWords(phrase)
    if(words.length!=2) throw new LogicParseException("""Cannot parse phrase """" + phrase + """" into fact""")
    Fact(words.head,words.tail.head)
  }
  def createRuleOfPhrase(phrase:String):Rule = {
    var words = getCapitalCaseWords(phrase)
    var leftSide:List[Fact] = Nil
    if(words.length%2!=0 || words.length<=4)throw new LogicParseException( """Cannot parse phrase """" + phrase + """" into rule""")
    while(words.length>=4){
      leftSide = Fact(words.head,words.tail.head) :: leftSide
      words = words.tail.tail
    }
    val rightSide = Fact(words.head,words.tail.head)
    Rule(leftSide,rightSide)
  }
}
case class LogicParseException(exception:String) extends RuntimeException(exception)
