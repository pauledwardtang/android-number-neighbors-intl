package io.phatcat.numberneighborsinternational.network.model

class Countries : ArrayList<Country>()

data class Country(
  val countryCode: String,
  val country_name: String,
  val dialling_code: String
)
