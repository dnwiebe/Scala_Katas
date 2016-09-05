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
  it ("Operates correctly") {
    assert (Babysitter.calculatePay (6, 8, 9) === 20)
    assert (Babysitter.calculatePay (5, 9, 9) === 40)
    assert (Babysitter.calculatePay (9, 12, 9) === 18)
    assert (Babysitter.calculatePay (5, 12, 9) === 58)
    assert (Babysitter.calculatePay (12, 4, 9) === 32)
    assert (Babysitter.calculatePay (5, 4, 9) === 90)
  }
}
