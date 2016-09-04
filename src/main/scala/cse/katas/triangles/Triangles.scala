package cse.katas.triangles

/**
  * Created by dnwiebe on 9/4/16.
  */

trait Classification {
  def qualifies (a: Int, b: Int, c: Int): Boolean

  private val ORDERS = List ((0, 1, 2), (1, 2, 0), (2, 0, 1), (2, 1, 0), (1, 0, 2), (0, 2, 1))
  protected def rotate (a: Int, b: Int, c: Int, closure: (Int, Int, Int) => Boolean): Boolean = {
    val argArray = Array (a, b, c)
    ORDERS.exists {order => closure (argArray (order._1), argArray (order._2), argArray (order._3))}
  }
}

case object Equilateral extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = rotate (a, b, c, {(a, b, c) => (a == b) && (b == c)})
}
case object Isosceles extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = rotate (a, b, c, {(a, b, c) => a == b})
}
case object Right extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = false
}
case object Other extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = false
}
case object NotATriangle extends Classification {
  override def qualifies (a: Int, b: Int, c: Int): Boolean = false
}

object Triangles {
  private val CLASSIFICATION_SEQUENCE = List (Equilateral, Isosceles, Right, Other)

  def classifySegments (a: Int, b: Int, c: Int): Classification = {
    CLASSIFICATION_SEQUENCE.find {triangleType => triangleType.qualifies (a, b, c)} match {
      case Some (triangleType) => triangleType
      case None => NotATriangle
    }
  }
}
