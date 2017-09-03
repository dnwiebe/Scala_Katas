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

  describe ("A Pencil with a 40-character lifespan and two available sharpenings") {
    val subject = new Pencil (40, 2)

    describe ("directed to write a string less than 40 characters") {
      val result = subject.write ("Fourscore and seven years ago");

      it ("writes the whole thing") {
        assert (result === "Fourscore and seven years ago")
      }

      describe ("and then directed to write some more") {
        val result = subject.write ("our fathers brought forth on this continent")

        it ("craps out after 40 total characters") {
          assert (result === "our fathers broug                          ")
        }

        describe ("and then sharpened") {
          subject.sharpen ()

          describe ("and directed to write more") {
            val result = subject.write ("brought forth on this continent a new nation, conceived")

            it ("writes 43 more characters and stops") {
              assert (result === "brought forth on this continent a new nation, conce    ")
            }

            describe ("and sharpened again and used to write even more, then sharpened a third time") {
              subject.sharpen ()
              subject.write ("tion, conceived in liberty and dedicated to the proposition")
              subject.sharpen ()

              describe ("and used again") {
                val result = subject.write ("to the proposition that all men are created equal")

                it ("writes nothing this time") {
                  assert (result === "                                                 ")
                }
              }
            }
          }
        }
      }
    }

    describe ("directed to write a long non-dense string") {
      val result = subject.write ("``````````````````````````````````````````````````````````````````````````````" +
        "````````````````````````````````````````````````````````````````````````````````````````````````````````")

      it ("writes fewer characters, but still rather a lot") {
        assert (result ===        "``````````````````````````````````````````````````````````````````````````````" +
        "``````````````````````````````````````````````````````````````````````````````````                      ")
      }
    }

    describe ("directed to write a long dense string") {
      val result = subject.write ("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB" +
        "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB")

      it ("writes very few characters") {
        assert (result ===        "BBBBBBBBBBBBBBBBBBBBBBBB                                                      " +
        "                                                                                                        ")
      }
    }

    describe ("directed to write a string longer than 40 characters") {
      val result = subject.write ("Fourscore and seven years ago our fathers brought forth on this continent")

      it ("only writes part of it") {
        assert (result === "Fourscore and seven years ago our fathers broug                          ")
      }
    }
  }
}
