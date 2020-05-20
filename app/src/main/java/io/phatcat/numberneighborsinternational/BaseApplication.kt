package io.phatcat.numberneighborsinternational

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.phatcat.numberneighborsinternational.domain.entity.Country
import io.phatcat.numberneighborsinternational.domain.usecase.GetCountriesUseCase
import io.phatcat.numberneighborsinternational.domain.usecase.GetPhoneNumberResultsUseCase
import io.phatcat.numberneighborsinternational.network.adapter.CountriesAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Note that HTTPS is available only for paid subscriptions
private const val BASE_URL = "http://apilayer.net/api/"

abstract class BaseApplication : Application() {

  protected lateinit var retrofit: Retrofit
  protected lateinit var moshi: Moshi

  abstract val getCountriesUseCase: GetCountriesUseCase
  abstract val getPhoneNumberResultsUseCase: GetPhoneNumberResultsUseCase

  override fun onCreate() {
    super.onCreate()

    val listType = Types.newParameterizedType(List::class.java, Country::class.java)
    moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(listType, CountriesAdapter())
      .build()

    retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

}
