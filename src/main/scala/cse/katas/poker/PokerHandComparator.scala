package cse.katas.poker

import cse.katas.poker.PokerHandComparator.Hand

/**
  * Created by dnwiebe on 3/2/17.
  */

object PokerHandComparator {
  case class Face (name: String) {
    def of (suit: Suit): Card = Card (this, suit)
    override def toString: String = name
  }
  val Ace = Face ("Ace")
  val One = Face ("One")
  val Two = Face ("Two")
  val Three = Face ("Three")
  val Four = Face ("Four")
  val Five = Face ("Five")
  val Six = Face ("Six")
  val Seven = Face ("Seven")
  val Eight = Face ("Eight")
  val Nine = Face ("Nine")
  val Ten = Face ("Ten")
  val Jack = Face ("Jack")
  val Queen = Face ("Queen")
  val King = Face ("King")

  case class Suit (name: String) {
    override def toString: String = name
  }
  val Clubs = Suit ("Clubs")
  val Diamonds = Suit ("Diamonds")
  val Hearts = Suit ("Hearts")
  val Spades = Suit ("Spades")

  case class Card (face: Face, suit: Suit) {
    override def toString: String = s"${face.toString} of ${suit.toString}"
  }

  case class Hand (card1: Card, card2: Card, card3: Card, card4: Card, card5: Card) {
    val cards = Set (card1, card2, card3, card4, card5)

    override def toString: String = s"(${cards.mkString (", ")})"
  }

  trait Rank {
    def recognizes (hand: Hand): Boolean
  }
  case object HighCard extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object OnePair extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object TwoPair extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object ThreeOfAKind extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object Straight extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object Flush extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object FullHouse extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object FourOfAKind extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object StraightFlush extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }
  case object RoyalFlush extends Rank {
    def recognizes (hand: Hand): Boolean = false
  }

  def outranks (first: Hand, second: Hand): Boolean = {
    false
  }
}
