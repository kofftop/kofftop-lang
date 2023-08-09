package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kofftop.sun.black.interpreter.utils.MessageScope

/**
 * Функция `begin` обнуляет область видимости
 */
class Begin(private val messageScope: MessageScope) : FunctionalLine {

  override fun call() {
    messageScope.clear()
  }
}