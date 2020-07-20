package patterns

object AdvancedPAtternMatching  extends App {

  val numbers=List(1)

  val description= numbers match {
      // This is called :: Infix Pattern
    case head ::  Nil => println(s"the only element is head $head")

  }
 // to understand above pattren match we need to understand the below fillowing mentioned code

  class Person(val name:String, val age:Int)
  // as this is not case class so it will not vailable for pattren match
  object Person{

    def unapply(person:Person): Option[(String,Int)]= Some((person.name,person.age))

    def unapply(age: Int): Option[String] = Some(if (age <21) "minor" else "major")
  }
  val bob= new Person("bob",33)

  val greeting: String = bob match {
    case Person(name,age) => s"Hi my name is $name and my age is $age"
  }

  val legalStatus: String = bob.age match{
    case Person(status) =>s"My legal status is $status"

  }
  println(greeting)
  println(legalStatus)


  //Exercise onPattern MAtching

//if we have to match pattern on condition basis
  // then we have to do with custom Singleton object rather than
  /*
  using conditional  statements in match condtions
   */
  val n:Int=45
  // This is not the correct way to pattern match the
  val matchpattern: String = n match {
    case x if x<10 => s"$x is single Digit"
    case x if x%2==0 => s"$x is even number"
    case _ => "no prpoery"
  }
  
  object even{
    def unapply(arg: Int): Option[Boolean] = if (arg%2==0) Some(true) else None
  }

  object singledigit{
    def unapply(arg: Int): Option[Boolean] = if (arg > -10 && arg<10 ) Some(true) else None
  }

  val matchpattern1: String = n match {
    case singledigit(_) => s" is single Digit"
    case even(_) => s" is even number"
    case _ => "no prpoery"
  }
  println(matchpattern)
  println(matchpattern1)
}
