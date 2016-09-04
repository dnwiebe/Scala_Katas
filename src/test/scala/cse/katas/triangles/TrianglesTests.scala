package cse.katas.triangles

import org.scalatest.path

/**
  * Created by dnwiebe on 9/4/16.
  */
class TrianglesTests extends path.FunSpec {
  describe ("Three equal segments") {
    val result = Triangles.classifySegments (5, 5, 5)

    it ("make an equilateral triangle") {
      assert (result === Equilateral)
    }
  }
}
