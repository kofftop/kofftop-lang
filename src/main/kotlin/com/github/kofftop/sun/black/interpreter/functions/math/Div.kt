package com.github.kofftop.sun.black.interpreter.functions.math

import com.github.kofftop.sun.black.interpreter.utils.BinaryOp
import com.github.kofftop.sun.black.interpreter.utils.MessageScope

/**
 * Функция `div` отвечает за деление и всегда принимает
 * имя переменной для сохранения результата и два предопределенных заранее аргумента, для деления первого на второй.
 * Например, `div divResult a b` или `div res x y`, и так далее.
 * Обратите внимание, функция `div` не может принимать и использовать литералы.
 *
 * @param scope область видимости сообщения, из которой и будут взяты значения аргументов
 * @param args все что идет после вызова `div`, для примеров выше,
 * соответственно это были бы строки `divResult a b` или `res x y`
 */
class Div(scope: MessageScope, args: String) : BinaryOp(scope, args) {

  override fun calculateResult(a: Any, b: Any): Any {
    if (a is String) {
      require(b is Long)
      return a.chunked(b.toInt())
    }

    if (a is Double || b is Double) {
      require(a is Number && b is Number)
      return a.toDouble() / b.toDouble()
    }

    require(a is Long && b is Long)
    return a / b
  }
}