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
        val newChangeRemaining = changeRemaining - (denomination.value * count)
        denomination.next match {
          case None => None
          case Some (newDenomination) => {
            makeChange (newChangeRemaining, nextDrawer, Some (newDenomination)) match {
              case None => makeChange (newChangeRemaining, nextDrawer, newDenomination.next)
              case Some (nextChange) => Some (increment + nextChange)
            }
          }
        }
      }
    }
  }
}
