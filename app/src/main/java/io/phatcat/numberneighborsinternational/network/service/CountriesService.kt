package io.phatcat.numberneighborsinternational.network.service

import io.phatcat.numberneighborsinternational.domain.entity.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountriesService {

  @GET("countries")
  suspend fun getCountries(@Query("access_key") apiKey: String): List<Country>
}
