package com.github.kofftop.sun.black.interpreter.utils.lookup

@Suppress("MemberVisibilityCanBePrivate")
class LookupError(
  val sourceText: String,
  val sourceIndex: Int,
  vararg val expects: String
) : OptionalLookup, RuntimeException() {

  init {
    require(expects.isNotEmpty())
  }

  val unexpectedText by lazy {
    when (val expectedText = expects.singleOrNull()) {
      null -> sourceText.substring(sourceIndex).take(60).plus(" <...>")
      else -> sourceText.substring(sourceIndex).take(expectedText.length)
    }
  }

  override val message by lazy {
    val lineNumber = sourceText.substring(0..sourceIndex).count { it == '\n' }
    when (expects.size) {
      1 -> "At line $lineNumber expected '${expects.single()}' but found '$unexpectedText'"
      else -> "At line $lineNumber expected one of $expects but found '$unexpectedText'"
    }
  }

  override fun lookupFor(expectedText: String): OptionalLookup {
    return this
  }

  override fun skipChar(): OptionalLookup {
    return this
  }

  override fun skipChars(n: Int): OptionalLookup {
    return this
  }

  override fun getString(): String {
    return sourceText.substring(sourceIndex)
  }

  override fun getChar(): Char {
    return sourceText[sourceIndex]
  }

  override fun lookupFor(vararg expectedText: String): OptionalLookup {
    return this
  }
}