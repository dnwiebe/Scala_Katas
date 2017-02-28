package cse.katas.wordwrap

/**
  * Created by dnwiebe on 9/25/16.
  */
object WordWrap {

  def wrap (input: String, lineLength: Int): String = {
    input match {
      case s if s.length <= lineLength => s
      case s => s.lastIndexOf (' ', lineLength) match {
        case idx if idx < 0 => s.substring (0, lineLength) + "\n" + wrap (s.substring (lineLength), lineLength)
        case idx => s.substring (0, idx) + "\n" + wrap (s.substring (idx + 1), lineLength)
      }
    }
  }
}
