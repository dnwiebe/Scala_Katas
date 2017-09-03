package cse.katas.closest

/**
  * Created by dnwiebe on 9/4/16.
  */
object Closest {

  def closestToZero (numbers: List[Int]): Option[Int] = {
    (findExtremeFiltered (numbers, _ >= 0, _.min), findExtremeFiltered (numbers, _ < 0, _.max)) match {
      case (None, None) => None
      case (p, None) => p
      case (None, n) => n
      case (Some (p), Some (n)) => if (-n < p) {Some (n)} else {Some (p)}
    }
  }

  private def findExtremeFiltered (numbers: List[Int], discriminator: Int => Boolean,
                                   extreminator: List[Int] => Int): Option[Int] = {
    numbers.filter (discriminator) match {
      case Nil => None
      case filtered => Some (extreminator (filtered))
    }
  }
}
