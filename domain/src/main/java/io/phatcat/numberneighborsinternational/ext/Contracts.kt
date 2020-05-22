package io.phatcat.numberneighborsinternational.ext

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
internal inline fun <T> requireNotEmpty(value: List<T>?): List<T> {
  contract {
    returns() implies (value != null)
  }

  return requireNotEmpty(value) { "Required value was null or empty" }
}

@OptIn(ExperimentalContracts::class)
internal inline fun <T> requireNotEmpty(
  value: List<T>?,
  lazyMessage: () -> Any
): List<T> {
  contract {
    returns() implies (value != null)
  }

  if (value.isNullOrEmpty()) {
    val message = lazyMessage()
    throw IllegalArgumentException(message.toString())
  } else {
    return value
  }
}


