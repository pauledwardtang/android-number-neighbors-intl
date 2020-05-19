package io.phatcat.numberneighborsinternational.domain.usecase

import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel

interface GetPhoneNumberResultsUseCase {
  suspend fun getPhoneNumbers(phoneNumber: String, countries: List<Country>): List<PhoneResultModel>
}
