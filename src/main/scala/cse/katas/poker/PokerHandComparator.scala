package cse.katas.poker

import cse.katas.poker.PokerHandComparator.Hand

/**
  * Created by dnwiebe on 3/2/17.
  */

object PokerHandComparator {
  case class Face ()
  val FA = Face ()
  val F1 = Face ()
  val F2 = Face ()
  val F3 = Face ()
  val F4 = Face ()
  val F5 = Face ()
  val F6 = Face ()
  val F7 = Face ()
  val F8 = Face ()
  val F9 = Face ()
  val F10 = Face ()
  val FJ = Face ()
  val FQ = Face ()
  val FK = Face ()

  case class Suit ()
  val C = Suit ()
  val D = Suit ()
  val H = Suit ()
  val S = Suit ()

  case class Card (face: Face, suit: Suit)

  object Hand {
    def apply (cards: (Card, Card, Card, Card, Card)) = new Hand (Set (cards._1, cards._2, cards._3, cards._4, cards._5))
    def apply (card1: (Face, Suit), card2: (Face, Suit), card3: (Face, Suit), card4: (Face, Suit), card5: (Face, Suit)) =
      new Hand (Set (Card (card1._1, card1._2), Card (card2._1, card2._2), Card (card3._1, card3._2),
        Card (card4._1, card4._2), Card (card5._1, card5._2)))
  }

  class Hand (private val cardSet: Set[Card]) {
    def cards: Set[Card] = cardSet
  }

  def outranks (first: Hand, second: Hand): Boolean = {
    false
  }
}
