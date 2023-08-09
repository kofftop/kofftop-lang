package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kofftop.sun.black.interpreter.utils.Interpolator.interpolate
import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.ChatId

/**
 * Функция `echo` для вывода сообщения в чат.
 * Принимает строку которая, может содержать имена переменных для подстановки, по аналогии с Kotlin.
 * Например: `echo Hello $name!`.
 *
 * @param bot телеграм бот исполняющий команды
 * @param chatId идентификатор чата в котором была вызвана команда
 * @param args все что идет после вызова `echo`, например для строки `echo Hello $name!`
 * этим параметром будет `Hello $name!`
 * **Обратите внимание, что `echo` умеет выводить только строки!**
 * Если вам нужно вывести предопределенные заранее числа, используйте интерполяцию.
 *
 * @param stringProvider провайдер строк для подстановки, например для переменной `$name` из примера выше
 */
class Echo(
  private val bot: Bot,
  private val chatId: ChatId,
  stringProvider: MessageScope,
  args: String
): FunctionalLine {

  internal val cachedString = interpolate(args, stringProvider)

  /**
   * Выполнить функцию `echo` с заданными параметрами
   */
  override fun call() {
    bot.sendMessage(chatId, cachedString)
  }
}