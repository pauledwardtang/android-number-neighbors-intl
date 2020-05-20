package io.phatcat.numberneighborsinternational.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorInfo(
  @Json(name = "error") val error: Error,
  @Json(name = "success") val success: Boolean
) {
  data class Error(
    @Json(name = "code") val code: Int,
    @Json(name = "info") val info: String?,
    @Json(name = "type") val type: String?
  )
}
