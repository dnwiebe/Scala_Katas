package cse.katas.babysitter

import org.scalatest.path

/**
  A babysitter has the following pay schedule:
    $10/hr to look after children before their bedtime
    $6/hr to house-sit after bedtime
    $8/hr after midnight when work causes her to lose sleep.

  Write a method or function that will accept an arrival time, a departure time, and a bedtime, and return
  the babysitter's earnings for the evening in dollars.

  Represent the times as integers on a 12-hour clock.  The earliest the babysitter can arrive is 5pm;
  the latest she can stay is 4am.
  */
class BabysitterTests extends path.FunSpec {
  describe ("Two hours before bedtime") {
    val result = Babysitter.calculatePay (6, 8, 9)

    it ("costs $20") {
      assert (result === 20)
    }
  }
}
