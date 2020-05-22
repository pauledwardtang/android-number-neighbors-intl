package io.phatcat.numberneighborsinternational.application.port

import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.ext.requireNotEmpty
import javax.inject.Inject

internal class CountryService @Inject constructor(
  private val getCountriesPort: GetCountriesPort,
  private val storeCountriesPort: StoreCountriesPort
) : GetCountriesUseCase {

  override suspend fun getCountryCodePrefixes(): List<Country> {
    val countries = requireNotEmpty(getCountriesPort.getCountries()) {
      "Failed to get any country data"
    }

    try {
      storeCountriesPort.storeCountries(countries)
    } catch (e: Exception) {
      // If this operation fails, we should still return data!
      e.printStackTrace()
    }

    return countries
  }

}
