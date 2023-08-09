package com.github.kofftop.sun.black.interpreter.utils.lookup

sealed interface OptionalLookup {

  /**
   * If the passed text is from the current position,
   * a new [Lookup] instance is returned in which the position
   * is shifted by the size of [expectedText] to the new unknown.
   * Otherwise [LookupError] is returned.
   */
  fun lookupFor(expectedText: String): OptionalLookup

  /**
   * Lazy [lookupFor] many variants
   */
  fun lookupFor(vararg expectedText: String): OptionalLookup

  /**
   * @return a new [Lookup] instance from the next character.
   * If there are no characters left, the current [Lookup] instance will be returned.
   */
  fun skipChar(): OptionalLookup

  /**
   * @return a new [Lookup] instance dropped [n] chars.
   * If there are not enough characters, the maximum possible number will be skipped.
   */
  fun skipChars(n: Int): OptionalLookup

  /**
   * @return current substring
   */
  fun getString(): String

  /**
   * @return current char
   */
  fun getChar(): Char
}