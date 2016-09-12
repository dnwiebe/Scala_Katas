# Babysitter

A babysitter has the following pay schedule:

* $10/hr to look after children before their bedtime
* $6/hr to house-sit after bedtime
* $8/hr after midnight when work causes her to lose sleep.

Write a method or function that will accept an arrival time, a departure time, and a bedtime, and return
the babysitter's earnings for the evening in dollars.

Represent the times as integers on a 12-hour clock.  The earliest the babysitter can arrive is 5pm;
the latest she can stay is 4am.


# Closest to Zero

Test-drive a method or function to accept a list of integers and return the one that is closest to zero.

It should be an error for the list to be empty.

If two different numbers tie for distance from zero (for example, 2 and -2), always return the positive one.


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
  1. Cannot be made into a triangle. (example: 2, 2, 5) [Warning: might look isosceles at first glance.]
