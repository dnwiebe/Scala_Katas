package cse.katas.triangles

/**
  * Created by dnwiebe on 9/4/16.
  */

trait Classification {
  val qualifies: (Int, Int, Int) => Boolean

  private val ORDERS = List ((0, 1, 2), (1, 2, 0), (2, 0, 1), (2, 1, 0), (1, 0, 2), (0, 2, 1))
  protected val rotate: (Int, Int, Int, (Int, Int, Int) => Boolean) => Boolean = (a, b, c, closure) => {
    val argArray = Array (a, b, c)
    ORDERS.exists {order => closure (argArray (order._1), argArray (order._2), argArray (order._3))}
  }
}

case object Equilateral extends Classification {
  override val qualifies = (a: Int, b: Int, c: Int) => (a == b) && (b == c)
}
case object NotATriangle extends Classification {
  override val qualifies = (a: Int, b: Int, c: Int) => rotate (a, b, c, {(a, b, c) => (a + b) < c})
}
case object Isosceles extends Classification {
  override val qualifies = (a: Int, b: Int, c: Int) => rotate (a, b, c, {(a, b, c) => a == b})
}
case object Right extends Classification {
  override val qualifies = (a: Int, b: Int, c: Int) => rotate (a, b, c, {(a, b, c) => (a*a) + (b*b) == (c*c)})
}
case object Other extends Classification {
  override val qualifies = (a: Int, b: Int, c: Int) => true
}

object Triangles {
  private val CLASSIFICATION_SEQUENCE = List (Equilateral, NotATriangle, Isosceles, Right)

  val classifySegments: (Int, Int, Int) => Classification = (a, b, c) => {
    CLASSIFICATION_SEQUENCE.find {triangleType => triangleType.qualifies (a, b, c)} match {
      case Some (triangleType) => triangleType
      case None => Other
    }
  }
}
