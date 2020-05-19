package io.phatcat.numberneighborsinternational.network.model

import com.google.gson.annotations.SerializedName

data class ErrorInfo(
  @SerializedName("error")
  val error: Error,
  @SerializedName("success")
  val success: Boolean
) {
  data class Error(
    @SerializedName("code")
    val code: Int,
    @SerializedName("info")
    val info: String?,
    @SerializedName("type")
    val type: String?
  )
}
