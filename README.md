#### Note
There are several branches in this repository:
* ```master```: Contains skeleton tests and skeleton production code for 
each of the katas listed here. All the tests fail. You can use the
existing code and tests to build your solution on, or you can completely
replace some or all of it.
* all the others: Each kata has its own branch.  If you check out the
branch for a particular kata, all the code for the other katas will
disappear, and you'll see a possible solution for the selected kata.
All the tests pass. In all cases, there are other solutions; there 
may even be _better_ solutions.  The ones provided _are_ solutions, 
but not _the_ solutions.


# Babysitter

A babysitter has the following pay schedule:

* $10/hr to look after children before their bedtime
* $6/hr to house-sit after bedtime
* $8/hr after midnight when work causes her to lose sleep.

Write a method or function that will accept an arrival time, a departure time, and 
a bedtime, and return the babysitter's earnings for the evening in dollars.

A partial hour is always charged as a whole hour; therefore arrival and departure
times will always be integral.  Represent all times as integers on a 12-hour 
clock--not as Dates or as OffsetDateTimes or anything more complicated.
The earliest the babysitter can arrive is 5pm; the latest she can stay is 4am.
Bedtime must never be after midnight.

Dollars in this kata will also always be integral; hence, represent them as 
integers--not as Doubles or BigDecimals or anything more complicated.

The first run at this kata should not include testing or handling for any error
conditions, such as arrival times less than 1 or greater than 12 or bedtimes after
midnight.


# Closest to Zero

Test-drive a method or function to accept a list of integers and return the one that 
is closest to zero.

It should be an error for the list to be empty.

If two different numbers tie for distance from zero (for example, 2 and -2), always 
return the positive one.


# Pricing

A store has the following pricing policy:

1. Buy less than $100 worth of merchandise and pay the full price.
1. Buy $100 or more, but less than $1000, and get 10% off.
1. Buy $1000 or more, and get 15% off.

According to the laws governing the store, the following taxes apply:

* Food: no tax.
* Alcohol: 7.5% sales tax + 8% "sin" tax.
* All other merchandise: 7.5% sales tax.

Discounts are calculated on sticker prices; taxes are calculated on discounted prices.

Using TDD, write a method that will accept purchased items of food, alcohol, and other, and will
return the price the customer should be charged.


# Triangles

Using TDD, write a method or function that accepts three numbers as the lengths of three line segments.
Determine computationally which of the following cases are true:

The three segments
  1. Can be joined into an equilateral triangle. (example: 3, 3, 3)
  1. Can be joined into an isosceles triangle. (example: 5, 5, 3);
  1. Can be joined into a right triangle. (example: 3, 4, 5);
  1. Can only be joined into a triangle that is not one of the preceding cases. (example: 2, 3, 4)
  1. Cannot be made into a triangle. (example: 2, 3, 5) [Warning: might look isosceles at first glance.]

The order in which the segments are supplied must not affect the result.


# Word Wrap

Test-drive a function to accept a string and a line length, and return a version of 
the original string, possibly with newline characters inserted, such that no line in 
the resulting string is longer than the supplied line length.

Wherever possible, newlines should be inserted between words; they should be placed
inside words only when those words are themselves longer than the permitted line 
length.

For the initial cut of this kata:

* Assume that the input will contain only spaces to separate words: no tabs, 
no newlines, no form feeds; and hyphens, commas, periods, etc. are considered to be 
part of the words they're embedded in.
* Assume that words will be separated by only one space: no multiple consecutive spaces.


# Pencil

(Not the Red Pencil kata, but the Pencil Kata from Marion Correctional Institution)

Test-drive code to simulate an ordinary wooden graphite pencil. Run the
following steps:

1. When the pencil is instructed to write a string, it returns exactly
the string it was instructed to write.

1. Add simulation of a dulling point.  After writing a certain number
of characters, the simulated pencil goes dull and begins to return
spaces instead of the characters it's instructed to write.

1. Add sharpening capability. When a pencil is sharpened, it regains
full sharpness; but it can only be sharpened a certain number of times.
When sharpened past the limit, it no longer regains any sharpness.

1. It requires no lead to write a space (or a tab or a carriage return
or a newline).  Make the pencil capable of writing an arbitrary amount
of whitespace without going any duller.

1. It requires more lead to write a capital M than it does to write
a period.  Come up with a reasonable classification of the writeable
characters so that the pencil can write more periods than capital Ms
before going dull.


# Scoring Tennis

Test-drive some code to keep track of the score of a single
tennis game and present it as String.

The score of a tennis game has two stages.

In the first, which we will call the Absolute Stage, the
two players proceed through a sequence of absolute point
names: "love," "fifteen," "thirty," and "forty." During this stage,
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

Prohibit further scoring after the game has ended.


# Ranking Poker Hands

Write some code that, given two five-card poker hands,
tells which hand outranks the other.

Unlike the other katas, this one provides a significant
amount of pre-written testing functionality.  (Note that
not all these tests fail in the ```master``` branch.
However, that doesn't mean we've written any production code
for you.)  If you choose to use this, it will have a 
significant effect on the way your data structures are 
arranged; this may affect your algorithms.  Feel free to 
discard it and start from scratch if you don't like it.

Poker ranking references are provided in many places on
the Internet, including [this one.](http://www.cardplayer.com/rules-of-poker/hand-rankings)


# Translating Names

Test-drive a function that will accept a camel-cased name
either `beginningWithALowercaseCharacter` or `WithAnUppercaseOne`,
and produce a snake-cased version of that name, like
`beginning_with_a_lowercase_character` or `with_an_uppercase_one`.


# Cash Register

A small shop has a standard cash register, with a drawer containing
various numbers of various denominations of bills and coins.  A
customer arrives at the checkout counter with a shopping cart
containing merchandise of a particular value and hands the cashier
a cash payment, also consisting of various numbers of various
denominations of bills and coins.

There are three possibilities.

* The customer does not offer enough money for the merchandise.  In
this case the money is returned to the customer and the contents of
the cash register is unchanged.

* The customer offers more than enough money, but the contents of
the cash register cannot be used to make change.  In this case the
money is also returned to the customer and the contents of the cash
register is unchanged.

* The customer offers enough or more than enough money for the
merchandise, and the cashier is able to make change from the contents
of the cash register.  In this case the customer's payment is added
to the cash register, and the change is removed from it.

Test-drive some code that will accept a populated cash register,
a number representing the value of a cart of merchandise, and a
data structure representing a cash payment; will attempt to
perform the indicated transaction; and will provide results
including which of the three possibilities obtained and the contents
of the cash register after the transaction.

__Note:__ Make sure your algorithm can handle the situation where fifty
cents of change needs to be made and the cash register contains only
one quarter and five dimes. _[credit: Tracy Harms]_
