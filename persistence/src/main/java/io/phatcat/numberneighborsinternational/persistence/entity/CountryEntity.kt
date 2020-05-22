package io.phatcat.numberneighborsinternational.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class CountryEntity(
  @PrimaryKey @ColumnInfo(name = "country_code") val countryCode: String,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "dialing_code") val dialingCode: String
)
