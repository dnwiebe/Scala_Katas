package cse.katas.pencil

import org.scalatest.path

/**

  Test-drive code to simulate an ordinary wooden graphite pencil. Run the
  following steps:

  1. When the pencil is instructed to write a string, it returns exactly
  the string it was instructed to write.

  2. Add simulation of a dulling point.  After writing a certain number
  of characters, the simulated pencil goes dull and begins to return
  spaces instead of the characters it's instructed to write.

  3. Add sharpening capability. When a pencil is sharpened, it regains
  full sharpness; but it can only be sharpened a certain number of times.
  When sharpened past the limit, it no longer regains any sharpness.

  4. It requires no lead to write a space (or a tab or a carriage return
  or a newline).  Make the pencil capable of writing an arbitrary amount
  of whitespace without going any duller.

  5. It requires more lead to write a capital M than it does to write
  a period.  Come up with a reasonable classification of the writeable
  characters so that the pencil can write more periods than capital Ms
  before going dull.

*/

class PencilTests extends path.FunSpec {

  describe ("A Pencil") {
    val subject = new Pencil ()

    describe ("directed to write a string") {
      val result = subject.write ("Fourscore and seven years ago");

      it ("does so") {
        assert (result === "Fourscore and seven years ago")
      }
    }
  }
}
