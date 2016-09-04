package cse.katas.closest

/**
  * Created by dnwiebe on 9/4/16.
  */
object Closest {

  val closestToZero: List[Int] => Option[Int] = numbers => {
    if (numbers.isEmpty) {None}
    else {
      Some (numbers.min)
    }
  }
}
