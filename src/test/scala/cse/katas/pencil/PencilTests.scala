package cse.katas.pencil

import org.scalatest.path

/**
 Test-drive code to simulate an ordinary wooden graphite pencil.

 Add some of the steps listed in README.md.
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
          assert (result === "our fathers                                ")
        }

        describe ("and then sharpened") {
          subject.sharpen ()

          describe ("and directed to write more") {
            val result = subject.write ("brought forth on this continent a new nation")

            it ("writes 40 more characters and stops") {
              assert (result === "brought forth on this continent a new na    ")
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

    describe ("directed to write a string longer than 40 characters") {
      val result = subject.write ("Fourscore and seven years ago our fathers brought forth on this continent")

      it ("only writes part of it") {
        assert (result === "Fourscore and seven years ago our father                                 ")
      }
    }
  }
}
