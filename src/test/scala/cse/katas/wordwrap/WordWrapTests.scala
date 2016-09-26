package cse.katas.wordwrap

import org.scalatest.path

/**
  * Test-drive a function to accept a string and a line length, and return a version of the original string, possibly with
  *newline characters inserted, such that no line in the resulting string is longer than the supplied line length.
 **
 Wherever possible, newlines should be inserted between words; they should be placed inside words only when those
  *words are themselves longer than the permitted line length.
 **
 For the initial cut of this kata:
 *
 * Assume that the input will contain only spaces to separate words: no tabs, no newlines, no form feeds, and hyphens
  *are considered to be part of the words they're embedded in.
  * Assume that words will be separated by only one space: no multiple consecutive spaces.
*/

class WordWrapTests extends path.FunSpec {

  checkWordWrap ("", 10, "")
  checkWordWrap ("See Spot run.", 80, "See Spot run.")
  checkWordWrap ("See Spot run.", 3, "See\nSpo\nt\nrun\n.")
  checkWordWrap ("See Spot run.", 10, "See Spot\nrun.")

  private def escape (s: String): String = {
    s.foldLeft ("") {(soFar, c) => if (c == '\n') soFar + "\\n" else soFar + c}
  }

  private def countLines (s: String): Int = {
    s.foldLeft (1) {(soFar, c) => if (c == '\n') soFar + 1 else soFar}
  }

  private def checkWordWrap (input: String, lineLength: Int, expected: String): Unit = {
    describe (s"Given an input of '${escape(input)}' into a line length of ${lineLength}") {
      val actual = WordWrap.wrap (input, lineLength)
      val expectedLineCount = countLines(expected)

      it (s"produces ${expectedLineCount} line${if (expectedLineCount == 1) "" else "s"}: '${escape (expected)}'") {
        assert (actual === expected)
      }
    }
  }
}
