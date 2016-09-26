package cse.katas.wordwrap

/**
  * Created by dnwiebe on 9/25/16.
  */
object WordWrap {

  def wrap (input: String, lineLength: Int): String = {
    val words: List[String] = input.split (" ").toList.flatMap {partition (_, lineLength)}
    val lines = words.foldLeft (List[String] ()) {(soFar, word) =>
      soFar match {
        case w :: ws if (w.length + word.length + 1) <= lineLength => s"$w $word" :: ws
        case _ => word :: soFar
      }
    }
    lines.reverse.mkString ("\n")
  }

  private def partition (string: String, size: Int): List[String] = {
    if (string.length <= size) {
      List (string)
    }
    else {
      string.substring (0, size) :: partition (string.substring (size), size)
    }
  }
}
