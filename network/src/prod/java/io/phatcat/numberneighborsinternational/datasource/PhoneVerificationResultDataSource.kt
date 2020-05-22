package io.phatcat.numberneighborsinternational.datasource

import io.phatcat.numberneighborsinternational.BuildConfig
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.LineType
import io.phatcat.numberneighborsinternational.entity.Location
import io.phatcat.numberneighborsinternational.entity.Phone
import io.phatcat.numberneighborsinternational.network.service.PhoneNumberService
import io.phatcat.numberneighborsinternational.phoneverification.data.RemoteDataSource
import javax.inject.Inject

internal class PhoneVerificationResultDataSource @Inject constructor(
  private val phoneNumberService: PhoneNumberService
) : RemoteDataSource {

  override suspend fun getVerificationResult(phoneNumber: String, country: Country): Phone {
    val result = phoneNumberService.validatePhoneNumber(
      countryCode = country.countryCode,
      phoneNumber = phoneNumber,
      apiKey = BuildConfig.NUMVERIFY_API_KEY
    )

    return Phone(
      valid = result.valid,
      phoneNumber = phoneNumber,
      carrier = result.carrier,
      lineType = parseLineType(result.lineType),
      location = Location(
        name = result.location,
        country = country
      )
    )
  }

  private fun parseLineType(input: String?): LineType? {
    return when (input) {
      "mobile" -> LineType.MOBILE
      "landline" -> LineType.LANDLINE
      "special_services" -> LineType.SPECIAL_SERVICES
      "toll_free" -> LineType.TOLL_FREE
      "premium_rate" -> LineType.PREMIUM_RATE
      "satellite" -> LineType.SATELLITE
      "paging" -> LineType.PAGING
      else -> null
    }
  }

}
