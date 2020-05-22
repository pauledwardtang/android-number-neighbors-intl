package io.phatcat.numberneighborsinternational.phoneverification.data

import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.entity.Phone

interface RemoteDataSource {
  suspend fun getVerificationResult(phoneNumber: String, country: Country): Phone
}
