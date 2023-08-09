package com.github.kofftop.sun.black.interpreter.functions

import com.github.kofftop.sun.black.interpreter.utils.FunctionalLine
import com.github.kotlintelegrambot.Bot

/**
 * Функция `call` вызывает код из файла по идентификатору
 *
 * @param arg идентификатор файла
 */
class Call(private val bot: Bot, private val arg: String) : FunctionalLine {

  override fun call() {
    TODO("Not yet implemented")
  }
}