package cse.katas.pencil

/**
  * Created by dnwiebe on 2/20/17.
  */

object Pencil {
  val AVG_PT_PER_CHAR = 12
  val DENSITIES = Map (
    ' ' -> 0, '!' -> 5, '"' -> 6, '#' -> 20, '$' -> 17, '%' -> 13, '&' -> 15, '\'' -> 4, '(' -> 7, ')' -> 7,
    '*' -> 11, ',' -> 4, '-' -> 5, '.' -> 4, '/' -> 5, '0' -> 19, '1' -> 10, '2' -> 15, '3' -> 14, '4' -> 14,
    '5' -> 17, '6' -> 15, '7' -> 11, '8' -> 17, '9' -> 15, ':' -> 8, ';' -> 8, '<' -> 7, '=' -> 10, '>' -> 7,
    '?' -> 9, '@' -> 18, 'A' -> 18, 'B' -> 20, 'C' -> 13, 'D' -> 16, 'E' -> 18, 'F' -> 14, 'G' -> 18, 'H' -> 17,
    'I' -> 11, 'J' -> 11, 'K' -> 14, 'L' -> 11, 'M' -> 18, 'N' -> 17, 'O' -> 18, 'P' -> 15, 'Q' -> 17, 'R' -> 18,
    'S' -> 15, 'T' -> 11, 'U' -> 15, 'V' -> 13, 'W' -> 17, 'X' -> 13, 'Y' -> 11, 'Z' -> 15, '[' -> 11, '\\' -> 5,
    ']' -> 11, '^' -> 5, '_' -> 5, '`' -> 3, 'a' -> 14, 'b' -> 15, 'c' -> 10, 'd' -> 15, 'e' -> 14, 'f' -> 11,
    'g' -> 16, 'h' -> 14, 'i' -> 5, 'j' -> 9, 'k' -> 12, 'l' -> 10, 'm' -> 13, 'n' -> 12, 'o' -> 12, 'p' -> 12,
    'q' -> 12, 'r' -> 9, 's' -> 12, 't' -> 11, 'u' -> 12, 'v' -> 9, 'w' -> 12, 'x' -> 9, 'y' -> 12, 'z' -> 13,
    '{' -> 7, '|' -> 7, '}' -> 7, '~' -> 4
  )
}

class Pencil (val lifespan: Int, val sharpenings: Int) {
  import Pencil._

  private var pointsRemaining = 0
  private var sharpeningsRemaining = sharpenings + 1

  sharpen ()

  def write (input: String): String = {
    val (newRemaining, output) = input.foldLeft ((pointsRemaining, "")) {(soFar, c) =>
      val density = DENSITIES.getOrElse (c, AVG_PT_PER_CHAR)
      soFar match {
        case (remaining, output) if remaining <= 0 => (remaining, output + " ")
        case (remaining, output) => (remaining - density, output + c)
      }
    }
    pointsRemaining = newRemaining;
    output
  }

  def sharpen (): Unit = {
    if (sharpeningsRemaining > 0) {
      pointsRemaining = lifespan * AVG_PT_PER_CHAR
      sharpeningsRemaining -= 1;
    }
  }
}
