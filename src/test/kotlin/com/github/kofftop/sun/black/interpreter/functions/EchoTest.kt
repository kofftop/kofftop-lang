package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.jupiter.api.Test

class EchoTest {

  @Test
  fun testEcho() {
    val argumentName = "arg"
    val argumentValue = "foo bar"
    val stringProvider = MessageScope()
    stringProvider.setVariable(argumentName, argumentValue)
    val sourceLine = "test \$$argumentName argument"
    val bot = mockk<Bot>(relaxed = true)
    val chatId = fromId(0)
    val echoString = Echo(bot, chatId, stringProvider, sourceLine).cachedString
    assertThat(echoString).isEqualTo("test $argumentValue argument")
  }
}