package io.phatcat.numberneighborsinternational.countries.data

import io.phatcat.numberneighborsinternational.entity.Country

interface LocalDataSource {
  suspend fun getCountries(): List<Country>
  suspend fun storeCountries(countries: List<Country>)
  suspend fun hasCountries(): Boolean
}
