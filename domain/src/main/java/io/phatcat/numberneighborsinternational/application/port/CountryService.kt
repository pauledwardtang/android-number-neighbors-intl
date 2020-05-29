package io.phatcat.numberneighborsinternational.application.port

import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.output.GetCountriesPort
import io.phatcat.numberneighborsinternational.application.port.output.StoreCountriesPort
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.ext.requireNotEmpty
import javax.inject.Inject

// Visible for testing. This could be injected
internal const val RANDOM_COUNTRIES_COUNT = 5

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

    return getRandomCountries(countries, RANDOM_COUNTRIES_COUNT)
  }

  private fun getRandomCountries(countries: List<Country>, numCountries: Int): List<Country> {
    val countrySet = countries.toSet()
    when (numCountries) {
      countrySet.size -> return countries
      !in 0..countrySet.size -> throw IllegalArgumentException(
        "Number of requested neighbors is out of range"
      )
    }

    val randomCountries = HashSet<Country>()
    while (randomCountries.size < numCountries) {
      randomCountries.add(countrySet.random())
    }

    return randomCountries.toList()
  }

}
