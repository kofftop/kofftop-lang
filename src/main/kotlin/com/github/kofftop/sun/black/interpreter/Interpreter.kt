package com.github.kofftop.sun.black.interpreter

import com.github.kofftop.sun.black.interpreter.functions.Echo
import com.github.kofftop.sun.black.interpreter.functions.Set
import com.github.kofftop.sun.black.interpreter.functions.math.Sum
import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId.Companion.fromId
import com.github.kotlintelegrambot.entities.Message

/**
 * Исполнитель строк с кодом
 *
 * @param bot телеграм бот, от лица которого будет исполняться команда
 * @param message исходное сообщение, содержащее данные об отправителе, чате, etc
 * @param scope область видимости сообщения
 * @param code строки с кодом
 */
class Interpreter(
  private val bot: Bot,
  private val message: Message,
  private val scope: MessageScope,
  private val code: List<String>
) {

  private val chatId = fromId(message.chat.id)

  init {
    require(code.isNotBlank())
    require(code.first().isLetter()) { "Invalid function name: $code" }
  }

  fun executeLines() {

    findFunction().call()
  }

  /**
   * Находит функцию в строке, в противном случае получим исключение
   */
  private fun findFunction(): FunctionalLine {
    val (f, args) = extractFunctionNameAndArgs()
    return when (f) {
      "echo" -> Echo(bot, chatId, scope, args)
      "set" -> Set(scope, args)
      "sum" -> Sum(scope, args)
      else -> throw IllegalArgumentException("Unknown function: $code")
    }
  }

  private fun extractFunctionNameAndArgs(): Pair<String, String> {
    val functionName = buildString {
      for (i in code) {
        if (i.isLetterOrDigit()) {
          append(i)
          continue
        }
        break
      }
    }
    val args = code.substringAfter(functionName, "")
    if(args.startsWith(' ')) {
      return functionName to args.drop(1)
    }
    return functionName to args
  }
}