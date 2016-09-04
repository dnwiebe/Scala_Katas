package cse.katas.closest

/**
  * Created by dnwiebe on 9/4/16.
  */
object Closest {

  val closestToZero: List[Int] => Option[Int] = numbers => {
    val negatives = numbers.filter {n => n < 0}
    val positives = numbers.filter {n => n >= 0}
    val minPositive = if (positives.nonEmpty) {Some (positives.min)} else {None}
    val maxNegative = if (negatives.nonEmpty) {Some (negatives.max)} else {None}
    (minPositive, maxNegative) match {
      case (None, None) => None
      case (p, None) => p
      case (None, n) => n
      case (Some (p), Some (n)) => if (p < -n) {Some (p)} else {Some (n)}
    }
  }
}
