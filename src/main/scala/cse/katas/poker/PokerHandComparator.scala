package cse.katas.poker

import cse.katas.poker.PokerHandComparator.Hand

/**
  * Created by dnwiebe on 3/2/17.
  */

object PokerHandComparator {
  case class Face () {
    def of (suit: Suit): Card = Card (this, suit)
  }
  val Ace = Face ()
  val One = Face ()
  val Two = Face ()
  val Three = Face ()
  val Four = Face ()
  val Five = Face ()
  val Six = Face ()
  val Seven = Face ()
  val Eight = Face ()
  val Nine = Face ()
  val Ten = Face ()
  val Jack = Face ()
  val Queen = Face ()
  val King = Face ()

  case class Suit ()
  val Clubs = Suit ()
  val Diamonds = Suit ()
  val Hearts = Suit ()
  val Spades = Suit ()

  case class Card (face: Face, suit: Suit)

  case class Hand (card1: Card, card2: Card, card3: Card, card4: Card, card5: Card) {
    val cards = Set (card1, card2, card3, card4, card5)
  }

  def outranks (first: Hand, second: Hand): Boolean = {
    false
  }
}
