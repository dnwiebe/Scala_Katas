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
    val drawer = WadOfCash.builder
      .add (Twenty, 5)
      .add (Ten, 5)
      .add (Five, 5)
      .add (Single, 5)
      .add (Quarter, 5)
      .add (Dime, 5)
      .add (Nickel, 5)
      .add (Penny, 5)
      .build
    val subject = CashRegister (drawer)

    describe ("used for a successful transaction") {
      val result = subject.transact (2600, WadOfCash.builder.add (Twenty, 2).build)

      it ("completes the transaction correctly") {
        assert (result === Result (Success, CashRegister (WadOfCash.builder
          .add (Twenty, 7)
          .add (Ten, 4)
          .add (Five, 5)
          .add (Single, 1)
          .add (Quarter, 5)
          .add (Dime, 5)
          .add (Nickel, 5)
          .add (Penny, 5)
          .build
        )))
      }
    }

    describe ("used for a successful transaction that requires part of the payment to be returned") {
      val result = subject.transact (2600, WadOfCash.builder.add (Twenty, 10).build)

      it ("completes the transaction correctly") {
        assert (result === Result (Success, CashRegister (WadOfCash.builder
          .add (Twenty, 7)
          .add (Ten, 4)
          .add (Five, 5)
          .add (Single, 1)
          .add (Quarter, 5)
          .add (Dime, 5)
          .add (Nickel, 5)
          .add (Penny, 5)
          .build
        )))
      }
    }

    describe ("used for a non-sufficient-funds transaction") {
      val result = subject.transact (2600, WadOfCash.builder
        .add (Twenty, 1)
        .add (Five, 1)
        .add (Quarter, 3)
        .add (Dime, 2)
        .add (Penny, 4)
        .build
      )

      it ("completes the transaction correctly") {
        assert (result === Result (Insufficient, CashRegister (drawer)))
      }
    }
  }

  describe ("A skinny cash register") {
    val skinnyWad = WadOfCash.builder
      .add (Twenty, 1)
      .add (Ten, 1)
      .add (Five, 1)
      .add (Single, 1)
      .add (Quarter, 1)
      .add (Dime, 1)
      .add (Nickel, 1)
      .add (Penny, 1)
      .build
    val subject = CashRegister (skinnyWad)

    describe ("used for a transaction requiring change") {
      val result = subject.transact (2600, WadOfCash.builder.add (Twenty, 2).build)

      it ("cannot complete the transaction") {
        assert (result === Result (NoChange, CashRegister (skinnyWad)))
      }
    }
  }

  describe ("An empty cash register") {
    val subject = CashRegister (WadOfCash.builder.build)

    describe ("used for a transaction requiring change") {
      val result = subject.transact (2600, WadOfCash.builder.add (Twenty, 2).build)

      it ("cannot complete the transaction") {
        assert (result === Result (NoChange, CashRegister (WadOfCash.builder.build)))
      }
    }
  }

  describe ("A Tracy Harms cash register") {
    val harmsWad = WadOfCash.builder
      .add (Quarter, 1)
      .add (Dime, 5)
      .build
    val subject = CashRegister (harmsWad)

    describe ("used for a transaction requiring 50c change") {
      val result = subject.transact (1950, WadOfCash.builder.add (Twenty, 1).build)

      it ("completes the transaction") {
        assert (result.situation === Success)
        assert (result.cashRegister.drawer.count (Twenty) === 1)
        assert (result.cashRegister.drawer.count (Quarter) === 1)
        assert (result.cashRegister.drawer.value === 2025)
      }
    }
  }
}
