package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.BuildConfig
import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.network.service.CountriesService

class GetCountriesUseCaseImpl(
  private val countriesService: CountriesService
) : GetCountriesUseCase {

  override suspend fun getCountryCodePrefixes(): List<Country> {
    return countriesService.getCountries(BuildConfig.NUMVERIFY_API_KEY)
  }

}
