package cse.katas.cashregister

import scala.collection.mutable

/**
  * Created by dnwiebe on 9/13/17.
  */

object WadOfCash {
  def builder: WadOfCash.Builder = new WadOfCash.Builder ()

  class Builder {
    private val counts = mutable.Map[Denomination, Int] ()

    def add (denomination: Denomination, count: Int): Builder = {
      val existingCount = counts.getOrElse (denomination, 0)
      counts.put (denomination, existingCount + count)
      this
    }

    def build: WadOfCash = {
      new WadOfCash (counts.toMap)
    }
  }
}

case class WadOfCash (private val counts: Map[Denomination, Int]) {
  def count (denomination: Denomination): Int = counts.getOrElse (denomination, 0)

  def value: Int = {
    counts.map {entry =>
      val (denomination, count) = entry
      denomination.value * count
    }.sum
  }

  def + (addend: WadOfCash): WadOfCash = {
    operate (addend, (a, b) => a + b)
  }

  def - (subtrahend: WadOfCash): WadOfCash = {
    operate (subtrahend, (a, b) => a - b)
  }

  private def operate (that: WadOfCash, f: (Int, Int) => Int): WadOfCash = {
    WadOfCash (Denomination.values.map {d => (d, f (count (d), that.count (d)))}.toMap)
  }

  override def toString: String = {
    val denCounts = Denomination.values
      .filter {count (_) > 0}
      .map {d => s"${count (d)}x${d.getClass.getSimpleName.reverse.tail.reverse}"}
    denCounts.length match {
      case 0 => "<empty>"
      case _ => denCounts.mkString (", ")
    }
  }
}
