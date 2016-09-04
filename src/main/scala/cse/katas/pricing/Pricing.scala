package cse.katas.pricing

/**
  * Created by dnwiebe on 9/4/16.
  */

trait ItemNature
case object Food extends ItemNature
case object Alcohol extends ItemNature
case object Other extends ItemNature

case class Item (
  price: Int,
  nature: ItemNature
)

object Pricing {
  def priceItems (items: Seq[Item]): Int = {
    0
  }
}
