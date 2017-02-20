package cse.katas.pencil

import org.scalatest.path

/**
 Test-drive code to simulate an ordinary wooden graphite pencil.

 Add some of the steps listed in README.md.
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
