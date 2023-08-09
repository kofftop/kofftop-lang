package com.github.kofftop.sun.black.interpreter.functions.math

import com.github.kofftop.sun.black.interpreter.utils.BinaryOp
import com.github.kofftop.sun.black.interpreter.utils.MessageScope
import java.util.SplittableRandom

/**
 * Функция `subs`, вычитает из первого числа второе или из первой строки начальную подстроку.
 * Примеры `subs res a b`, `subs str data marker`.
 */
class Subs(scope: MessageScope, args: String) : BinaryOp(scope, args) {

  override fun calculateResult(a: Any, b: Any): Any {
    if (a is String && b is String) {
      return a.substringAfter(b, "")
    }

    if(a is Double || b is Double) {
      require(a is Number && b is Number)
      return a.toDouble() - b.toDouble()
    }

    require(a is Long && b is Long)
    return a - b
  }
}