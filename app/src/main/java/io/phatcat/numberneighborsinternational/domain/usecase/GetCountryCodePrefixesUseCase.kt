package io.phatcat.numberneighborsinternational.domain.usecase

import io.phatcat.numberneighborsinternational.network.model.Countries

interface GetCountryCodePrefixesUseCase {
  suspend fun getCountryCodePrefixes(): Countries
}
