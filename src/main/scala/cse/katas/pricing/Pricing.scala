package cse.katas.pricing

/**
  * Created by dnwiebe on 9/4/16.
  */

trait ItemNature {
  def taxRate: Int
}
case object Food extends ItemNature {override def taxRate = 0}
case object Alcohol extends ItemNature {override def taxRate = 155}
case object Other extends ItemNature {override def taxRate = 75}

case class Item (
  price: Int,
  nature: ItemNature
)

object Pricing {
  def priceItems (items: Seq[Item]): Int = {
    val rawTotal = itemsTotalPrice (items)
    val discount = rawTotal match {
      case p if p >= 100000 => 150
      case p if p >= 10000 => 100
      case _ => 0
    }
    val discountedPrices = items.map {item => Item (addPercent (item.price, -discount), item.nature)}
    val taxedPrices = discountedPrices.map {item => Item (addPercent (item.price, item.nature.taxRate), item.nature)}
    itemsTotalPrice (taxedPrices)
  }

  private def addPercent (base: Int, percentTimesTen: Int): Int = {
    (base * (1000 + percentTimesTen)) / 1000
  }

  private def itemsTotalPrice (items: Seq[Item]): Int = {
    items.foldLeft (0) {(soFar, item) => soFar + item.price}
  }
}
