package io.phatcat.numberneighborsinternational.network.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonWriter
import java.lang.UnsupportedOperationException

internal abstract class ReadOnlyJsonAdapter<T> : JsonAdapter<T>() {

  override fun toJson(writer: JsonWriter, value: T?) {
    throw UnsupportedOperationException("Serialization is not supported")
  }
}
