package cse.katas.tennis

/**
  * Created by dnwiebe on 3/1/17.
  */
object TennisScore {
  def apply (left: String, right: String): TennisScore = new AbsoluteStage (left, 0, right, 0)
}

trait TennisScore {
  def scoreLeft: TennisScore
  def scoreRight: TennisScore
}

object AbsoluteStage {
  private val POINTS = List ("love", "fifteen", "thirty", "forty")

  private def cap (s: String): String = {
    s.head.toUpper + s.tail
  }
}

class AbsoluteStage (left: String, leftScore: Int, right: String, rightScore: Int) extends TennisScore {
  import cse.katas.tennis.AbsoluteStage._

  def scoreLeft: TennisScore = {
    POINTS.size - leftScore match {
      case 1 => if (leftScore > rightScore) new TerminatedStage (left) else new RelativeStage (left, right, 1)
      case _ => new AbsoluteStage (left, leftScore + 1, right, rightScore)
    }
  }

  def scoreRight: TennisScore = {
    POINTS.size - rightScore match {
      case 1 => if (rightScore > leftScore) new TerminatedStage (right) else new RelativeStage (left, right, -1)
      case _ => new AbsoluteStage (left, leftScore, right, rightScore + 1)
    }
  }

  override def toString: String = {
    (leftScore, rightScore) match {
      case (l, r) if l == r => s"${cap (POINTS (l))} all"
      case (l, r) => s"$left ${POINTS (l)}, $right ${POINTS (r)}"
    }
  }
}

class RelativeStage (left: String, right: String, leftAdvantage: Int) extends TennisScore {
  def scoreLeft: TennisScore = {
    leftAdvantage match {
      case 1 => new TerminatedStage (left)
      case _ => new RelativeStage (left, right, leftAdvantage + 1)
    }
  }

  def scoreRight: TennisScore = {
    leftAdvantage match {
      case -1 => new TerminatedStage (right)
      case _ => new RelativeStage (left, right, leftAdvantage - 1)
    }
  }

  override def toString: String = {
    leftAdvantage match {
      case 1 => s"Advantage $left"
      case 0 => "Deuce"
      case -1 => s"Advantage $right"
    }
  }
}

class TerminatedStage (winner: String) extends TennisScore {
  def scoreLeft: TennisScore = this
  def scoreRight: TennisScore = this

  override def toString = s"Game $winner"
}
