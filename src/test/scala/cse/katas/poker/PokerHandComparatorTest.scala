package cse.katas.poker

import cse.katas.poker.PokerHandComparator._
import org.scalatest.path

/**
  * Created by dnwiebe on 3/2/17.
  */
class PokerHandComparatorTest extends path.FunSpec {

  describe ("Given a series of poker hands") {
    case class RankPair (rank: Rank, lowHand: Hand, highHand: Hand)
    val rankPairs = List (
      RankPair (HighCard,
        Hand (Two of Clubs, Three of Spades, Four of Hearts, Five of Spades, Seven of Diamonds),
        Hand (Ace of Diamonds, King of Clubs, Queen of Spades, Jack of Hearts, Nine of Spades)
      ),
      RankPair (OnePair,
        Hand (Two of Hearts, Two of Diamonds, Eight of Clubs, Four of Spades, Seven of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, Eight of Clubs, Four of Spades, Seven of Hearts)
      ),
      RankPair (TwoPair,
        Hand (Two of Hearts, Two of Diamonds, Four of Clubs, Five of Spades, Four of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, King of Clubs, King of Spades, Three of Hearts)
      ),
      RankPair (ThreeOfAKind,
        Hand (Two of Hearts, Two of Diamonds, Two of Clubs, Four of Spades, Three of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, Ace of Clubs, Four of Spades, King of Hearts)
      ),
      RankPair (Straight,
        Hand (Ace of Hearts, Two of Diamonds, Three of Clubs, Four of Spades, Five of Hearts),
        Hand (Ten of Hearts, Jack of Diamonds, Queen of Clubs, King of Spades, Ace of Hearts)
      ),
      RankPair (Flush,
        Hand (Two of Hearts, Three of Hearts, Four of Hearts, Five of Hearts, Seven of Hearts),
        Hand (Nine of Hearts, Jack of Hearts, Queen of Hearts, King of Hearts, Ace of Hearts)
      ),
      RankPair (FullHouse,
        Hand (Two of Hearts, Two of Diamonds, Two of Clubs, Three of Spades, Three of Hearts),
        Hand (King of Hearts, King of Diamonds, Ace of Clubs, Ace of Spades, Ace of Hearts)
      ),
      RankPair (FourOfAKind,
        Hand (Two of Hearts, Two of Diamonds, Two of Clubs, Two of Spades, Three of Hearts),
        Hand (Ace of Hearts, Ace of Diamonds, Ace of Clubs, Ace of Spades, King of Hearts)
      ),
      RankPair (StraightFlush,
        Hand (Ace of Clubs, Two of Clubs, Three of Clubs, Four of Clubs, Five of Clubs),
        Hand (King of Spades, Queen of Spades, Jack of Spades, Ten of Spades, Nine of Spades)
      ),
      RankPair (RoyalFlush,
        Hand (Ace of Clubs, King of Clubs, Queen of Clubs, Jack of Clubs, Ten of Clubs),
        Hand (Ace of Spades, King of Spades, Queen of Spades, Jack of Spades, Ten of Spades)
      )
    )

    rankPairs.flatMap {rankPair => List ((rankPair.rank, rankPair.lowHand), (rankPair.rank, rankPair.highHand))}.foreach {rank =>
      it (s"${rank._2.toString} qualifies as ${nameOf (rank._1)}") {
        assert (rank._1.recognizes (rank._2))
      }
    }

    describe ("comparing within ranks") {
      rankPairs.foreach {rankPair =>
        it (s"a high ${nameOf (rankPair.rank)} outranks a low ${nameOf (rankPair.rank)}") {
          assert (PokerHandComparator.outranks (rankPair.highHand, rankPair.lowHand))
        }
        it (s"a low ${nameOf (rankPair.rank)} does not outrank a high ${nameOf (rankPair.rank)}") {
          assert (!PokerHandComparator.outranks (rankPair.lowHand, rankPair.highHand))
        }
      }
    }

    describe ("comparing across ranks") {
      val rankPairPairs = rankPairs.zip (rankPairs.tail)
      rankPairPairs.foreach {rankPairPair =>
        case class RankExample (level: String, name: String, hand: Hand)
        val lows = List (
          RankExample ("low", nameOf (rankPairPair._1.rank), rankPairPair._1.lowHand),
          RankExample ("high", nameOf (rankPairPair._1.rank), rankPairPair._1.highHand)
        )
        val highs = List (
          RankExample ("low", nameOf (rankPairPair._2.rank), rankPairPair._2.lowHand),
          RankExample ("high", nameOf (rankPairPair._2.rank), rankPairPair._2.highHand)
        )

        describe (s"${rankPairPair._1.rank} vs ${rankPairPair._2.rank}") {
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

  private def nameOf (obj: Any): String = {
    obj.getClass.getSimpleName.reverse.substring (1).reverse
  }
}
