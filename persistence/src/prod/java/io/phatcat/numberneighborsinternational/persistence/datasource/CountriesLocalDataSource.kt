package io.phatcat.numberneighborsinternational.persistence.datasource

import io.phatcat.numberneighborsinternational.countries.data.LocalDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import javax.inject.Inject

internal class CountriesLocalDataSource @Inject constructor() : LocalDataSource {
  override suspend fun getCountries(): List<Country> {
    TODO("Not yet implemented")
  }

  override suspend fun storeCountries(countries: List<Country>) {
    TODO("Not yet implemented")
  }

  override suspend fun hasCountries(): Boolean {
    TODO("Not yet implemented")
  }
}
