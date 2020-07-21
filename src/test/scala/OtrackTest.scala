object OtrackTest extends App {

  /* Nil => Nil
   Seq(0) => Seq(1)
   Seq(1, 2, 3) => Seq(1, 2, 4)
   Seq(9, 9, 9) => Seq(1, 0, 0, 0)*/

  // Q3
  def incrementByOne(s: Seq[Int]): Seq[Int] = {
    s match {
      case Nil => Nil
      case _ => incrementRec(s.reverse).reverse
    }
  }

  // Seq(3,2,1) - 4,2,1
  def incrementRec(seq: Seq[Int]): Seq[Int] = {
    seq match {
      case Nil => Seq(1)
      case head :: tail if head < 9 => Seq(head + 1) ++ tail
      case _ :: tail => Seq(0) ++ incrementRec(tail) // Seq(0) + Seq(0) + Seq(0) + Seq(1)
    }
  }

  println(incrementByOne(Nil))
  println(incrementByOne(Seq(0)))
  println(incrementByOne(Seq(1, 2, 3)))
  println(incrementByOne(Seq(9, 9, 9)))

}
