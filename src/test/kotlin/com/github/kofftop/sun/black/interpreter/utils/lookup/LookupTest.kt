package com.github.kofftop.sun.black.interpreter.utils.lookup

import com.github.kofftop.sun.black.interpreter.utils.lookup.Lookup
import com.github.kofftop.sun.black.interpreter.utils.lookup.LookupError
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class LookupTest {

  @Test
  fun lookupFor() {
    val lookup = Lookup("for while i").lookupFor("for ")
    check(lookup is Lookup)
    assertThat(lookup.lookupFor("while ")).isInstanceOf(Lookup::class.java)

    val lookupError = lookup.lookupFor("function ")
    check(lookupError is LookupError)
    assertThat(lookupError.unexpectedText).isEqualTo("while i")

    val lookupError2 = lookup.lookupFor("whole ")
    check(lookupError2 is LookupError)
    assertThat(lookupError2.unexpectedText).isEqualTo("while ")
  }
}