package cse.katas.closest

/**
  * Created by dnwiebe on 9/4/16.
  */
object Closest {

  private val findExtreme = (numbers: List[Int], discriminator: Int => Boolean, extreminator: List[Int] => Int) => {
    numbers.filter (discriminator) match {
      case Nil => None
      case filtered => Some (extreminator (filtered))
    }
  }

  private val findMinPositive: List[Int] => Option[Int] = numbers => {
    findExtreme (numbers, {n => n >= 0}, {x => x.min})
  }

  private val findMinNegative: List[Int] => Option[Int] = numbers => {
    findExtreme (numbers, {n => n < 0}, {x => x.max})
  }

  val closestToZero: List[Int] => Option[Int] = numbers => {
    (findMinPositive (numbers), findMinNegative (numbers)) match {
      case (None, None) => None
      case (p, None) => p
      case (None, n) => n
      case (Some (p), Some (n)) => if (-n < p) {Some (n)} else {Some (p)}
    }
  }
}
