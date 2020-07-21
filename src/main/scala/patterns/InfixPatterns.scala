package patterns

object InfixPatterns  extends App {

  case class Or[A,B](a:A,b:B)

  val either=Or(2,"two")
  val matchPattern= either match {
    case Or(number,string) => s" Number $number is Written As $string"

  }
// both are same but this is called Infix Pattern in Pattern matching
 val matchPattern1= either match {
    case number Or string => s" Number $number is Written As $string"

  }
  println(matchPattern)
  println(matchPattern1)

  // Decomposing Sequences using Pattern Match
  //1: vararg Pattern
  val numbers=List(1,2,3,4)
  val vararg= numbers match {
      //Here standard technique for unapply a list will not work here bcz we don't know about the arguments
      // Hence this will not be successful here
    case List(1,_*) => s"starting with 1"
  }

// Now we need a new technique which is called unapply a seq
  abstract class MyList[+A]{
  def head: A= ???
  def tail:MyList[A] = ???
}
  case object Empty extends MyList[Nothing]
  case class Node[+A](override val head:A, override val tail:MyList[A]) extends MyList[A]
  object MyList{
    def unapplySeq[A](list: MyList[A]): Option[Seq[A]] =
      if (list == Empty) Some(Seq.empty)
        // this is good example that we can loop through the seq via recursion and transform the
        // output of recursion using map

      else unapplySeq(list.tail).map(seq=>list.head +: seq)
  }

}
