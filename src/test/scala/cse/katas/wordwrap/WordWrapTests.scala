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

  describe ("Given a string from a children's book and a short line length") {
    val result = WordWrap.wrap ("See Spot run. Run, Spot, run!", 10)

    it ("breaks the line in three") {
      assert (result === "See Spot\nrun. Run,\nSpot, run!")
    }
  }

  describe ("Given a word longer than two lines") {
    val result = WordWrap.wrap ("abcdefghijklmnopqrstuvwxyz", 10)

    it ("breaks the word twice") {
      assert (result === "abcdefghij\nklmnopqrst\nuvwxyz")
    }
  }
}
