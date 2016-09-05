package cse.katas.babysitter

/**
  * Created by dnwiebe on 9/4/16.
  */
object Babysitter {
  def calculatePay (arrival: Int, departure: Int, bedtime: Int): Int = {
    (mapTime (arrival) until mapTime (departure)).foldLeft (0) {(soFar, hour) =>
      soFar + rateFor (hour, bedtime)
    }
  }

  private def mapTime (hour: Int) = if (hour < 5) {hour + 12} else {hour}

  private def rateFor (hour: Int, bedtime: Int): Int = {
    hour match {
      case h if h < bedtime => 10
      case h if h < 12 => 6
      case h if h < 16 => 8
    }
  }
}
