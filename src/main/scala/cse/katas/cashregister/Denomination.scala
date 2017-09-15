package cse.katas.cashregister

/**
  * Created by dnwiebe on 9/13/17.
  */

object Denomination {
  val values = Array (Twenty, Ten, Five, Single, Quarter, Dime, Nickel, Penny)
}

trait Denomination {
  val value: Int
  val next: Option[Denomination]
}

object Twenty extends Denomination {val value = 2000; val next = Some (Ten)}
object Ten extends Denomination {val value = 1000; val next = Some (Five)}
object Five extends Denomination {val value = 500; val next = Some (Single)}
object Single extends Denomination {val value = 100; val next = Some (Quarter)}
object Quarter extends Denomination {val value = 25; val next = Some (Dime)}
object Dime extends Denomination {val value = 10; val next = Some (Nickel)}
object Nickel extends Denomination {val value = 5; val next = Some (Penny)}
object Penny extends Denomination {val value = 1; val next: Option[Denomination] = None}
