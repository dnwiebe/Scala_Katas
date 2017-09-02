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

    def check (description: String, input: String, output: String): Unit = {
      describe (s"directed to translate a $description") {
        val result = subject.translate (input)

        it ("does so") {
          assert (result === output)
        }
      }
    }

    check ("an empty string", "", "");
    check ("a simple capitalized string", "Simple", "simple")
    check ("a camelcased string with an abbreviation", "ICalledTheAAAWhenMyCarBrokeDown", "i_called_the_aaa_when_my_car_broke_down")
    check ("a camelcased string that ends with a one-letter word", "TheLetterA", "the_letter_a")
    check ("one character", "A", "a")
    check ("a camel-cased string beginning with a lowercase letter", "camelCase", "camel_case")
  }
}
