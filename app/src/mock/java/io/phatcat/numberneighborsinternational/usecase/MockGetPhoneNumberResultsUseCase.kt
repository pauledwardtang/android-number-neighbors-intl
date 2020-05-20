package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import kotlin.random.Random

class MockGetPhoneNumberResultsUseCase : GetPhoneNumberResultsUseCase {
  override suspend fun getPhoneNumbers(
    phoneNumber: String,
    countries: List<Country>
  ): List<PhoneResultModel> {
    return countries.map { country ->
      PhoneResultModel(
        success = Random.Default.nextBoolean(),
        countryName = country.countryName,
        dialingCode = country.dialingCode
      )
    }
  }
}
