package cse.katas.cashregister

import org.scalatest.path

/**
  * Created by dnwiebe on 9/13/17.
  */

class DenominationTests extends path.FunSpec {
  describe ("The list of denominations") {
    val result = Denomination.values

    it ("is correct") {
      assert (result === Array (Twenty, Ten, Five, Single, Quarter, Dime, Nickel, Penny))
    }
  }

  describe ("The face values of the denominations") {
    val result = Denomination.values.map {_.value}

    it ("are correct") {
      assert (result === Array (2000, 1000, 500, 100, 25, 10, 5, 1))
    }
  }

  describe ("The next values of the denominations") {
    val pairs = Denomination.values.zip (Denomination.values.tail)

    pairs.foreach {pair =>
      it (s"show that ${pair._1} leads to ${pair._2}") {
        assert (pair._1.next === Some (pair._2))
      }
    }

    it ("show that Penny is the last denomination") {
      assert (Penny.next === None)
    }
  }
}
