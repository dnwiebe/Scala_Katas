package cse.katas.closest

import org.scalatest.path

/**
  Test-drive a method or function to accept a list of integers and return the one that is closest to zero.

  It should be an error for the list to be empty.

  If two different numbers tie for distance from zero (for example, 2 and -2), always return the positive one.
  */
class ClosestTests extends path.FunSpec {

  check ("A list containing just one value", List (-10), "produces that one value", Some (-10))
  check ("A list containing several positive values", List (5, 2, 8, 4, 7, 1), "produces the smallest positive value", Some (1))
  check ("An empty list", Nil, "produces no result", None)
  check ("A list containing several negative values", List (-5, -2, -8, -4, -7, -1), "produces the greatest negative value", Some (-1))
  check ("A list with a closer negative value", List (5, 2, 8, 4, 7, -1), "produces that negative value", Some (-1))

  private def check (inMsg: String, input: List[Int], outMsg: String, expected: Option[Int]) = {
    describe (inMsg) {
      describe ("searched for the element closest to zero") {
        val result = Closest.closestToZero (input)

        it (outMsg) {
          assert (result === expected)
        }
      }
    }
  }
}
