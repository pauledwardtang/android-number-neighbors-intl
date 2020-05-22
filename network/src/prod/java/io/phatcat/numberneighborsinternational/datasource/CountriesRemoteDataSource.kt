package io.phatcat.numberneighborsinternational.datasource

import io.phatcat.numberneighborsinternational.BuildConfig
import io.phatcat.numberneighborsinternational.countries.data.RemoteDataSource
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.network.service.CountriesService
import javax.inject.Inject

internal class CountriesRemoteDataSource @Inject constructor(
  private val countriesService: CountriesService
) : RemoteDataSource {

  override suspend fun fetchCountries(): List<Country> {
    return countriesService.getCountries(BuildConfig.NUMVERIFY_API_KEY)
  }

}
