package io.phatcat.numberneighborsinternational.usecase

import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Location
import io.phatcat.numberneighborsinternational.entity.Phone
import kotlin.random.Random

class MockGetPhoneNumberResultsUseCase :
  GetPhoneNumberResultsUseCase {
  override suspend fun getPhoneNumbers(
    phoneNumber: String,
    countries: List<Country>
  ): List<Phone> {
    return countries.map { country ->
      Phone(
        valid = Random.nextBoolean(),
        phoneNumber = phoneNumber,
        carrier = null,
        lineType = null,
        location = Location(
          name = null,
          country = country
        )
      )
    }
  }
}
