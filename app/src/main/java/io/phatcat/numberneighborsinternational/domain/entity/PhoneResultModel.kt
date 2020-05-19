package io.phatcat.numberneighborsinternational.domain.entity

data class PhoneResultModel(
  val success: Boolean,
  val countryPrefix: String,
  val countryName: String
)