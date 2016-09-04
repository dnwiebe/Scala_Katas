package cse.katas.babysitter

import org.scalatest.path

/**
  * Created by dnwiebe on 9/4/16.
  */
class BabysitterTests extends path.FunSpec {
  describe ("Two hours before bedtime") {
    val result = Babysitter.calculatePay (6, 8, 9)

    it ("costs $20") {
      assert (result === 20)
    }
  }
}
