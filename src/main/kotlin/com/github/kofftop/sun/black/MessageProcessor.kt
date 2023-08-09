package com.github.kofftop.sun.black

import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.github.kofftop.sun.black.interpreter.Interpreter
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Message

/**
 * Обработчик одного сообщения, пытается распознать скрипт и принять решение, что с ним делать.
 */
@Suppress("ReplaceCallWithBinaryOperator")
class MessageProcessor(private val bot: Bot, private val message: Message) {

  private val lines = message.text?.lines().orEmpty()

  /**
   * Попытаться интерпретировать сообщение.
   * 1. Сообщения начинающиеся со строки `begin`, будут обрабатываться как исполняемый код.
   * Ошибки, возникшие при этом, будут отправлены в ответном сообщении.
   * 2. Сообщения начинающиеся со строки `fun`, будут сохранены в чате как функции для дальнейшего использования
   * (отправлены как файл с идентификатором в ответном сообщении).
   * 3. В остальных случаях, сообщения будут проигнорированы как заведомо нерелевантные, без вывода каких либо ошибок.
   */
  fun processMessage() {

    if (isEmptyMessage()) {
      return
    }

    if (isExecutableMessage()) {
      executeMessage()
    }

    if (isFunctionalMessage()) {
      saveFunctionForLater()
    }
  }

  private fun isEmptyMessage(): Boolean {
    return lines.isEmpty()
  }

  private fun isExecutableMessage(): Boolean {
    return lines.first().trim().equals("begin")
  }

  private fun isFunctionalMessage(): Boolean {
    return lines.first().startsWith("fun ")
  }

  private fun executeMessage() {
    val code = lines.drop(1)
    if (code.isEmpty()) {
      return
    }
    val messageScope = MessageScope()
    for (line in code) {
      if (line.isBlank()) {
        continue
      }
      val singleLineExecutor = Interpreter(bot, message, messageScope, line)
      singleLineExecutor.executeLine()
    }
  }

  private fun saveFunctionForLater() {

    TODO()
  }
}