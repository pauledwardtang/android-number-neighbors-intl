package io.phatcat.numberneighborsinternational.domain.usecase

import io.phatcat.numberneighborsinternational.domain.entity.Country

interface GetCountryCodePrefixesUseCase {
  suspend fun getCountryCodePrefixes(): List<Country>
}
