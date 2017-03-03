package cse.katas.poker

import cse.katas.poker.PokerHandComparator.Hand

/**
  * Created by dnwiebe on 3/2/17.
  */

object PokerHandComparator {
  case class Face (name: String, highValue: Int, lowValue: Int) {
    def of (suit: Suit): Card = Card (this, suit)
    override def toString: String = name
  }
  val Ace = Face ("Ace", 1, 14)
  val Two = Face ("Two", 2, 2)
  val Three = Face ("Three", 3, 3)
  val Four = Face ("Four", 4, 4)
  val Five = Face ("Five", 5, 5)
  val Six = Face ("Six", 6, 6)
  val Seven = Face ("Seven", 7, 7)
  val Eight = Face ("Eight", 8, 8)
  val Nine = Face ("Nine", 9, 9)
  val Ten = Face ("Ten", 10, 10)
  val Jack = Face ("Jack", 11, 11)
  val Queen = Face ("Queen", 12, 12)
  val King = Face ("King", 13, 13)

  case class Suit (name: String, rank: Int) {
    override def toString: String = name
  }
  val Clubs = Suit ("Clubs", 1)
  val Diamonds = Suit ("Diamonds", 2)
  val Hearts = Suit ("Hearts", 3)
  val Spades = Suit ("Spades", 4)

  case class Card (face: Face, suit: Suit) {
    override def toString: String = s"${face.toString} of ${suit.toString}"
  }

  case class Hand (card1: Card, card2: Card, card3: Card, card4: Card, card5: Card) {
    val cards = Set (card1, card2, card3, card4, card5)

    override def toString: String = s"(${cards.mkString (", ")})"
  }

  trait Rank {
    def value: Int
    def recognizes (hand: Hand): Boolean

    protected def faceCounts (hand: Hand): Map[Face, Int] = {
      counts (hand.cards.toList.map {_.face})
    }

    protected def suitCounts (hand: Hand): Map[Suit, Int] = {
      counts (hand.cards.toList.map {_.suit})
    }

    protected def isStraight (hand: Hand): Boolean = {
      val lowValues = hand.cards.map {_.face.lowValue}.toList.sorted
      val highValues = hand.cards.map {_.face.highValue}.toList.sorted
      ((lowValues.head + 4) == lowValues.last) || ((highValues.head + 4) == highValues.last)
    }

    protected def isFlush (hand: Hand): Boolean = {
      suitCounts (hand).values.size == 1
    }

    protected def isRoyalStraight (hand: Hand): Boolean = {
      def faces = hand.cards.map {_.face}
      isStraight (hand) && faces.contains (Ace) && faces.contains (Ten)
    }

    private def counts[A] (items: List[A]): Map[A, Int] = {
      items.foldLeft (Map[A, Int] ()) {(soFar, item) =>
        soFar.get (item) match {
          case Some (n) => soFar + (item -> (n + 1))
          case None => soFar + (item -> 1)
        }
      }
    }
  }
  case object HighCard extends Rank {
    def value = 1
    def recognizes (hand: Hand): Boolean = true
  }
  case object OnePair extends Rank {
    def value = 2
    def recognizes (hand: Hand): Boolean = faceCounts (hand).values.count (_ == 2) == 1
  }
  case object TwoPair extends Rank {
    def value = 3
    def recognizes (hand: Hand): Boolean = faceCounts (hand).values.count (_ == 2) == 2
  }
  case object ThreeOfAKind extends Rank {
    def value = 4
    def recognizes (hand: Hand): Boolean = faceCounts (hand).values.count (_ == 3) == 1
  }
  case object Straight extends Rank {
    def value = 5
    def recognizes (hand: Hand): Boolean = isStraight (hand)
  }
  case object Flush extends Rank {
    def value = 6
    def recognizes (hand: Hand): Boolean = isFlush (hand)
  }
  case object FullHouse extends Rank {
    def value = 7
    def recognizes (hand: Hand): Boolean = {
      val counts = faceCounts (hand).values
      counts.exists (_ == 2) && counts.exists (_ == 3)
    }
  }
  case object FourOfAKind extends Rank {
    def value = 8
    def recognizes (hand: Hand): Boolean = faceCounts (hand).values.count (_ == 4) == 1
  }
  case object StraightFlush extends Rank {
    def value = 9
    def recognizes (hand: Hand): Boolean = isStraight (hand) && isFlush (hand)
  }
  case object RoyalFlush extends Rank {
    def value = 10
    def recognizes (hand: Hand): Boolean = {
      isFlush (hand) &&
      isRoyalStraight (hand)
    }
  }
  private val RANKS = List (RoyalFlush, StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind,
    TwoPair, OnePair, HighCard)

  def outranks (first: Hand, second: Hand): Boolean = {
    rank (first).value > rank (second).value
  }

  private def rank (hand: Hand): Rank = {
    RANKS.find (_.recognizes(hand)).get
  }
}
