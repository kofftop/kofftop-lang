package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.functions.math.Sum
import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class SumTest {

  @Test
  fun testSum() {
    val scope = MessageScope()

    for(line in listOf("a 2", "b 3")) {
      Set(scope, line).call()
    }
    Sum(scope, "res a b").call()
    assertThat(scope.requireLong("res")).isEqualTo(5)

    for(line in listOf("str1 lol ", "str2 kek \$a hello world")) {
      Set(scope, line).call()
    }
    Sum(scope, "strRes str1 str2").call()
    assertThat(scope.requireString("strRes")).isEqualTo("lol kek 2 hello world")

    Sum(scope, "res str1 res").call()
    assertThat(scope.requireString("res")).isEqualTo("lol 5")
  }
}