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

  check ("$99.99 worth of food", List (Item (3333, Food), Item (3333, Food), Item (3333, Food)), 9999)
  check ("$100.00 worth of food", List (Item (5000, Food), Item (5000, Food)), 9000)
  check ("$999.99 worth of food", List (Item (5000, Food), Item (5000, Food), Item (89999, Food)), 89999)
  check ("$1000.00 worth of food", List (Item (100000, Food)), 85000)
  check ("$10.00 worth of other", List (Item (1000, Other)), 1075)
  check ("$10.00 worth of alcohol", List (Item (1000, Alcohol)), 1155)
  check ("A real mishmash", List (Item (1000, Food), Item (1000, Alcohol), Item (1000, Other), Item (10000, Alcohol)), 13301)

  private def check (msg: String, merchandise: List[Item], expected: Int): Unit = {
    describe (msg) {
      describe ("priced") {
        val result = Pricing.priceItems (merchandise)

        it (f"costs ${(expected/100.0)}%1.2f") {
          assert (result === expected)
        }
      }
    }
  }
}
