package io.phatcat.numberneighborsinternational.network.service

import io.phatcat.numberneighborsinternational.network.model.Countries
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesService {

  @GET("countries")
  suspend fun getCountries(@Query("access_key") apiKey: String): Countries
}
