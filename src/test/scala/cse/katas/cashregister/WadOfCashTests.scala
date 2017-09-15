package cse.katas.cashregister

import org.scalatest.path

/**
  * Created by dnwiebe on 9/13/17.
  */

class WadOfCashTests extends path.FunSpec {
  describe ("A WadOfCash with all different counts") {
    val subject = WadOfCash.builder
      .add (Twenty, 2)
      .add (Ten, 3)
      .add (Five, 4)
      .add (Single, 5)
      .add (Quarter, 2)
      .add (Quarter, 4)
      .add (Dime, 7)
      .add (Nickel, 8)
      .add (Penny, 9)
      .build

    describe ("asked what its value is") {
      val result = subject.value

      it ("answers correctly") {
        assert (result === 9769)
      }
    }

    describe ("asked how many Quarters it has") {
      val result = subject.count (Quarter)

      it ("says six") {
        assert (result === 6)
      }
    }

    describe ("when another WadOfCash is added") {
      val change = WadOfCash.builder
        .add (Twenty, -1)
        .add (Nickel, 2)
        .build
      val result = subject + change

      it ("produces the expected result") {
        assert (result === WadOfCash.builder
          .add (Twenty, 1)
          .add (Ten, 3)
          .add (Five, 4)
          .add (Single, 5)
          .add (Quarter, 2)
          .add (Quarter, 4)
          .add (Dime, 7)
          .add (Nickel, 10)
          .add (Penny, 9)
          .build)
      }
    }

    describe ("when another WadOfCash is subtracted") {
      val change = WadOfCash.builder
        .add (Twenty, -1)
        .add (Nickel, 2)
        .build
      val result = subject - change

      it ("produces the expected result") {
        assert (result === WadOfCash.builder
          .add (Twenty, 3)
          .add (Ten, 3)
          .add (Five, 4)
          .add (Single, 5)
          .add (Quarter, 2)
          .add (Quarter, 4)
          .add (Dime, 7)
          .add (Nickel, 6)
          .add (Penny, 9)
          .build)
      }
    }
  }
}
