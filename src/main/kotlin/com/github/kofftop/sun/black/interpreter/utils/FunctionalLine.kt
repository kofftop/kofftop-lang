package com.github.kofftop.sun.black.interpreter.utils

/**
 * Вспомогательный интерфейс, представляющий функцию собственного ЯП, где каждая строка суть вызов функции.
 * Особенность такого подхода состоит в том, что как только мы распознали первое ключевое слово в строке (вызов функции),
 * то дальше управление как парсингом, так и выполнением строки передается объекту представляющую эту самую функцию.
 * Такой ЯП легко реализовать.
 */
interface FunctionalLine {

  /**
   * Вызвать функцию с заданными параметрами.
   * Каждый вызов будет повторно вычислять функцию с переданными ей именами, обращаясь к области видимости сообщения.
   * Предполагается что такая реализация будет удобна в циклах
   */
  fun call()
}