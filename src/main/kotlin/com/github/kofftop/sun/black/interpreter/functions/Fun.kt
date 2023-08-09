package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import com.github.kofftop.sun.black.interpreter.utils.NamesValidator.requireValid

/**
 * Функция `fun` проверяет что заданные аргументы действительно были переданы в области видимости.
 * Пример использования в начале сообщения: `fun a b c`
 *
 * @param messageScope область видимости сообщения
 * @param args строка идущая после `fun`, для примера выше это будет `a b c`
 */
class Fun(private val messageScope: MessageScope, args: String) : FunctionalLine {

  private val parsedArgs =
    args.split(' ')
      .filter(String::isNotBlank)
      .map(String::trim)
      .onEach(::requireValid)

  /**
   * Выполнить валидацию аргументов функции на корректность имен и их наличие в области видимости
   */
  override fun call() {
    parsedArgs.forEach(messageScope::requireVariable)
  }
}