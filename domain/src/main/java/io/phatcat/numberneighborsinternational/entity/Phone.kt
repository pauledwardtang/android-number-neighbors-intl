package io.phatcat.numberneighborsinternational.entity

data class Phone(
  val valid: Boolean,
  val phoneNumber: String,
  val carrier: String?,
  val lineType: LineType?,
  val location: Location
)
