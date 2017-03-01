package cse.katas.tennis

import org.scalatest.path

/**
  * Created by dnwiebe on 3/1/17.
  */

/**
  Test-drive some code to keep track of the score of a single
  tennis game and present it as String.

  The score of a tennis game has two stages.

  In the first, which we will call the Absolute Stage, the
  two players proceed through a sequence of absolute point
  names: love, fifteen, thirty, and forty. During this stage,
  the score will be either of the form "Alice forty, Bob fifteen"
  if the players have different scores, or of the form
  "Love all" if the scores are the same.

  In the second stage, the Relative Stage, the representation
  of the score depends on how many points one player has
  relative to the other.  If Alice and Bob have the same score,
  the representation is "Deuce."  If Bob has one more point
  Alice, the representation is "Advantage Bob."  If Alice
  scores two more points than Bob, the score is "Game Alice,"
  and the game is over.

  Note that the Absolute Stage cannot possibly last longer
  than a total of eight points, but the Relative Stage can
  theoretically go on forever, as long as no one ever has
  two points more than his opponent.

  Write some code that can take a series of scores by one
  player or the other and produce the proper representation
  of the game's score at each step.

  Prohibit scoring after the game has ended.
 */

class TennisScoreTest extends path.FunSpec {
  describe ("A new game") {
    val subject0_0 = new TennisScore ("Alice", "Bob")

    it ("shows the expected score") {
      assert (subject0_0.toString === "Love all")
    }

    describe ("after a score by Alice") {
      val subject1_0 = subject0_0.scoreLeft

      it ("shows the expected score") {
        assert (subject1_0.toString === "Alice fifteen, Bob love")
      }
    }

    describe ("after a score by Bob") {
      val subject0_1 = subject0_0.scoreRight

      it ("shows the expected score") {
        assert (subject0_1.toString === "Alice love, Bob fifteen")
      }
    }
  }
}
