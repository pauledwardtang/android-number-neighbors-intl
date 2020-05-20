package io.phatcat.numberneighborsinternational.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.phatcat.numberneighborsinternational.entity.Country
import io.phatcat.numberneighborsinternational.network.adapter.CountriesAdapter
import io.phatcat.numberneighborsinternational.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.usecase.GetPhoneNumberResultsUseCase
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Note that HTTPS is available only for paid subscriptions
private const val BASE_URL = "http://apilayer.net/api/"

// This can be replaced with Dagger 🗡
abstract class AbstractUseCaseFactory {
  protected val retrofit: Retrofit
  protected val moshi: Moshi

  abstract val getCountriesUseCase: GetCountriesUseCase
  abstract val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

  init {
    val listType = Types.newParameterizedType(List::class.java, Country::class.java)
    moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(
        listType,
        CountriesAdapter()
      )
      .build()

    retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

}
