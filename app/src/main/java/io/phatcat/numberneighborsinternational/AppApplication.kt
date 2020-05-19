package io.phatcat.numberneighborsinternational

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://apilayer.net/api/"

class AppApplication : Application() {

  lateinit var retrofit: Retrofit

  override fun onCreate() {
    super.onCreate()

    retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}
