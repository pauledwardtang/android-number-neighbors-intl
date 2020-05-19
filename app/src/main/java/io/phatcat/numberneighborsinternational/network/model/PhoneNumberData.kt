package io.phatcat.numberneighborsinternational.network.model

import com.google.gson.annotations.SerializedName

data class PhoneNumberData(
  @SerializedName("carrier")
  val carrier: String?,
  @SerializedName("country_code")
  val countryCode: String?,
  @SerializedName("country_name")
  val countryName: String?,
  @SerializedName("country_prefix")
  val countryPrefix: String?,
  @SerializedName("international_format")
  val internationalFormat: String?,
  @SerializedName("line_type")
  val lineType: String?,
  @SerializedName("local_format")
  val localFormat: String?,
  @SerializedName("location")
  val location: String?,
  @SerializedName("number")
  val number: String,
  @SerializedName("valid")
  val valid: Boolean
)
