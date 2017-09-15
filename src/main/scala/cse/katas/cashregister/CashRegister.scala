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
      makeChange (changeDue, newDrawer, Some (Twenty)) match {
        case Some (change) => Result (Success, CashRegister (newDrawer - change))
        case None => Result (NoChange, this)
      }
    }
  }

  private def makeChange (changeRemaining: Int, drawer: WadOfCash, denominationOpt: Option[Denomination]): Option[WadOfCash] = {
    denominationOpt match {
      case None => None
      case Some (denomination) => {
        val count = Math.min (changeRemaining / denomination.value, drawer.count (denomination))
        val increment = WadOfCash.builder.add (denomination, count).build
        if (changeRemaining == 0) {return Some (increment)}
        val nextDrawer = drawer - increment
        makeChange (changeRemaining - (denomination.value * count), nextDrawer, denomination.next) match {
          case Some (nextChange) => Some (increment + nextChange)
          case None => None
        }
      }
    }
  }
}
