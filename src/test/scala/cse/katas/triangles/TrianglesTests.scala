package cse.katas.triangles

import org.scalatest.path

/**
  Using TDD, write a method or function that accepts three numbers as the lengths of three line segments.
  Determine computationally which of the following cases are true:
    The three segments
      (a) Can be joined into an equilateral triangle. (example: 3, 3, 3)
      (b) Can be joined into an isosceles triangle. (example: 5, 5, 3);
      (c) Can be joined into a right triangle. (example: 3, 4, 5);
      (d) Can only be joined into a triangle that is not one of the preceding cases. (example: 2, 3, 4)
      (e) Cannot be made into a triangle. (example: 2, 2, 5) [Warning: might look isosceles at first glance.]
  */

class TrianglesTests extends path.FunSpec {

  inOneOrder ("Three equal segments", 5, 5, 5, Equilateral)

  describe ("Two equal segments and one unequal one") {
    inAllOrders (5, 5, 4, Isosceles)
  }

  describe ("Two segments whose squares add to the square of the third") {
    inAllOrders (6, 8, 10, Right)
  }

  describe ("Three segments that don't fit any classification except that they make a triangle") {
    inAllOrders (10, 11, 12, Other)
  }

  describe ("One segment that's equal to the sum of the other two") {
    inAllOrders (7, 5, 12, NotATriangle)
  }

  describe ("Three segments that appear isosceles except that they're actually not a triangle") {
    inAllOrders (4, 4, 8, NotATriangle)
  }

  private def inOneOrder (msg: String, a: Int, b: Int, c: Int, expected: Classification): Unit = {
    describe (s"$msg ($a, $b, $c)") {
      val result = Triangles.classifySegments (a, b, c)

      it (s"classify as ${expected.getClass.getSimpleName.reverse.substring (1).reverse}") {
        assert (result === expected)
      }
    }
  }

  private def inAllOrders (a: Int, b: Int, c: Int, expected: Classification): Unit = {
    inOneOrder ("in specified order", a, b, c, expected)
    inOneOrder ("rotated once", b, c, a, expected)
    inOneOrder ("rotated twice", c, a, b, expected)
    inOneOrder ("reversed", c, b, a, expected)
    inOneOrder ("reversed and rotated once", b, a, c, expected)
    inOneOrder ("reversed and rotated twice", a, c, b, expected)
  }
}
