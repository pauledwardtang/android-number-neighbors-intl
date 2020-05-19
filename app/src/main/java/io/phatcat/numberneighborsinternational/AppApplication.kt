package io.phatcat.numberneighborsinternational

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://apilayer.net/api/"

class AppApplication : Application() {

  lateinit var retrofit: Retrofit

  override fun onCreate() {
    super.onCreate()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }
}
