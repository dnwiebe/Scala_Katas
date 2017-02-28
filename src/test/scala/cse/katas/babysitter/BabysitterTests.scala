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

  check (6, 8, 9, 20)
  check (5, 9, 9, 40)
  check (9, 12, 9, 18)
  check (5, 12, 9, 58)
  check (12, 4, 9, 32)
  check (5, 4, 9, 90)
  
  private def check (arrival: Int, departure: Int, bedtime: Int, expectedPay: Int): Unit = {
    describe (s"A babysitter who arrives at ${time(arrival)} and departs at ${time(departure)}, with bedtime at ${time(bedtime)}") {
      val result = Babysitter.calculatePay (arrival, departure, bedtime);
      
      it (s"should be paid $$$expectedPay") {
        assert (result === expectedPay)
      }
    }
  }
  
  private def time (integralTime: Int): String = {
    integralTime match {
      case t if (t < 5) || (t == 12) => s"${t}am"
      case t => s"${t}pm"
    }
  }
}
