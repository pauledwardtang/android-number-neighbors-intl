package io.phatcat.numberneighborsinternational

import androidx.lifecycle.ViewModel
import io.phatcat.numberneighborsinternational.application.port.input.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.application.port.input.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.results.PhoneResultModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
  private val getCountriesUseCase: GetCountriesUseCase,
  private val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase
) : ViewModel() {

  suspend fun searchClicked(phoneNumber: String): List<PhoneResultModel> {
    val countries = getCountriesUseCase.getCountryCodePrefixes()
    return getPhoneNumberResultsUseCase.getPhoneNumbers(phoneNumber, countries)
      .map {
        PhoneResultModel(
          success = it.valid,
          countryName = it.location.country.name,
          dialingCode = it.location.country.dialingCode
        )
      }
  }

}
