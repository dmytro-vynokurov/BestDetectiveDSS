package dss

/**
 * User: Dmytro Vynokurov
 * Date: 10/22/13
 * Time: 10:40 AM
 */
case class Fact(name:String, value:String){
  override def toString: String = s"$name=$value"
}
object Fact{
  implicit def String2FactName(s:String):FactName = new FactName(s)
}

class FactName(val name:String){
  def ==>(value:String):Fact = Fact(name,value)
}

object Runner{
  def main(args: Array[String]) {
    import Rule._
    import Fact._
    import StraightFinder._
    import ReverseFinder._

    val f1 = String2FactName("Marina").==>("girl")
    val f2 = "Marina" ==> "nice"
    val f3 = "I like" ==> "Marina"
    val r1 = f1 :: f2 |= f3
    val f4 = Fact("Marina","girl")

    println(straight(f1 :: f2, r1 :: Nil,"I like"))
    println(reverse(f1 :: f2,r1::Nil,f3))
    println(f4==f1)
  }
}