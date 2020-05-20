package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.BuildConfig
import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.network.service.PhoneNumberService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class GetPhoneNumberResultsUseCaseImpl(
  private val phoneNumberService: PhoneNumberService
) : GetPhoneNumberResultsUseCase {

  override suspend fun getPhoneNumbers(
    phoneNumber: String,
    countries: List<Country>
  ): List<PhoneResultModel> {
    return try {
      val results = mutableListOf<PhoneResultModel>()
      coroutineScope {
        countries.forEach {
          launch(Dispatchers.IO) {
            results.add(getResult(phoneNumber, it))
          }
        }
      }

      results

    } catch (e: Exception) {
      e.printStackTrace()
      emptyList()
    }

  }

  private suspend fun getResult(phoneNumber: String, country: Country): PhoneResultModel {
    val result = phoneNumberService.validatePhoneNumber(
      countryCode = country.countryCode,
      phoneNumber = phoneNumber,
      apiKey = BuildConfig.NUMVERIFY_API_KEY
    )

    return PhoneResultModel(
      success = result.valid,
      countryName = country.countryName,
      dialingCode = country.countryCode
    )
  }

}