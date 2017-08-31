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
  }
}
