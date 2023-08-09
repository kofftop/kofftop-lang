package com.github.kofftop.sun.black.interpreter.utils.lookup

class Lookup(private val text: String, private val pos: Int = 0) : OptionalLookup {

  init {
    require(text.isNotBlank())
    require(pos in text.indices)
  }

  override fun lookupFor(expectedText: String): OptionalLookup {
    return when (text.startsWith(expectedText, pos)) {
      true -> Lookup(text, pos + expectedText.length)
      else -> LookupError(text, pos, expectedText)
    }
  }

  override fun skipChar(): OptionalLookup {
    return skipChars(1)
  }

  override fun skipChars(n: Int): OptionalLookup {
    return when (val pos = pos + n) {
      in text.indices -> Lookup(text, pos)
      else -> this
    }
  }

  override fun getString(): String {
    return text.substring(pos)
  }

  override fun getChar(): Char {
    return text[pos]
  }

  override fun lookupFor(vararg expectedText: String): OptionalLookup {
    return when(val lookup = expectedText.asSequence().map(::lookupFor).firstOrNull { it is Lookup }) {
      null -> LookupError(text, pos, *expectedText)
      else -> lookup
    }
  }
}