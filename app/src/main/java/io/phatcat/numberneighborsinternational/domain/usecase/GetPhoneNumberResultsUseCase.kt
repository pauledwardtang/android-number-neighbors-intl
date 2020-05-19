package io.phatcat.numberneighborsinternational.domain.usecase

import io.phatcat.numberneighborsinternational.domain.entity.PhoneResultModel
import io.phatcat.numberneighborsinternational.network.model.Countries

interface GetPhoneNumberResultsUseCase {
  suspend fun getPhoneNumbers(phoneNumber: String, countries: Countries): List<PhoneResultModel>
}
