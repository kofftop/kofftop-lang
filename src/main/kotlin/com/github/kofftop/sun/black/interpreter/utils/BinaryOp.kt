package com.github.kofftop.sun.black.interpreter.utils

import com.github.kofftop.sun.black.interpreter.utils.NamesValidator.requireValid

/**
 * Абстрактная операция, принимающая три аргумента
 * 1. имя переменной куда будет записан результат
 * 2. имя первой переменной участвующей в операции
 * 3. имя второй переменной участвующей в операции
 *
 * Примеры таких функций: `sum res a b`, `div result x y`, и так далее
 *
 * @param scope область видимости операции
 * @param args три вышеописанных аргумента следующие за вызовом математической операции, например
 * `res a b` или `result x y`.
 */
abstract class BinaryOp(private val scope: MessageScope, args: String) : FunctionalLine {

  private val resultName: String

  private val firstName: String

  private val secondName: String

  init {
    val names = args.split(' ').filter(String::isNotBlank).map(String::trim)
    require(names.size == 3) { "Invalid args: $args" }
    resultName = requireValid(names[0])
    firstName = requireValid(names[1])
    secondName = requireValid(names[2])
  }

  /**
   * Вычислить операцию из двух последних операндов и записать результат в первое имя переменной.
   * Например: `sum res a b`.
   */
  final override fun call() {
    val a = scope.requireVariable(firstName)
    val b = scope.requireVariable(secondName)
    val resultValue = calculateResult(a, b)
    scope.setVariable(resultName, resultValue)
  }

  /**
   * Результат операции с двумя последними переменными будет сохранен для дальнейшего использования в первую
   */
  protected abstract fun calculateResult(a: Any, b: Any): Any
}