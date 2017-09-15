package cse.katas.cashregister

/**
  * Created by dnwiebe on 9/13/17.
  */

case class CashRegister (drawer: WadOfCash) {
  def transact (value: Int, payment: WadOfCash): Result = {
    val paymentValue = payment.value
    val changeDue = paymentValue - value
    if (changeDue < 0) {
      Result (Insufficient, this)
    }
    else {
      val newDrawer = drawer + payment
      makeChange (changeDue, newDrawer, Twenty) match {
        case Some (change) => Result (Success, CashRegister (newDrawer - change))
        case None => Result (NoChange, this)
      }
    }
  }

  private def makeChange (changeRemaining: Int, drawer: WadOfCash, denomination: Denomination): Option[WadOfCash] = {
    denomination.next match {
      case None => {
        val count = Math.min (changeRemaining, drawer.count (Penny))
        if (count < changeRemaining) {None} else {Some (WadOfCash.builder.add (Penny, count).build)}
      }
      case Some (nextDenomination) => {
        val count = Math.min (changeRemaining / denomination.value, drawer.count (denomination))
        val increment = WadOfCash.builder.add (denomination, count).build
        val nextDrawer = drawer - increment
        makeChange (changeRemaining - (denomination.value * count), nextDrawer, nextDenomination) match {
          case Some (nextChange) => Some (increment + nextChange)
          case None => None
        }
      }
    }
  }
}
