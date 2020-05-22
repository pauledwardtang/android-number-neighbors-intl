package io.phatcat.numberneighborsinternational.network.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// Note that HTTPS is available only for paid subscriptions
private const val BASE_URL = "http://apilayer.net/api/"

@Module(includes = [DataSourceModule::class])
object NetworkModule {

  @Provides
  fun providesRetrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()
  }

}
