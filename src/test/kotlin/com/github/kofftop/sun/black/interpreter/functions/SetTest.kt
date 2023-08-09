package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

class SetTest {

  @Test
  fun testSet() {
    val messageScope = MessageScope()
    Set(messageScope, "x 2").call()
    assertThat(messageScope.requireLong("x")).isEqualTo(2)

    Set(messageScope, "y \$x.1").call()
    assertThat(messageScope.requireDouble("y")).isEqualTo(2.1)

    Set(messageScope, "name John Carter \$x").call()
    assertThat(messageScope.requireString("name")).isEqualTo("John Carter 2")
  }
}