package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.entity.Country

interface GetCountriesUseCase {
  suspend fun getCountryCodePrefixes(): List<Country>
}
