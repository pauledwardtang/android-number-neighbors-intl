package io.phatcat.numberneighborsinternational.application.port.input

import io.phatcat.numberneighborsinternational.entity.Country

interface GetCountriesUseCase {
  suspend fun getCountryCodePrefixes(): List<Country>
}
