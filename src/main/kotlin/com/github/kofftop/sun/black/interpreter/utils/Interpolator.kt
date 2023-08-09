package com.github.kofftop.sun.black.interpreter.utils

/**
 * Интерполятор строк для собственного ЯП
 */
object Interpolator {

  /**
   * Интерполирует строки по правилам, аналогичным правилам Kotlin.
   * Переменные указываются после символа `$`, если нужен сам символ `$`, используйте `\$`
   *
   * @param sourceString исходная строка, например: `Hello $name!`
   * @param stringProvider провайдер строк для подстановки, например для переменной `name` из примера выше
   *
   * @return интерполированная строка, например `Hello John!` или `Hello Ivan!` в зависимости от значения `name`
   *
   */
  fun interpolate(sourceString: String, stringProvider: MessageScope): String {
    if (sourceString.isBlank()) {
      return sourceString
    }
    return buildString {
      var i = 0
      while (i in sourceString.indices) {
        if (isDollarSymbol(sourceString, i)) {
          append('$')
          i += lengthOfDollarSymbol()
          continue
        }
        if (isName(sourceString, i)) {
          val name = getName(sourceString, i)
          val value = stringProvider.requireString(name)
          append(value)
          i += lengthOfName(name)
          continue
        }
        append(sourceString[i])
        ++i
      }
    }
  }

  private fun lengthOfDollarSymbol() = 2

  private fun lengthOfName(name: String) = name.length + 1

  private fun isDollarSymbol(args: String, pos: Int): Boolean {
    val currSymbol = args[pos]
    val nextSymbol = args.getOrNull(pos + 1)
    return currSymbol == '\\' && nextSymbol == '$'
  }

  private fun isName(args: String, pos: Int): Boolean {
    val currSymbol = args[pos]
    return currSymbol == '$'
  }

  private fun getName(args: String, pos: Int): String {
    return buildString {
      for (i in pos + 1 until args.length) {
        val currChar = args[i]
        if (currChar.isLetter()) {
          append(currChar)
          continue
        }
        break
      }
    }
  }
}