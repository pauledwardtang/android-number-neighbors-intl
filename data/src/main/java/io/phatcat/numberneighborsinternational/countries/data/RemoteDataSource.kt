package io.phatcat.numberneighborsinternational.countries.data

import io.phatcat.numberneighborsinternational.entity.Country

interface RemoteDataSource {
  suspend fun fetchCountries(): List<Country>
}
