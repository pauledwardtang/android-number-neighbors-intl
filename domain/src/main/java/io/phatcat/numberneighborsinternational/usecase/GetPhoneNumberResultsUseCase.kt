package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Phone

interface GetPhoneNumberResultsUseCase {
  suspend fun getPhoneNumbers(phoneNumber: String, countries: List<Country>): List<Phone>
}
