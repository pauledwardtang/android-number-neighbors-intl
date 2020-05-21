package io.phatcat.numberneighborsinternational.network.service

import io.phatcat.numberneighborsinternational.network.model.PhoneNumberData
import retrofit2.http.GET
import retrofit2.http.Query

interface PhoneNumberService {

  @GET("validate")
  suspend fun validatePhoneNumber(
    @Query("number") phoneNumber: String,
    @Query("country_code") countryCode: String,
    @Query("access_key") apiKey: String
  ): PhoneNumberData

}
