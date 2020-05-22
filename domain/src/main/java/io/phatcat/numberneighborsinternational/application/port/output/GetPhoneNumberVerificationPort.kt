package io.phatcat.numberneighborsinternational.application.port.output

import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Phone
import io.phatcat.numberneighborsinternational.entity.PhoneNumber

interface GetPhoneNumberVerificationPort {
  suspend fun getPhoneVerification(phoneNumber: PhoneNumber, country: Country): Phone
}
