package io.phatcat.numberneighborsinternational

import androidx.lifecycle.ViewModel
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.results.PhoneResultModel
import javax.inject.Inject

private const val RANDOM_COUNTRIES_COUNT = 5

class MainActivityViewModel @Inject constructor(
  private val getCountriesUseCase: GetCountriesUseCase,
  private val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase
) : ViewModel() {

  suspend fun searchClicked(phoneNumber: String): List<PhoneResultModel> {
    // 1. Get and cache list of country code prefixes
    val countries = getCountriesUseCase.getCountryCodePrefixes()

    // 2. Get random country prefixes
    val randomCountries = getRandomCountries(countries, RANDOM_COUNTRIES_COUNT)

    // 3. Make API calls with numverify and build out the results
    return getPhoneNumberResultsUseCase.getPhoneNumbers(phoneNumber, randomCountries)
      .map {
        PhoneResultModel(
          success = it.valid,
          countryName = it.location.country.name,
          dialingCode = it.location.country.dialingCode
        )
      }
  }

  private fun getRandomCountries(countries: List<Country>, numCountries: Int): List<Country> {
    when (numCountries) {
      countries.size -> return countries
      !in 0..countries.size -> throw IllegalArgumentException(
        "Number of requested neighbors is out of range"
      )
    }

    val randomCountries = HashSet<Country>()
    while (randomCountries.size < numCountries) {
      randomCountries.add(countries.random())
    }

    return randomCountries.toList()
  }

}
