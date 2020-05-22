package io.phatcat.numberneighborsinternational.datasource

import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Location
import io.phatcat.numberneighborsinternational.entity.Phone
import io.phatcat.numberneighborsinternational.phoneverification.data.RemoteDataSource
import javax.inject.Inject
import kotlin.random.Random

class PhoneVerificationResultDataSource @Inject constructor() : RemoteDataSource {

  override suspend fun getVerificationResult(phoneNumber: String, country: Country): Phone {
    return Phone(
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
