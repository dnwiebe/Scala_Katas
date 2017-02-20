package cse.katas.pencil

/**
  * Created by dnwiebe on 2/20/17.
  */
class Pencil (val lifespan: Int, val sharpenings: Int) {

  private var pointsRemaining = 0
  private var sharpeningsRemaining = sharpenings + 1

  sharpen ()

  def write (input: String): String = {
    val (newRemaining, output) = input.foldLeft ((pointsRemaining, "")) {(soFar, c) =>
      soFar match {
        case (remaining, output) if remaining <= 0 => (remaining, output + " ")
        case (remaining, output) if Character.isWhitespace (c) => (remaining, output + c)
        case (remaining, output) => (remaining - 100, output + c)
      }
    }
    pointsRemaining = newRemaining;
    output
  }

  def sharpen (): Unit = {
    if (sharpeningsRemaining > 0) {
      pointsRemaining = lifespan * 100
      sharpeningsRemaining -= 1;
    }
  }
}
