package cse.katas.pricing

import org.scalatest.path

/**
  * Created by dnwiebe on 9/4/16.
  */
class PricingTests extends path.FunSpec {

  describe ("$20.00 worth of food") {
    val merchandise = List (Item (500, Food), Item (1500, Food))

    describe ("priced") {
      val result = Pricing.priceItems (merchandise)

      it ("costs $20.00") {
        assert (result === 2000)
      }
    }
  }
}
