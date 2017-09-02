package cse.katas.translator

import org.scalatest.path

/**
  *  Test-drive a function that will accept a camel-cased name
  *  either beginningWithALowercaseCharacter or WithAnUppercaseOne,
  *  and produce a snake-cased version of that name, like
  *  beginning_with_a_lowercase_character or with_an_uppercase_one.
  */

class TranslatorTests extends path.FunSpec {

  describe ("A Translator") {
    val subject = new Translator ()

    describe ("directed to translate an empty string") {
      val result = subject.translate ("")

      it ("produces an empty string") {
        assert (result === "")
      }
    }

    describe ("directed to translate a simple capitalized string") {
      val result = subject.translate ("Simple")

      it ("produces a lowercased version") {
        assert (result === "simple")
      }
    }

    describe ("directed to translate a camelcased string with an abbreviation") {
      val result = subject.translate ("ICalledTheAAAWhenMyCarBrokeDown")

      it ("does so") {
        assert (result === "i_called_the_aaa_when_my_car_broke_down")
      }
    }
  }
}
