package cse.katas.poker

import cse.katas.poker.PokerHandComparator._
import org.scalatest.path

/**
  * Created by dnwiebe on 3/2/17.
  */
class PokerHandComparatorTest extends path.FunSpec {

  describe ("Given a series of poker hands") {
    case class RankPair (rankName: String, lowHand: Hand, highHand: Hand)
    val rankPairs = List (
      RankPair ("high card",
        Hand (Two of Diamonds, Three of Clubs, Four of Spades, Five of Hearts, Six of Spades),
        Hand (Ace of Diamonds, King of Clubs, Queen of Spades, Jack of Hearts, Ten of Spades)
      ),
      RankPair ("pair",
        Hand (Two of Hearts, Two of Diamonds, Eight of Clubs, Four of Spades, Seven of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, Eight of Clubs, Four of Spades, Seven of Hearts)
      ),
      RankPair ("two pair",
        Hand (Two of Hearts, Two of Diamonds, Three of Clubs, Four of Spades, Three of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, King of Clubs, Four of Spades, King of Hearts)
      ),
      // ...
      RankPair ("royal flush",
        Hand (Ace of Clubs, King of Clubs, Queen of Clubs, Jack of Clubs, Ten of Clubs),
        Hand (Ace of Spades, King of Spades, Queen of Spades, Jack of Spades, Ten of Spades)
      )
    )

    describe ("comparing within ranks") {
      rankPairs.foreach {rankPair =>
        it (s"a high ${rankPair.rankName} outranks a low ${rankPair.rankName}") {
          assert (PokerHandComparator.outranks (rankPair.highHand, rankPair.lowHand))
        }
        it (s"a low ${rankPair.rankName} does not outrank a high ${rankPair.rankName}") {
          assert (!PokerHandComparator.outranks (rankPair.lowHand, rankPair.highHand))
        }
      }
    }

    describe ("comparing across ranks") {
      val rankPairPairs = rankPairs.zip (rankPairs.tail)
      rankPairPairs.foreach {rankPairPair =>
        case class RankExample (level: String, name: String, hand: Hand)
        val lows = List (
          RankExample ("low", rankPairPair._1.rankName, rankPairPair._1.lowHand),
          RankExample ("high", rankPairPair._1.rankName, rankPairPair._1.highHand)
        )
        val highs = List (
          RankExample ("low", rankPairPair._2.rankName, rankPairPair._2.lowHand),
          RankExample ("high", rankPairPair._2.rankName, rankPairPair._2.highHand)
        )

        describe (s"${rankPairPair._1.rankName} vs ${rankPairPair._2.rankName}") {
          case class Combo (lowEx: RankExample, highEx: RankExample)
          val combos = for (low <- lows; high <- highs) yield Combo (low, high)
          combos.foreach { combo =>
            it (s"a ${combo.highEx.level} ${combo.highEx.name} outranks a ${combo.lowEx.level} ${combo.lowEx.name}") {
              assert (PokerHandComparator.outranks (combo.highEx.hand, combo.lowEx.hand))
            }

            it (s"a ${combo.lowEx.level} ${combo.lowEx.name} does not outrank a ${combo.highEx.level} ${combo.highEx.name}") {
              assert (!PokerHandComparator.outranks (combo.lowEx.hand, combo.highEx.hand))
            }
          }
        }
      }
    }
  }
}
