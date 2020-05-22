package io.phatcat.numberneighborsinternational.application.port.output

import io.phatcat.numberneighborsinternational.entity.Country

interface StoreCountriesPort {
  suspend fun storeCountries(countries: List<Country>)
}
