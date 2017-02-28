package cse.katas.pricing

import org.scalatest.path

/**
  A store has the following pricing policy:
    1. Buy less than $100 worth of merchandise and pay the full price.
    2. Buy $100 or more, but less than $1000, and get 10% off.
    3. Buy $1000 or more, and get 15% off.

  According to the laws governing the store, the following taxes apply:
  Food: no tax.
  Alcohol: 7.5% sales tax + 8% "sin" tax.
  All other merchandise: 7.5% sales tax.

  Discounts are calculated on sticker prices; taxes are calculated on discounted prices.

  Using TDD, write a method that will accept purchased items of food, alcohol, and other, and will
  return the price the customer should be charged.
  */

class PricingTests extends path.FunSpec {

  describe ("$20.00 worth of food") {
    val result = Pricing.priceItems (List (Item (500, Food), Item (1500, Food)))

    it ("costs $20.00") {
      assert (result === 2000)
    }
  }
}
