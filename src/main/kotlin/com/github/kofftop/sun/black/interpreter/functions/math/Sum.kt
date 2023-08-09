package com.github.kofftop.sun.black.interpreter.functions.math

import com.github.kofftop.sun.black.interpreter.utils.BinaryOp
import com.github.kofftop.sun.black.interpreter.utils.MessageScope

/**
 * Функция сложения двух переменных.
 * Первым аргументом всегда принимает имя переменной, в которую и будет записан результат сложения объектов.
 * Например `sum result a b` или `sum res str1 str2`.
 * Обратите внимание, что передача литералов функции `sum` запрещена.
 * @param scope область видимости сообщения
 * @param args все что указывается после вызова `sum`, например,
 * для примеров выше, это будет `result a b` или `res str1 str2`.
 * Первый параметр всегда должен являться именем результирующей переменной, а два других, тем что складываем.
 */
class Sum(scope: MessageScope, args: String) : BinaryOp(scope, args) {

  override fun calculateResult(a: Any, b: Any): Any {
    if (a is String || b is String) {
      return a.toString() + b.toString()
    }

    if (a is Double || b is Double) {
      require(a is Number && b is Number)
      return a.toDouble() + b.toDouble()
    }

    require(a is Long && b is Long)
    return a + b
  }
}