package cse.katas.cashregister

/**
  * Created by dnwiebe on 9/13/17.
  */

trait Situation

case object Success extends Situation
case object Insufficient extends Situation
case object NoChange extends Situation
