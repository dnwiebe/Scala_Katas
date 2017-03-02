package cse.katas.poker

import cse.katas.poker.PokerHandComparator._
import org.scalatest.path

/**
  * Created by dnwiebe on 3/2/17.
  */
class PokerHandComparatorTest extends path.FunSpec {

  describe ("Given a series of poker hands") {
    val rankPairs = List (
      RankPair ("high card",
        Hand ((F3, D), (FJ, C), (F8, S), (F4, H), (F2, S)),
        Hand ((F3, D), (FQ, C), (F8, S), (F4, H), (F2, S))
      ),
      RankPair ("one pair",
        Hand ((F2, H), (F2, D), (F8, C), (F4, S), (F7, H)),
        Hand ((FA, H), (FA, D), (F8, C), (F4, S), (F7, H))
      ),
      RankPair ("two pair",
        Hand ((F2, H), (F2, D), (F8, C), (F4, S), (F8, H)),
        Hand ((F2, H), (FA, D), (F9, C), (F4, S), (F9, H))
      ),
      // ...
      RankPair ("royal flush",
        Hand ((FA, C), (FK, C), (FQ, C), (FJ, C), (F10, C)),
        Hand ((FA, S), (FK, S), (FQ, S), (FJ, S), (F10, S))
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

        describe (s"${rankPairPair._1.rankName} and ${rankPairPair._2.rankName}") {
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

  case class RankPair (rankName: String, lowHand: Hand, highHand: Hand)

}
