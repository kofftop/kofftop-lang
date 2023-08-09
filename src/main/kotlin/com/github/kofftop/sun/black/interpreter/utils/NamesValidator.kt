package com.github.kofftop.sun.black.interpreter.utils

/**
 * Сущность для проверки имен переменных
 */
object NamesValidator {

  /**
   * Убедиться что имя начинается с буквы и дальше содержит только разрешенные символы (буквы и цифры).
   * Какие либо иные символы, включая `-` или `_`, в переменных не разрешены.
   */
  fun requireValid(name: String): String {
    require(name.isNotBlank()) { nameErrorMessage(name) }
    require(name.first().isLetter()) { nameErrorMessage(name) }
    require(name.all(Char::isLetterOrDigit)) { nameErrorMessage(name) }
    return name
  }

  private fun nameErrorMessage(name: String) = "Invalid name '$name'"
}