package cse.katas.triangles

/**
  * Created by dnwiebe on 9/4/16.
  */

trait Classification
case object NotATriangle extends Classification
case object Right extends Classification
case object Equilateral extends Classification
case object Isosceles extends Classification
case object Other extends Classification

object Triangles {
  def classifySegments (a: Int, b: Int, c: Int): Classification = {
    val sortedList = List (a, b, c).sorted
    (sortedList(0), sortedList(1), sortedList(2)) match {
      case (x, y, z) if x + y <= z => NotATriangle
      case (x, y, z) if (x * x + y * y) == z * z => Right
      case (x, y, z) if (x == y) && (y == z) => Equilateral
      case (x, y, z) if (x == y) || (y == z) => Isosceles
      case _ => Other
    }
  }
}
