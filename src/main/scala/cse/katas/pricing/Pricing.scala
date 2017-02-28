package cse.katas.pricing

/**
  * Created by dnwiebe on 9/4/16.
  */

trait TaxRate {
  def apply (): Int
}

case object Food extends TaxRate {override def apply () = 0}
case object Alcohol extends TaxRate {override def apply () = 155}
case object Other extends TaxRate {override def apply () = 75}

case class Item (
  price: Int,
  taxRate: TaxRate
)

object Pricing {
  def priceItems (items: Seq[Item]): Int = {
    val rawTotal = itemsTotalPrice (items)
    val discount = rawTotal match {
      case p if p >= 100000 => 150
      case p if p >= 10000 => 100
      case _ => 0
    }
    val discountedPrices = items.map {item => Item (addPercent (item.price, -discount), item.taxRate)}
    val taxedPrices = discountedPrices.map {item => Item (addPercent (item.price, item.taxRate ()), item.taxRate)}
    itemsTotalPrice (taxedPrices)
  }

  private def addPercent (base: Int, percentTimesTen: Int): Int = {
    (base * (1000 + percentTimesTen)) / 1000
  }

  private def itemsTotalPrice (items: Seq[Item]): Int = {
    items.map {_.price}.sum
  }
}
