package cse.katas.translator

/**
  * Created by dnwiebe on 8/30/17.
  */

object Translator {
  val twoNones: List[Option[Char]] = None :: None :: Nil
}

class Translator {
  import Translator._

  def translate (camelCase: String): String = {
    if (camelCase.isEmpty ()) {return ""}
    val adjusted = camelCase.head.toUpper + camelCase.tail + ' '
    val withEdgeEffects = adjusted.foldLeft (('a', 'a', "")) {(soFar, c) =>
      val (a, b, snakeCase) = soFar
      (a.isLower, b.isLower, c.isLower) match {
        case (false, false, false) => withB (snakeCase, b, c)
        case (_, true, _) => withB (snakeCase, b, c)
        case (_, false, _) => with_B (snakeCase, b, c)
      }
    }._3
    withEdgeEffects.drop (2)
  }

  private def withB (snakeCase: String, b: Char, c: Char): (Char, Char, String) = {
    (b, c, snakeCase + b.toLower)
  }

  private def with_B (snakeCase: String, b: Char, c: Char): (Char, Char, String) = {
    (b, c, snakeCase + '_' + b.toLower)
  }
}
