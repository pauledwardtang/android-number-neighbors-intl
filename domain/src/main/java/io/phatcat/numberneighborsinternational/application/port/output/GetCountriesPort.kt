package io.phatcat.numberneighborsinternational.application.port.output

import io.phatcat.numberneighborsinternational.entity.Country

interface GetCountriesPort {
  suspend fun getCountries(): List<Country>
}
