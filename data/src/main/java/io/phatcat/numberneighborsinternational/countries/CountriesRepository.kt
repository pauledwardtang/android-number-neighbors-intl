package io.phatcat.numberneighborsinternational.countries

import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.countries.data.LocalDataSource
import io.phatcat.numberneighborsinternational.countries.data.RemoteDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
  private val localDataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource
) :
  GetCountriesPort,
  StoreCountriesPort {

  override suspend fun getCountries(): List<Country> {
    // Country data can change, but not very often. A new port can be later added to determine
    // conditions for retrieving new data (expiry, user input, etc)
    return if (localDataSource.hasCountries())
      localDataSource.getCountries()
    else
      remoteDataSource.fetchCountries()
  }

  override suspend fun storeCountries(countries: List<Country>) {
    var shouldStore = false
    if (localDataSource.hasCountries()) {
      shouldStore = localDataSource.getCountries() != countries
    }
    if (shouldStore) {
      localDataSource.storeCountries(countries)
    }
  }

}
