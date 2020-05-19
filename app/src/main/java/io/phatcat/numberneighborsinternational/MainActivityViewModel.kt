package io.phatcat.numberneighborsinternational

import androidx.lifecycle.ViewModel
import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel
import io.phatcat.numberneighborsinternational.domain.usecase.GetCountryCodePrefixesUseCase
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.network.model.Countries
import io.phatcat.numberneighborsinternational.network.model.Country

private const val RANDOM_COUNTRIES_COUNT = 5

class MainActivityViewModel(
  private val getCountryCodePrefixesUseCase: GetCountryCodePrefixesUseCase,
  private val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase
) : ViewModel() {

  suspend fun searchClicked(phoneNumber: String): List<PhoneResultModel> {
    // 1. Get and cache list of country code prefixes
    val countries = getCountryCodePrefixesUseCase.getCountryCodePrefixes()

    // 2. Get random country prefixes
    val randomCountries = countries.randomCountries(RANDOM_COUNTRIES_COUNT)

    // 3. Make API calls with numverify and build out the results
    return getPhoneNumberResultsUseCase.getPhoneNumbers(phoneNumber, randomCountries)

  }

  private fun Countries.randomCountries(numCountries: Int): Countries {
    when (numCountries) {
      size -> return this
      !in 0..size -> throw IllegalArgumentException("Out of range")
    }

    val randomCountries = HashSet<Country>()
    while (randomCountries.size < numCountries) {
      randomCountries.add(random())
    }
    return Countries().apply { addAll(randomCountries) }
  }

}