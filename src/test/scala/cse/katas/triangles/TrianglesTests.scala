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
  describe ("Three equal segments") {
    val result = Triangles.classifySegments (5, 5, 5)

    it ("make an equilateral triangle") {
      assert (result === Equilateral)
    }
  }
}
