package cse.katas.closest

/**
  * Created by dnwiebe on 9/4/16.
  */
object Closest {

  val closestToZero: List[Int] => Option[Int] = numbers => {
    if (numbers.isEmpty) {None}
    else {
      val negatives = numbers.filter {n => n < 0}
      val positives = numbers.filter {n => n >= 0}
      if (positives.nonEmpty) {Some (positives.min)}
      else {Some (negatives.max)}
    }
  }
}
