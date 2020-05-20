package io.phatcat.numberneighborsinternational.domain.entity

import java.io.Serializable

data class PhoneResultModel(
  val success: Boolean,
  val countryName: String,
  val dialingCode: String
) : Serializable
