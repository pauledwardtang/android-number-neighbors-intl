package io.phatcat.numberneighborsinternational.phoneverification

import io.phatcat.numberneighborsinternational.application.port.output.GetPhoneNumberVerificationPort
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Phone
import io.phatcat.numberneighborsinternational.entity.PhoneNumber
import io.phatcat.numberneighborsinternational.phoneverification.data.RemoteDataSource
import javax.inject.Inject

class PhoneVerificationRepository @Inject constructor(
  private val remoteDataSource: RemoteDataSource
) : GetPhoneNumberVerificationPort {
  override suspend fun getPhoneVerification(phoneNumber: PhoneNumber, country: Country): Phone {
    return remoteDataSource.getVerificationResult(
      phoneNumber = phoneNumber.number,
      country = country
    )
  }
}
