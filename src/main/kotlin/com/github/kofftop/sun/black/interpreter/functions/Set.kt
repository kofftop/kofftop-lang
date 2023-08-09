package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kofftop.sun.black.interpreter.utils.Interpolator.interpolate
import com.github.kofftop.sun.black.interpreter.utils.MessageScope

/**
 * Функция `set` задает переменные во всей области видимости сообщения.
 * Она не умеет проводить вычисления, поэтому все что задается ей, всегда является литералом: строкой или числом.
 * Например `set x 1`, `set y 2`, или `set name John`.
 * Вы также можете задействовать интерполяцию, чтобы использовать уже существующие значения `set message hello $name`.
 *
 * @param messageScope область видимости сообщения
 * @param args все что указано после `set`, например для `set x 1`, это `x 1`, а для `set str Hello World` это `str Hello World`.
 * Первый параметр всегда будет являться именем переменной, а все что после, строковым или числовым литералом.
 */
class Set(private val messageScope: MessageScope, args: String) : FunctionalLine {

  private val name =
    buildString {
      require(args.isNotBlank()) { "Invalid args for set: $args" }
      require(args.first().isLetter()) { "Invalid variable name: $args" }
      append(args.first())
      for (i in args.drop(1)) {
        if (i.isLetterOrDigit()) {
          append(i)
          continue
        }
        break
      }
    }

  private val str: String

  init {
    val argsAfterName = args.substringAfter(name, "")
    require(argsAfterName.startsWith(' ')) { "Invalid args: $argsAfterName" }
    str = argsAfterName.drop(1)
  }

  /**
   * Выполнить функцию `set` с заданными параметрами
   */
  override fun call() {
    val str = interpolate(str, messageScope)
    val number = str.toLongOrNull() ?: str.toDoubleOrNull()
    messageScope.setVariable(name, number ?: str)
  }
}