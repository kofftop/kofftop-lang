package com.github.kofftop.sun.black.interpreter.utils

import com.github.kofftop.sun.black.interpreter.utils.NamesValidator.requireValid

/**
 * Область видимости одного сообщения. Хранит переменные. Предназначена для использования в одном потоке.
 */
class MessageScope {

  private val vars = mutableMapOf<String, Any>()

  /**
   * Установить переменную, строку или число
   */
  fun setVariable(name: String, value: Any) {
    requireValid(name)
    require(value is Number || value is String)
    vars[name] = value
  }

  fun clear() {
    vars.clear()
  }

  fun requireString(name: String): String {
    return requireVariable(name).toString()
  }

  fun requireDouble(name: String): Double {
    return requireNumber(name).toDouble()
  }

  fun requireLong(name: String): Long {
    return requireNumber(name).toLong()
  }

  fun requireNumber(name: String): Number {
    val value = requireVariable(name)
    require(value is Number) { "$name is not Number!" }
    return value
  }

  fun requireVariable(name: String): Any {
    requireValid(name)
    val value = vars[name]
    require(value != null) { "Not found variable '$name'" }
    return value
  }
}