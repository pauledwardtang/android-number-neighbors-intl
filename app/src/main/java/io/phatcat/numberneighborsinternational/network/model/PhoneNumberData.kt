package io.phatcat.numberneighborsinternational.network.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PhoneNumberData(
  @Json(name = "carrier") val carrier: String?,
  @Json(name = "country_code") val countryCode: String?,
  @Json(name = "country_name") val countryName: String?,
  @Json(name = "country_prefix") val countryPrefix: String?,
  @Json(name = "international_format") val internationalFormat: String?,
  @Json(name = "line_type") val lineType: String?,
  @Json(name = "local_format") val localFormat: String?,
  @Json(name = "location") val location: String?,
  @Json(name = "number") val number: String,
  @Json(name = "valid") val valid: Boolean
)
