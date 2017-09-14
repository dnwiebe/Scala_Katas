package cse.katas.cashregister

import org.scalatest.path

/**
A small shop has a standard cash register, with a drawer containing
various numbers of various denominations of bills and coins.  A
customer arrives at the checkout counter with a shopping cart
containing merchandise of a particular value and hands the cashier
a cash payment, also consisting of various numbers of various
denominations of bills and coins.

There are three possibilities.

  * The customer does not offer enough money for the merchandise.  In
this case the money is returned to the customer and the contents of
the cash register is unchanged.

  * The customer offers more than enough money, but the contents of
the cash register cannot be used to make change.  In this case the
money is also returned to the customer and the contents of the cash
register is unchanged.

  * The customer offers enough or more than enough money for the
merchandise, and the cashier is able to make change from the contents
of the cash register.  In this case the customer's payment is added
to the cash register, and the change is removed from it.

Test-drive some code that will accept a populated cash register,
a number representing the value of a cart of merchandise, and a
data structure representing a cash payment; will attempt to
perform the indicated transaction; and will provide results
including which of the three possibilities obtained and the contents
of the cash register after the transaction.

Note: Make sure your algorithm can handle the situation where fifty
cents of change needs to be made and the cash register contains only
one quarter and five dimes. [credit: Tracy Harms]
  */

class CashRegisterTests extends path.FunSpec {

  describe ("A populated cash register") {
    val subject = CashRegister (twenties = 1, singles = 10)

    describe ("used for a transaction") {
      val result = subject.transact (value = 2600, twenties = 2, singles = 0)

      it ("completes the transaction correctly") {
        assert (result === Result (situation = Success, CashRegister (twenties = 3, singles = 6)))
      }
    }
  }
}
