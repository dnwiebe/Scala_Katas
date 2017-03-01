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

  When a player has forty and scores again, what happens depends
  on the other player's score.  If the other player has forty
  as well, the game moves on to the Relative Stage.  If the
  other player does not have forty yet, the game is over.

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
    verify (TennisScore ("Alice", "Bob"), "Alice", "Bob",
      branch ("Love all",
        branch ("Alice fifteen, Bob love",
          branch ("Alice thirty, Bob love",
            branch ("Alice forty, Bob love",
              branch ("Game Alice",
                branch ("Game Alice"),
                branch ("Game Alice")
              ),
              branch ("Alice forty, Bob fifteen",
                branch ("Game Alice"),
                branch ("Alice forty, Bob thirty",
                  branch ("Game Alice"),
                  branch ("Forty all",
                    branch ("Advantage Alice",
                      branch ("Game Alice"),
                      branch ("Deuce")
                    ),
                    branch ("Advantage Bob",
                      branch ("Deuce"),
                      branch ("Game Bob")
                    )
                  )
                )
              )
            ),
            branch ("Alice thirty, Bob fifteen",
              branch ("Alice forty, Bob fifteen",
                branch ("Game Alice"),
                branch ("Alice forty, Bob thirty")
              ),
              branch ("Thirty all",
                branch ("Alice forty, Bob thirty",
                  branch ("Game Alice"),
                  branch ("Forty all",
                    branch ("Advantage Alice"),
                    branch ("Advantage Bob")
                  )
                ),
                branch ("Alice thirty, Bob forty",
                  branch ("Forty all"),
                  branch ("Game Bob")
                )
              )
            )
          ),
          branch ("Fifteen all")
        ),
        branch ("Alice love, Bob fifteen")
      )
    )
  }

  private def branch (representation: String, left: Node, right: Node): Node = {
    Node (representation, Some (left), Some (right))
  }

  private def branch (representation: String): Node = {
    Node (representation, None, None)
  }

  private case class Node (representation: String, left: Option[Node], right: Option[Node])

  private def verify (score: TennisScore, left: String, right: String, node: Node): Unit = {
    it (s"represents the score as '${node.representation}'") {
      assert (score.toString === node.representation)
    }

    if (node.left.nonEmpty) {
      describe (s"after a score by $left") {
        verify (score.scoreLeft, left, right, node.left.get)
      }
    }

    if (node.right.nonEmpty) {
      describe (s"after a score by $right") {
        verify (score.scoreRight, left, right, node.right.get)
      }
    }
  }
}
