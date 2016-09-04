package cse.katas.triangles

import org.scalatest.path

/**
  * Created by dnwiebe on 9/4/16.
  */
class TrianglesTests extends path.FunSpec {

  inOneOrder ("Three equal segments", 5, 5, 5, Equilateral)

  describe ("Two equal segments and one unequal one") {
    inAllOrders (5, 5, 4, Isosceles)
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
