package cse.katas.triangles

/**
  * Created by dnwiebe on 9/4/16.
  */

trait Classification
case object Equilateral extends Classification
case object Isosceles extends Classification
case object Right extends Classification
case object Other extends Classification
case object NotATriangle extends Classification

object Triangles {
  def classifySegments (a: Int, b: Int, c: Int): Classification = {
    NotATriangle
  }
}
