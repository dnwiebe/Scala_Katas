package cse.katas.triangles

/**
  * Created by dnwiebe on 9/4/16.
  */

private object Classification {
  private val SEQUENCES = List ((0, 1, 2), (1, 2, 0), (2, 0, 1), (2, 1, 0), (1, 0, 2), (0, 2, 1))
}

trait Classification {
  import cse.katas.triangles.Classification._

  def qualifies (a: Int, b: Int, c: Int): Boolean

  protected def rotate (a: Int, b: Int, c: Int, predicate: (Int, Int, Int) => Boolean): Boolean = {
    val argArray = Array (a, b, c)
    SEQUENCES.exists {sequence => predicate (argArray (sequence._1), argArray (sequence._2), argArray (sequence._3))}
  }
}

case object Equilateral extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = (a == b) && (b == c)
}
case object NotATriangle extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = rotate (a, b, c, {(a, b, c) => (a + b) < c})
}
case object Isosceles extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = rotate (a, b, c, {(a, b, _) => a == b})
}
case object Right extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = rotate (a, b, c, {(a, b, c) => (a*a) + (b*b) == (c*c)})
}
case object Other extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = true
}

object Triangles {
  private val CLASSIFICATION_SEQUENCE = List (Equilateral, NotATriangle, Isosceles, Right, Other)

  def classifySegments (a: Int, b: Int, c: Int): Classification = {
    CLASSIFICATION_SEQUENCE.find {triangleType => triangleType.qualifies (a, b, c)}.get
  }
}
