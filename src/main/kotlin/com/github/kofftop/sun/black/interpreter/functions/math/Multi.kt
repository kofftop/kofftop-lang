package com.github.kofftop.sun.black.interpreter.functions.math

import com.github.kofftop.sun.black.interpreter.utils.BinaryOp
import com.github.kofftop.sun.black.interpreter.utils.MessageScope

/**
 * Функция `multi`. Перемножает одну строку N раз подряд или число с числом.
 * Примеры: `multi res x y`, `multi res j i`, и так далее.
 */
class Multi(scope: MessageScope, args: String) : BinaryOp(scope, args) {

  override fun calculateResult(a: Any, b: Any): Any {
    if (a is String) {
      require(b is Long)
      return a.repeat(b.toInt())
    }

    if (b is String) {
      require(a is Long)
      return b.repeat(a.toInt())
    }

    if (a is Double || b is Double) {
      require(a is Number && b is Number)
      return a.toDouble() * b.toDouble()
    }

    require(a is Long && b is Long)
    return a * b
  }
}