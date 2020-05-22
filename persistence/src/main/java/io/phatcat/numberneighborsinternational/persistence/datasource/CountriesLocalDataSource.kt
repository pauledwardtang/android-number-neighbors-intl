package io.phatcat.numberneighborsinternational.persistence.datasource

import io.phatcat.numberneighborsinternational.countries.data.LocalDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.persistence.dao.CountryDao
import io.phatcat.numberneighborsinternational.persistence.entity.CountryEntity
import javax.inject.Inject

internal class CountriesLocalDataSource @Inject constructor(
  private val dao: CountryDao
) : LocalDataSource {

  private var cache: List<Country>? = null

  override suspend fun getCountries(): List<Country> {
    return if (!cache.isNullOrEmpty()) {
      cache!!
    } else {
      cache = dao.getAll().map { it.toCountry() }
      cache!!
    }
  }

  override suspend fun storeCountries(countries: List<Country>) {
    if (countries.isEmpty()) return

    cache = countries
    dao.insert(countries.map { it.toEntity() })
  }

  override suspend fun hasCountries(): Boolean {
    if (cache == null) {
      cache = getCountries()
    }

    return !cache.isNullOrEmpty()
  }

  private fun CountryEntity.toCountry() = Country(
    name = name,
    countryCode = countryCode,
    dialingCode = dialingCode
  )

  private fun Country.toEntity() = CountryEntity(
    name = name,
    countryCode = countryCode,
    dialingCode = dialingCode
  )

}
